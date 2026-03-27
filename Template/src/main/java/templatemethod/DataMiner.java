package templatemethod;

import java.io.File;

public abstract class DataMiner {

    public final void mine(String path) {
        File file = null;
        try {
            file = openFile(path);
            RawData rawData = extractData(file);
            Data data = parseData(rawData);
            Analysis analysis = analyzeData(data);
            sendReport(analysis);
            closeFile(file);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo procesar el archivo: " + e.getMessage(), e);
        }
    }

    protected File openFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("Archivo no encontrado: " + path);
        }
        if (!file.isFile()) {
            throw new RuntimeException("La ruta no es un archivo válido: " + path);
        }
        return file;
    }

    protected abstract RawData extractData(File file) throws Exception;
    protected abstract Data parseData(RawData rawData) throws Exception;

    protected Analysis analyzeData(Data data) {
        String content = data.getContent();
        int chars = content.length();
        int words = content.trim().isEmpty() ? 0 : content.trim().split("\\s+").length;
        int lines = content.isEmpty() ? 0 : content.split("\\R").length;
        int rows = (data.getRows() == null) ? 0 : data.getRows().size();

        String summary = "Reporte Básico:\n" +
                "- Caracteres: " + chars + "\n" +
                "- Palabras: " + words + "\n" +
                "- Líneas: " + lines + "\n" +
                "- Filas CSV: " + rows;

        return new Analysis(summary);
    }

    protected void sendReport(Analysis analysis) {
        System.out.println("=== REPORTE ===");
        System.out.println(analysis.getSummary());
        System.out.println("===============");
    }

    protected void closeFile(File file) {
        // No-op (se mantiene por el patrón)
    }
}