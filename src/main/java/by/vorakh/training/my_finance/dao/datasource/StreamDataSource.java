package by.vorakh.training.my_finance.dao.datasource;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;

public class StreamDataSource<T> {
    
    private Convertor<String, T> formatToBeanConvertor;
    private Convertor<T, String> beanToFormatConvertor;
    
    public StreamDataSource(Convertor<String, T> formatToBeanConvertor, 
            Convertor<T, String> beanToFormatConvertor) {
        this.formatToBeanConvertor = formatToBeanConvertor;
        this.beanToFormatConvertor = beanToFormatConvertor;
    }

    public Stream<T> ReadAll(String pathToFile) throws DataSourceException {
        Path path = Paths.get(Optional.ofNullable(pathToFile)
                .orElseThrow(() -> 
                        new DataSourceException("pathToFile has a null value"))
                );
        try {
            boolean isExist = Files.exists(path, 
                    new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
            Stream<T> stream = (isExist) 
                    ? Files.lines(path)
                            .filter(csv -> !csv.isEmpty())
                            .map(formatToBeanConvertor::converte) 
                    : Stream.empty();
            return stream;
        } catch (IOException e) {
            String message = "App cannot open this file:" + pathToFile;
            throw new DataSourceException(message, e);
        }
    }

    public Path write(Stream<T> beans, String pathToFile) throws 
            DataSourceException {
        Path path = Paths.get(Optional.ofNullable(pathToFile)
                .orElseThrow(() -> 
                        new DataSourceException("pathToFile has a null value"))
                );
        Stream<String> lines = beans.map(beanToFormatConvertor::converte);
        Iterable<String> csvIterable = getIterableFrom(lines.iterator());
        Charset utf8Charset = StandardCharsets.UTF_8;
        OpenOption[] options = new OpenOption[] {StandardOpenOption.CREATE, 
                StandardOpenOption.WRITE, 
                StandardOpenOption.TRUNCATE_EXISTING};
        try {
            return Files.write(path, csvIterable, utf8Charset, options);
        } catch (IOException e) {
            String message = "App cannot create or write this file:" 
                    + pathToFile;
            throw new DataSourceException(message, e);
        }
    }

    public boolean write(T bean, String pathToFile) throws DataSourceException {
        Path path = Paths.get(Optional.ofNullable(pathToFile)
                .orElseThrow(() -> 
                        new DataSourceException("pathToFile has a null value"))
                );
        String beanAsCsv = beanToFormatConvertor.converte(bean);
        Charset utf8Charset = StandardCharsets.UTF_8;
        OpenOption[] options = new OpenOption[] {StandardOpenOption.CREATE, 
                StandardOpenOption.WRITE, 
                StandardOpenOption.APPEND};
        try (BufferedWriter writer = Files
                    .newBufferedWriter(path, utf8Charset, options);) {
            writer.write(beanAsCsv);
            writer.newLine();
            return true;
        } catch (IOException e) {
            String message = "App cannot create or write this file:" 
                    + pathToFile;
            throw new DataSourceException(message, e);
        }
    }
    
    private Iterable<String> getIterableFrom(Iterator<String> iterator) { 
        return new Iterable<String>() { 
            @Override
            public Iterator<String> iterator() { 
                return iterator; 
            } 
        }; 
    } 
    
}
