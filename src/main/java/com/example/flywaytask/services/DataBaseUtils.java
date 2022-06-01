package com.example.flywaytask.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DataBaseUtils<E, ID> {
    void createFile(File file) throws IOException;

    void exportTableRecordsToTxtFile(File toFile, List<E> records) throws IOException;

    void convertDataFromTxtFileToCSVFile(File txtFile, File csvFile) throws IOException;

    void convertDataFromCsvFileToXlsxFile(File csvFile, File xlsxFile) throws Exception;

    void importDataFromXlsxFile(File fromFile, EntityCRUDService<E, ID> entityCRUDService) throws IOException;
}
