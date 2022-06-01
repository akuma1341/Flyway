package com.example.flywaytask.jobs.db;

import com.example.flywaytask.jobs.db.students.StudentsExporter;
import com.example.flywaytask.jobs.db.students.StudentsFileConverter;
import com.example.flywaytask.jobs.db.students.StudentsImporter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@ConditionalOnProperty(value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledDBJobsExecutorConfig {

    private final StudentsExporter studentsExporter;
    private final StudentsFileConverter studentsFileConverter;
    private final StudentsImporter studentsImporter;

    @Scheduled(cron = "0 */30 * * * *")
    public void executeStudentsTasks() {
        studentsExporter.exportStudentsToTxtFile();
        studentsFileConverter.convertFromTxtToCsv();
        studentsFileConverter.convertFromCsvToXlsx();
        studentsImporter.importDataFromXlsxFile();
    }
}
