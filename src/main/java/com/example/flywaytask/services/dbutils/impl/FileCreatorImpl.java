package com.example.flywaytask.services.dbutils.impl;

import com.example.flywaytask.services.dbutils.FileCreator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileCreatorImpl implements FileCreator {

    @Override
    public void createFile(File file) throws IOException {
        Files.createDirectories(Paths.get(file.getParent()));
        if (Files.notExists(Paths.get(file.getPath()))) {
            Files.createFile(Paths.get(file.getPath()));
        }
    }
}
