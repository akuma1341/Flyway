package com.example.flywaytask.services.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.DataBaseUtils;
import com.example.flywaytask.services.EntityCRUDService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentsDataBaseUtilImpl implements DataBaseUtils<Student, Integer> {
    @Override
    public void createFile(File file) throws IOException {
        Files.createDirectories(Paths.get(file.getParent()));
        if (Files.notExists(Paths.get(file.getPath()))) {
            Files.createFile(Paths.get(file.getPath()));
        }
    }

    @Override
    public void exportTableRecordsToTxtFile(File toFile, List<Student> students) throws IOException {
        try (FileWriter fileWriter = new FileWriter(toFile, false)) {
            for (Student student : students) {
                fileWriter.write(student.getId().toString());
                fileWriter.append(",");
                fileWriter.write(student.getFirstName());
                fileWriter.append(",");
                fileWriter.write(student.getLastName());
                fileWriter.append(",");
                fileWriter.write(student.getBirthDate().toString());
                fileWriter.append("\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new IOException("Error writing TXT file: " + e);
        }
    }

    @Override
    public void convertDataFromTxtFileToCSVFile(File txtFile, File csvFile) throws IOException {
        try (FileReader fileReader = new FileReader(txtFile);
             CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile))) {

            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                csvWriter.writeNext(record.split(","));
            }
        } catch (IOException e) {
            throw new IOException("Error writing CSV file: " + e);
        }
    }

    @Override
    public void convertDataFromCsvFileToXlsxFile(File csvFile, File xlsxFile) throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile));
             OutputStream outputStream = new FileOutputStream(xlsxFile)) {

            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Students");

            CellStyle dateStyle = createDateCellStyle(workbook);
            createTitleRow(sheet);

            List<String[]> records = csvReader.readAll();
            int i = 1;
            for (String[] record : records) {
                Row recordRow = createRecordRow(sheet, i, dateStyle);
                fillRecordRow(recordRow, record);
                i++;
            }

            sheet.autoSizeColumn(1);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            throw new Exception("Error writing XLSX file: " + e);
        }
    }

    @Override
    public void importDataFromXlsxFile(File fromFile, EntityCRUDService<Student, Integer> entityCRUDService) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(fromFile)) {
            Workbook workbook = new HSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet("Students");

            List<Student> students = getListStudentsFromSheet(sheet);
            entityCRUDService.saveAll(students);

            workbook.close();
        } catch (IOException e) {
            throw new IOException("Error reading XLSX file: " + e);
        }
    }

    private CellStyle createDateCellStyle(Workbook workbook) {
        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        return dateStyle;
    }

    private void createTitleRow(Sheet sheet) {
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("First name");
        row.createCell(2).setCellValue("Last name");
        row.createCell(3).setCellValue("Birth date");
    }

    private Row createRecordRow(Sheet sheet, int index, CellStyle dateStyle) {
        Row row = sheet.createRow(index);
        row.createCell(0);
        row.createCell(1);
        row.createCell(2);
        row.createCell(3).setCellStyle(dateStyle);
        return row;
    }

    private void fillRecordRow(Row row, String[] record) {
        int id = Integer.parseInt(record[0]);
        String firstName = record[1];
        String lastName = record[2];
        Date birthDate = Date.valueOf(record[3]);

        row.getCell(0).setCellValue(id);
        row.getCell(1).setCellValue(firstName);
        row.getCell(2).setCellValue(lastName);
        row.getCell(3).setCellValue(birthDate);
    }

    private List<Student> getListStudentsFromSheet(Sheet sheet) {
        List<Student> students = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int id = (int) row.getCell(0).getNumericCellValue();
            String firstName = row.getCell(1).getStringCellValue();
            String lasName = row.getCell(2).getStringCellValue();
            Date birthDate = new Date(row.getCell(3).getDateCellValue().getTime());

            Student student = Student.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lasName)
                    .birthDate(birthDate)
                    .build();
            students.add(student);
        }
        return students;
    }
}
