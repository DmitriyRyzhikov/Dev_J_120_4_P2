
package Dev_J120;

import java.awt.Window;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class Utils {
    //метод задает ширину для каждого столбца таблицы
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
    //метод выравнивает текст в нужном столбце таблицы по центру
    public static void alignCenter(JTable table, int column) {
         DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
         centerRenderer.setHorizontalAlignment(JLabel.CENTER);
         table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer); 
    }
    //метод ищет последнее открытое окно
    public static Window findLatestWindow() {
        Window result = null;
        for (Window w : Window.getWindows()) {
            if (w.isVisible()) {
                result = w;
            }
        }
        return result;
    }
    /*метод удаляет маркер последовательности байтов (если он есть) из текстового файла 
    с сохраненными данными. BOM может появиться, если попытаться отредактировать файл через 
    какой-нибудь редактор.
    */
    public static List<String> killerBOM(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (char character : list.get(0).toCharArray()) { 
            if (character != '\uFEFF') 
                sb.append(character); 
        }  
        list.remove(0);
        list.add(0, sb.toString());
    return list;
    }   
}
