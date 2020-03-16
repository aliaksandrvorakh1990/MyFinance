package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;

public abstract class RecordCsvDataSource extends 
        CsvDataSource<Record, Collection<Record>> {

    protected RecordCsvDataSource(
            Convertor<String, Record> csvToEntityConvertor,
            Convertor<Record, String> entityToCsvConvertor) {
        super(csvToEntityConvertor, entityToCsvConvertor);
    }
    
}
