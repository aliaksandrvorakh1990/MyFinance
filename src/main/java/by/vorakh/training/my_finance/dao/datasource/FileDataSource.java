package by.vorakh.training.my_finance.dao.datasource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.validation.FileValidator;

public interface FileDataSource<V,T> extends FileValidator {

    public default Map<V, T> read(String path, Convertor<String, T> convertor) throws
            DataSourceException{
        final String PROBLEM = "Unable to execute file reading operation:";
        if (isEqualsNull(path)) {
            String message = PROBLEM + "File path has null value.";
            throw new DataSourceException(message);
        }
        File file = new File(path);
        if (!isExistedFile(file)) {
            String message = PROBLEM + UNCORRECT_FILE;
            throw new DataSourceException(message);
        }
        try (Reader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader);) {
            Map<V, T> map = new LinkedHashMap<V, T>();
            String currentLine;
            while (!isEqualsNull(currentLine = reader.readLine())) {
                if (currentLine.isEmpty()) {
                    continue;
                }
                T t = convertor.converte(currentLine);
                addTo(map, t);
            }
            return map;
        } catch (IOException |ConvertorException e) {
            String message = PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

    public void addTo(Map<V, T> map, T object);

    public default void write(T t, boolean append, String path,
            Convertor<T, String> convertor) throws DataSourceException {
        final String PROBLEM = "Unable to execute file writing operation:";
        if (isEqualsNull(t)) {
            String message = PROBLEM + "object has null value.";
            throw new DataSourceException(message);
        }
        try (Writer fileWriter = new FileWriter(path, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            String  str = convertor.converte(t);
            writer.write(str);
            writer.newLine();
        } catch (ConvertorException|IOException e) {
            String message = PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

    public default void clearFile(String path) throws DataSourceException {
        final String PROBLEM = "Unable to execute file clear operation:";
        if (isEqualsNull(path)) {
            String message = PROBLEM + "object has null value.";
            throw new DataSourceException(message);
        }
        boolean append = false;
        try (Writer fileWriter = new FileWriter(path, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            writer.write("");
        } catch (IOException e) {
            String message = PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

}
