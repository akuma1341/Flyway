package com.example.flywaytask.jobs.db.students.impl;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.jobs.db.students.StudentsExporter;
import com.example.flywaytask.services.DataBaseUtils;
import com.example.flywaytask.services.StudentsService;
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
    private final DataBaseUtils<Student, Integer> dataBaseUtils;

    @Value("${my.export.file.path}")
    private String filePath;
    @Value("${my.export.file.name.txt}")
    private String fileName;

    @Override
    public void exportStudentsToTxtFile() {
        List<Student> students = studentsService.getAll();
        File txtFile = new File(filePath, fileName);

        try {
            dataBaseUtils.createFile(txtFile);
            dataBaseUtils.exportTableRecordsToTxtFile(txtFile, students);
        } catch (IOException e) {
            log.error("Error: " + e);
        }
        log.info("Exported students table. Record count: " + students.size() + ". Path: " + txtFile);
    }
}
