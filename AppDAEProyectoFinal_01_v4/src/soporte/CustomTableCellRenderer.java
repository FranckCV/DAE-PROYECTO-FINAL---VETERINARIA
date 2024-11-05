/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soporte;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author franc
 */
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private static class Condition {
        int columnIndex;
        String searchText;
        Color highlightColor;

        public Condition(int columnIndex, String searchText, Color highlightColor) {
            this.columnIndex = columnIndex;
            this.searchText = searchText;
            this.highlightColor = highlightColor;
        }
    }

    private final List<Condition> conditions = new ArrayList<>();

    // Método para agregar una condición
    public void addCondition(int columnIndex, String searchText, String hexColor) {
        conditions.add(new Condition(columnIndex, searchText, Color.decode(hexColor)));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Fondo predeterminado
        Color defaultBackground = isSelected ? table.getSelectionBackground() : Color.WHITE;
        cell.setBackground(defaultBackground);

        // Verifica todas las condiciones
        for (Condition condition : conditions) {
            String cellValue = table.getValueAt(row, condition.columnIndex).toString();
            if (cellValue.contains(condition.searchText)) {
                cell.setBackground(condition.highlightColor); // Aplica el color si coincide
                break; // Detiene el ciclo una vez que se cumple una condición
            }
        }

        return cell;
    }
    
    public static void setCustomRenderer(JTable table, Object[][] conditions) {
        CustomTableCellRenderer renderer = new CustomTableCellRenderer();

        // Añade cada condición del arreglo
        for (Object[] condition : conditions) {
            int columnIndex = (int) condition[0];
            String searchText = (String) condition[1];
            String hexColor = (String) condition[2];
            renderer.addCondition(columnIndex, searchText, hexColor);
        }

        // Aplica el renderizador a todas las columnas
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }
    
}
