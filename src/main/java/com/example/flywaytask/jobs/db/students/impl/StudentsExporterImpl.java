package com.example.flywaytask.jobs.db.students.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.jobs.db.students.StudentsExporter;
import com.example.flywaytask.services.StudentsService;
import com.example.flywaytask.services.dbutils.DatabaseExporter;
import com.example.flywaytask.services.dbutils.FileCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class StudentsExporterImpl implements StudentsExporter {

    private final StudentsService studentsService;
    private final FileCreator fileCreator;
    private final DatabaseExporter<Student> databaseExporter;

    @Value("${my.export.file.path}")
    private String filePath;
    @Value("${my.export.file.name.txt}")
    private String fileName;

    @Override
    public void exportStudentsToTxtFile() {
        List<Student> students = studentsService.getAll();
        File txtFile = new File(filePath, fileName);

        try {
            fileCreator.createFile(txtFile);
            databaseExporter.exportTableRecordsToTxtFile(txtFile, students);
        } catch (IOException e) {
            log.error("Error: " + e);
        }
        log.info("Exported students table. Record count: " + students.size() + ". Path: " + txtFile);
    }
}
