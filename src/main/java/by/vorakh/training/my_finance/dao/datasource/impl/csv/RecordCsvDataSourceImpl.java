package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordCsvDataSource;

public class RecordCsvDataSourceImpl extends RecordCsvDataSource {

    public RecordCsvDataSourceImpl(Convertor<String, Record> formatToBeanConvertor,
            Convertor<Record, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

    @Override
    protected void addTo(Map<String, Record> map, Record entity) {
        map.put(entity.getId(), entity);
    }
    

}
