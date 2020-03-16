package by.vorakh.training.my_finance.dao.impl.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.vorakh.training.my_finance.bean.UserRole;
import by.vorakh.training.my_finance.dao.datasource.csv.UserCsvDataSource;
import by.vorakh.training.my_finance.dao.datasource.exception.DataSourceException;
import by.vorakh.training.my_finance.dao.entity.UserEntity;
import by.vorakh.training.my_finance.dao.exception.DAOException;

public class CsvUserDAOTest {
    
    @Mock
    private UserCsvDataSource dataSource;

    @InjectMocks
    private CsvUserDAO dao;
    
    private Map<String,UserEntity> userMap;
    private String path;
    
    @Before
    public void init() {
        userMap = new LinkedHashMap<String,UserEntity>();
        UserEntity user1 = new UserEntity("admin", "3ac674216f3e15c761ee1a5e255"
                + "f067953623c8b388b4459e13f978d7c846f4", UserRole.ADMIN);
        UserEntity user2 = new UserEntity("MrRobot","888df25ae35772424a560c7152"
                + "a1de794440e0ea5cfee62828333a456a506e05", UserRole.USER);
        UserEntity user3 = new UserEntity("MrXXX","ffe1abd1a08215353c233d6e0096"
                + "13e95eec4253832a761af28ff37ac5a150c", UserRole.USER);
        userMap.put(user1.getLogin(), user1);
        userMap.put(user2.getLogin(), user2);
        userMap.put(user3.getLogin(), user3);
        path = "./csv/users.csv";
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() throws DataSourceException, DAOException {
        List<UserEntity> expected = new ArrayList<UserEntity>();
        expected.add(new UserEntity("admin", "3ac674216f3e15c761ee1a5e255"
                + "f067953623c8b388b4459e13f978d7c846f4", UserRole.ADMIN));
        expected.add(new UserEntity("MrRobot","888df25ae35772424a560c7152"
                + "a1de794440e0ea5cfee62828333a456a506e05", UserRole.USER));
        expected.add(new UserEntity("MrXXX","ffe1abd1a08215353c233d6e0096"
                + "13e95eec4253832a761af28ff37ac5a150c", UserRole.USER));
        when(dataSource.read(path)).thenReturn(userMap);
        List<UserEntity> actual = dao.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetById() throws DAOException, DataSourceException {
        UserEntity expected = new UserEntity("MrRobot","888df25ae35772424a560c7"
                + "152a1de794440e0ea5cfee62828333a456a506e05", UserRole.USER);
        String id = "MrRobot";
        when(dataSource.read(path)).thenReturn(userMap);
        UserEntity actual = dao.getById(id);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreate() throws DataSourceException, DAOException {
        String expected = "admin";
        UserEntity newUser = new UserEntity("admin", "3ac674216f3e15c761"
                + "ee1a5e255f067953623c8b388b4459e13f978d7c846f4", 
                UserRole.ADMIN);
        String actual = dao.create(newUser);
        verify(dataSource, times(1)).write(newUser, path);
        assertEquals(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testUpdate() throws DataSourceException, DAOException {
        UserEntity updatedUser = new UserEntity("admin", "3ac674216f3e15c761"
                + "ee1a5e255f067953623c8b388b4459e13f978d7c846f4", 
                UserRole.ADMIN);
        when(dataSource.read(path)).thenReturn(userMap);
        boolean actual = dao.update(updatedUser);
        verify(dataSource, times(1)).clearFile(path);
        verify(dataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testDelete() throws DataSourceException, DAOException {
        String id = "MrXXX";
        when(dataSource.read(path)).thenReturn(userMap);
        boolean actual = dao.delete(id);
        verify(dataSource, times(1)).clearFile(path);
        verify(dataSource, times(1)).write(any(Collection.class), 
                any(String.class));
        assertTrue(actual);
    }

}
