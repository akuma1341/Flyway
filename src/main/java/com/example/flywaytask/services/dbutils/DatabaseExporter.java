package com.example.flywaytask.services.dbutils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DatabaseExporter<E> {

    void exportTableRecordsToTxtFile(File toFile, List<E> records) throws IOException;
}
