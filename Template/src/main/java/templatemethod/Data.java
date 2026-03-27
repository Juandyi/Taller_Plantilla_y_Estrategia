package templatemethod;

import java.util.List;

public class Data {
    private final String content;
    private final List<String[]> rows;

    public Data(String content, List<String[]> rows) {
        this.content = content;
        this.rows = rows;
    }

    public String getContent() {
        return content;
    }

    public List<String[]> getRows() {
        return rows;
    }
}