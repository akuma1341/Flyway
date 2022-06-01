package com.example.flywaytask.services.dbutils.impl.students;

import com.example.flywaytask.entities.Student;
import com.example.flywaytask.services.dbutils.DatabaseExporter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class StudentsDatabaseExporterImpl implements DatabaseExporter<Student> {

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
}
