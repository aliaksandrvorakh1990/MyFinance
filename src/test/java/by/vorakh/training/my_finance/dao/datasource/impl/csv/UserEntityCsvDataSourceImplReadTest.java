package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.convertor.impl.csv.CsvToUserEntityConvetor;
import by.vorakh.training.my_finance.convertor.impl.csv.UserEntityToCsvConvetor;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;

public class UserEntityCsvDataSourceImplReadTest {

    UserEntityCsvDataSourceImpl ds;
    private String path;
    
    @Before
    public void init() throws IOException {
        Convertor<String, UserEntity> csvToEntityConvertor = 
                new CsvToUserEntityConvetor();
        Convertor<UserEntity, String> entitycsvToConvertor = 
                new UserEntityToCsvConvetor();
        ds = new UserEntityCsvDataSourceImpl(
                csvToEntityConvertor, entitycsvToConvertor);
        path = "src/test/resources/csv/data_source_impl"
                + "/users.csv";
    }

    @Test
    public void testRead() throws DataSourceException {
        String login = "alex";
        String password = "3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f"
                + "978d7c846f4";
        UserRole role = UserRole.ADMIN;
        UserEntity user = new UserEntity(login, password, role);
        Map<String, UserEntity> expected = 
                new LinkedHashMap<String, UserEntity>();
        expected.put(login, user);
        Map<String, UserEntity> actual = ds.read(path);
        assertEquals(expected, actual);
    }

}
