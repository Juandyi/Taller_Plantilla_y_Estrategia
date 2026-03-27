package templatemethod;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

public class DocxDataMiner extends DataMiner {

    @Override
    protected RawData extractData(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument doc = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(doc)) {

            String text = extractor.getText();
            return new RawData(text);
        }
    }

    @Override
    protected Data parseData(RawData rawData) {
        return new Data(rawData.getContent(), null);
    }
}