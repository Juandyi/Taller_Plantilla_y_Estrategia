package templatemethod;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

public class PDFDataMiner extends DataMiner {

    @Override
    protected RawData extractData(File file) throws Exception {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            return new RawData(text);
        }
    }

    @Override
    protected Data parseData(RawData rawData) {
        return new Data(rawData.getContent(), null);
    }
}