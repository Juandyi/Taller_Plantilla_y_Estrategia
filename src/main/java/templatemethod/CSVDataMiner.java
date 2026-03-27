package templatemethod;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataMiner extends DataMiner {

    @Override
    protected RawData extractData(File file) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(file)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        }
        return new RawData(sb.toString());
    }

    @Override
    protected Data parseData(RawData rawData) throws Exception {
        List<String[]> rows = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(new java.io.StringReader(rawData.getContent()));
        for (CSVRecord record : records) {
            String[] row = new String[record.size()];
            for (int i = 0; i < record.size(); i++) {
                row[i] = record.get(i);
            }
            rows.add(row);
        }
        return new Data(rawData.getContent(), rows);
    }
}