package com.example.flywaytask.services.dbutils;

import java.io.File;
import java.io.IOException;

public interface FileConverter {

    void convertDataFromTxtFileToCSVFile(File txtFile, File csvFile) throws IOException;

    void convertDataFromCsvFileToXlsxFile(File csvFile, File xlsxFile) throws Exception;
}
