package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToUserEntityConvetor;
import by.vorakh.training.my_finance.convertor.impl.csv.UserEntityToCsvConvetor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class UserEntityCsvDataSourceImplWriteTest {
    
    UserEntityCsvDataSourceImpl ds;
    private String path;
    private String expectedFilePath;
    private File expectedFile;
    private File actualFile;
    
    @Before
    public void init() throws IOException {
        Convertor<String, UserEntity> csvToEntityConvertor = 
                new CsvToUserEntityConvetor();
        Convertor<UserEntity, String> entitycsvToConvertor = 
                new UserEntityToCsvConvetor();
        ds = new UserEntityCsvDataSourceImpl(
                csvToEntityConvertor, entitycsvToConvertor);
        path = "src/test/resources/csv/data_source_impl/test.csv";
        expectedFilePath = "src/test/resources/csv/data_source_impl"
                + "/users.csv";
        expectedFile = new File(expectedFilePath);
        actualFile = new File(path);
        actualFile.createNewFile();
    }
    
    @After
    public void finalize() {
        actualFile.delete();
    }

    @Test
    public void testWrite_Single_UserEntity() throws DataSourceException, 
            IOException {
        String login = "alex";
        String password = "3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f"
                + "978d7c846f4";
        UserRole role = UserRole.ADMIN;
        UserEntity user = new UserEntity(login, password, role);
        ds.write(user, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testWrite_Collection_UserEntity() throws IOException, 
            DataSourceException {
        String login = "alex";
        String password = "3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f"
                + "978d7c846f4";
        UserRole role = UserRole.ADMIN;
        UserEntity user = new UserEntity(login, password, role);
        Collection<UserEntity> users =  new ArrayList<UserEntity>();
        users.add(user);
        ds.write(users, path);
        byte[] expected = Files.readAllBytes(expectedFile.toPath());
        byte[] actual = Files.readAllBytes(actualFile.toPath());
        assertArrayEquals(expected, actual);
    }

}
