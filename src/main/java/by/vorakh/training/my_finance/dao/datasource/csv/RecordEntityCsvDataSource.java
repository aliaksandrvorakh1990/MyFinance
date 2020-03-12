package by.vorakh.training.my_finance.dao.datasource.csv;

import java.util.Collection;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.CsvDataSource;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public abstract class RecordEntityCsvDataSource extends 
        CsvDataSource<RecordEntity, Collection<RecordEntity>> {

    protected RecordEntityCsvDataSource(
            Convertor<String, RecordEntity> csvToEntityConvertor,
            Convertor<RecordEntity, String> entityToCsvConvertor) {
        super(csvToEntityConvertor, entityToCsvConvertor);
    }
    
}
