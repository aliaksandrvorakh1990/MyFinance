package by.vorakh.training.my_finance.dao.datasource.csv;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public abstract class RecordEntityCsvDataSource extends 
        CsvDataSource<RecordEntity> {

    protected RecordEntityCsvDataSource(
            Convertor<String, RecordEntity> csvToEntityConvertor,
            Convertor<RecordEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

}
