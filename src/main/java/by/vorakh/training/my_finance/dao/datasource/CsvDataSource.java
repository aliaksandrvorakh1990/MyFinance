package by.vorakh.training.my_finance.dao.datasource;

import static by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.validation.FileValidator;

public abstract class CsvDataSource<T, C extends Collection<T>> implements FileValidator {
    
    private Convertor<String, T> csvToEntityConvertor;
    private Convertor<T, String> entitycsvToConvertor;
    
    protected CsvDataSource(Convertor<String, T> csvToEntityConvertor, 
            Convertor<T, String> entitycsvToConvertor) {
        this.csvToEntityConvertor = csvToEntityConvertor;
        this.entitycsvToConvertor = entitycsvToConvertor;
    }

    public Convertor<String, T> getCsvToEntityConvertor() {
        return csvToEntityConvertor;
    }

    public Convertor<T, String> getEntitycsvToConvertor() {
        return entitycsvToConvertor;
    }
    
    public Map<String, T> read(String path) throws DataSourceException{
        if (path == null) {
            String message = READ_PROBLEM + "File path has null value.";
            throw new DataSourceException(message);
        }
        File file = new File(path);
        if (!isExistedFile(file)) {
            String message = READ_PROBLEM + UNCORRECT_FILE;
            throw new DataSourceException(message);
        }
        try (Reader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader);) {
            Map<String, T> map = new LinkedHashMap<String, T>();
            reader.lines().forEach(csv -> {
                T entity = csvToEntityConvertor.converte(csv);
                addTo(map, entity);
            });
            return map;
        } catch (IOException e) {
            String message = READ_PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }
    
    protected abstract void addTo(Map<String, T> map, T entity);
    
    public void write(T entity, String path) throws DataSourceException {
        if (entity == null) {
            String message = WRITE_PROBLEM + "entity has null value.";
            throw new DataSourceException(message);
        }
        boolean append = true;
        try (Writer fileWriter = new FileWriter(path, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            String  csv = entitycsvToConvertor.converte(entity);
            writer.write(csv);
            writer.newLine();
        } catch (ConvertorException|IOException e) {
            String message = WRITE_PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }
    

    public void write(C entities, String path) throws 
            DataSourceException {
        if (entities == null) {
            String message = WRITE_PROBLEM + "entities has null value.";
            throw new DataSourceException(message);
        }
        for  (T entity : entities) {
            try {
                write(entity, path);
            } catch (DataSourceException e) {
                String message = WRITE_PROBLEM + e.getMessage();
                throw new DataSourceException(message, e);
            }
        }
    }

    public void clearFile(String path) throws DataSourceException {
        if (isEqualsNull(path)) {
            String message = CREAR_PROBLEM + "path has null value.";
            throw new DataSourceException(message);
        }
        boolean append = false;
        try (Writer fileWriter = new FileWriter(path, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            writer.write("");
        } catch (IOException e) {
            String message = CREAR_PROBLEM + e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

}
