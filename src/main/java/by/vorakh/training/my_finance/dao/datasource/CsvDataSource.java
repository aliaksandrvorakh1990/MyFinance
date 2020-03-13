package by.vorakh.training.my_finance.dao.datasource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.exception.ConvertorException;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public abstract class CsvDataSource<T, C extends Collection<T>> {
    
    private Convertor<String, T> csvToEntityConvertor;
    private Convertor<T, String> entityToCsvConvertor;
    
    protected CsvDataSource(Convertor<String, T> csvToEntityConvertor, 
            Convertor<T, String> entityToCsvConvertor) {
        this.csvToEntityConvertor = csvToEntityConvertor;
        this.entityToCsvConvertor = entityToCsvConvertor;
    }

    public Convertor<String, T> getCsvToEntityConvertor() {
        return csvToEntityConvertor;
    }

    public Convertor<T, String> getEntityToCsvConvertor() {
        return entityToCsvConvertor;
    }
    
    public Map<String, T> read(String path) throws DataSourceException{
        if (path == null) {
            String message = "File path has null value.";
            throw new DataSourceException(message);
        }
        Map<String, T> map = new LinkedHashMap<String, T>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.filter(csv -> !csv.isEmpty()).forEach(csv -> {
                    T entity = csvToEntityConvertor.converte(csv);
                    addTo(map, entity);
            });
               
        } catch (InvalidPathException | IOException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
        return map;
    }
    
    protected abstract void addTo(Map<String, T> map, T entity);
    
    public void write(T entity, String path) throws DataSourceException {
        if (entity == null) {
            String message = "entity has null value.";
            throw new DataSourceException(message);
        }
        boolean append = true;
        try (Writer fileWriter = new FileWriter(path, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            String  csv = entityToCsvConvertor.converte(entity);
            writer.write(csv);
            writer.newLine();
        } catch (ConvertorException|IOException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
    }
    

    public void write(C entities, String path) throws 
            DataSourceException {
        if (entities == null) {
            String message = "Entities has null value.";
            throw new DataSourceException(message);
        }
        for  (T entity : entities) {
            try {
                write(entity, path);
            } catch (DataSourceException e) {
                String message =  e.getMessage();
                throw new DataSourceException(message, e);
            }
        }
    }

    public void clearFile(String path) throws DataSourceException {
        if (path == null) {
            String message = "path has null value.";
            throw new DataSourceException(message);
        }
        boolean append = false;
        File file = new File(path);
        try (Writer fileWriter = new FileWriter(file, append);
             BufferedWriter writer = new BufferedWriter(fileWriter);) {
            writer.write("");
        } catch (IOException e) {
            String message = e.getMessage();
            throw new DataSourceException(message, e);
        }
    }

}
