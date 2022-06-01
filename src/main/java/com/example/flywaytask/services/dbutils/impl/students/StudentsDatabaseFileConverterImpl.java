package com.example.flywaytask.services.dbutils.impl.students;

import com.example.flywaytask.services.dbutils.FileConverter;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class StudentsDatabaseFileConverterImpl implements FileConverter {

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
            for (int i = 0; i < records.size(); i++) {
                Row recordRow = createRecordRow(sheet, i + 1, dateStyle);
                fillRecordRow(recordRow, records.get(i));
            }

            sheet.autoSizeColumn(1);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            throw new Exception("Error writing XLSX file: " + e);
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
}
