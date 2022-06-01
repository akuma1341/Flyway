package com.example.flywaytask.jobs.db.students.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.jobs.db.students.StudentsFileConverter;
import com.example.flywaytask.services.DataBaseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentsFileConverterImpl implements StudentsFileConverter {

    private final DataBaseUtils<Student, Integer> dataBaseUtils;

    @Value("${my.export.file.path}")
    private String filePath;

    @Value("${my.export.file.name.txt}")
    private String txtFileName;

    @Value("${my.export.file.name.csv}")
    private String csvFileName;

    @Value("${my.export.file.name.xlsx}")
    private String xlsxFileName;

    @Override
    public void convertFromTxtToCsv() {
        File fromFile = new File(filePath, txtFileName);
        File toFile = new File(filePath, csvFileName);

        try {
            dataBaseUtils.createFile(toFile);
            dataBaseUtils.convertDataFromTxtFileToCSVFile(fromFile, toFile);
        } catch (IOException e) {
            log.error("Error: " + e);
        }
        log.info("Data converted: from " + fromFile.getPath() + " to " + toFile.getPath());
    }

    @Override
    public void convertFromCsvToXlsx() {
        File fromFile = new File(filePath, csvFileName);
        File toFile = new File(filePath, xlsxFileName);

        try {
            dataBaseUtils.createFile(toFile);
            dataBaseUtils.convertDataFromCsvFileToXlsxFile(fromFile, toFile);
        } catch (Exception e) {
            log.error("Error: " + e);
        }
        log.info("Data converted: from " + fromFile.getPath() + " to " + toFile.getPath());
    }
}
