package com.management.library.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


public interface FileService {
    public void exportCSV(String fileName, HttpServletResponse response)
            throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException;
}

