package com.example.flywaytask.jobs.db.students.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.jobs.db.students.StudentsImporter;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.dbutils.DatabaseImporter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentsImporterImpl implements StudentsImporter {

    private final StudentsService studentsService;
    private final DatabaseImporter<Student, Integer> databaseImporter;

    @Value("${my.export.file.path}")
    private String filePath;

    @Value("${my.export.file.name.xlsx}")
    private String fromFileName;

    @Override
    public void importDataFromXlsxFile() {
        File fromFile = new File(filePath, fromFileName);

        try {
            databaseImporter.importDataFromXlsxFile(fromFile, studentsService);
        } catch (IOException e) {
            log.error("Error: " + e);
        }
        log.info("Data imported: from " + fromFile.getPath());
    }
}
