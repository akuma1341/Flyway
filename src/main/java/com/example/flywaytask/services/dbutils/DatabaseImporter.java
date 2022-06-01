package com.example.flywaytask.services.dbutils;

import com.example.flywaytask.services.EntityCRUDService;

import java.io.File;
import java.io.IOException;

public interface DatabaseImporter<E, ID> {

    void importDataFromXlsxFile(File fromFile, EntityCRUDService<E, ID> entityCRUDService) throws IOException;
}
