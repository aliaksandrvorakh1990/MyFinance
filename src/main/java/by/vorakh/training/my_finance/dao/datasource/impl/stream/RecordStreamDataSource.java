package by.vorakh.training.my_finance.dao.datasource.impl.stream;

import by.vorakh.training.my_finance.bean.Record;
import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.StreamDataSource;

public class RecordStreamDataSource extends StreamDataSource<Record> {

    public RecordStreamDataSource(
            Convertor<String, Record> formatToBeanConvertor,
            Convertor<Record, String> beanToFormatConvertor) {
        super(formatToBeanConvertor, beanToFormatConvertor);
    }

}
