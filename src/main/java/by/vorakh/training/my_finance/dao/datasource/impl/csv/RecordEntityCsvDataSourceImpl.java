package by.vorakh.training.my_finance.dao.datasource.impl.csv;

import java.util.Map;

import by.vorakh.training.my_finance.convertor.Convertor;
import by.vorakh.training.my_finance.dao.datasource.csv.RecordEntityCsvDataSource;
import by.vorakh.training.my_finance.dao.entity.RecordEntity;

public class RecordEntityCsvDataSourceImpl extends RecordEntityCsvDataSource {

    public RecordEntityCsvDataSourceImpl(
            Convertor<String, RecordEntity> csvToEntityConvertor,
            Convertor<RecordEntity, String> entitycsvToConvertor) {
        super(csvToEntityConvertor, entitycsvToConvertor);
    }

    @Override
    protected void addTo(Map<String, RecordEntity> map, RecordEntity entity) {
        map.put(entity.getId(), entity);
    }

}
