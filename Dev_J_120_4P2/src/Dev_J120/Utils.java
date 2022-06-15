
package Dev_J120;

import java.awt.Window;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class Utils {
    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
        double... percentages) {
    double total = 0;
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
        total += percentages[i];
    } 
    for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
        TableColumn column = table.getColumnModel().getColumn(i);
        column.setPreferredWidth((int)
                (tablePreferredWidth * (percentages[i] / total)));
        }
    }
    public static void alignCenter(JTable table, int column) {
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
         centerRenderer.setHorizontalAlignment(JLabel.CENTER);
         table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer); 
    }
    public static Window findLatestWindow() {
        Window result = null;
        for (Window w : Window.getWindows()) {
            if (w.isVisible()) {
                result = w;
            }
        }
        return result;
    }
    
}
