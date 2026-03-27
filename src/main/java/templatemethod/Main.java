package templatemethod;

import javax.swing.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        while (true) {
            File file = chooseFile();
            if (file == null) {
                // usuario canceló
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                return;
            }

            try {
                DataMiner miner = buildMiner(file);
                miner.mine(file.getAbsolutePath());
                JOptionPane.showMessageDialog(null, "Procesado correctamente.");
                return;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al procesar el archivo:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                // vuelve al inicio del loop para pedir otro archivo
            }
        }
    }

    private static File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona un archivo .docx, .pdf o .csv");
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private static DataMiner buildMiner(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".csv")) return new CSVDataMiner();
        if (name.endsWith(".pdf")) return new PDFDataMiner();
        if (name.endsWith(".docx")) return new DocxDataMiner();
        throw new IllegalArgumentException("Tipo de archivo no soportado: " + name);
    }
}