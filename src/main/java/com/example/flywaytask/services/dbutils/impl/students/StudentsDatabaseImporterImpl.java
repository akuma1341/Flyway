package com.example.flywaytask.services.dbutils.impl.students;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.EntityCRUDService;
import com.example.flywaytask.services.dbutils.DatabaseImporter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class StudentsDatabaseImporterImpl implements DatabaseImporter<Student, Integer> {

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

    private List<Student> getListStudentsFromSheet(Sheet sheet) {
        List<Student> students = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int id = (int) row.getCell(0).getNumericCellValue();
            String firstName = row.getCell(1).getStringCellValue();
            String lastName = row.getCell(2).getStringCellValue();
            Date birthDate = new Date(row.getCell(3).getDateCellValue().getTime());

            Student student = Student.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthDate(birthDate)
                    .build();
            students.add(student);
        }
        return students;
    }
}
