package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.AbstractDataSource;

public abstract class RecordCsvDataSource extends 
        AbstractDataSource<Record, Collection<Record>> {

    protected RecordCsvDataSource(
            Convertor<String, Record> formatToBeanConvertor,
            Convertor<Record, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
