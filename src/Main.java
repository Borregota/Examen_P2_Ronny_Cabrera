import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpiderverseUI ventana = new SpiderverseUI();
            ventana.setVisible(true);
        });
    }
}
