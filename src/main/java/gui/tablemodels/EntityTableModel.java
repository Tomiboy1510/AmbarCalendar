package gui.tablemodels;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.stream.IntStream;

/**
 * Generic table model to manage entities and show them in a {@link JTable}.
 */
public abstract class EntityTableModel extends AbstractTableModel {

    /**
     * Array of column names (order matters!)
     */
    private final String[] columnNames;

    public EntityTableModel(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Returns the column index corresponding to a column name
     * @param columnName the column name to find the index of
     * @return the column index of the column with the given name or {@code -1} if not found
     */
    public int getColumnIndex(String columnName) {
        return IntStream.range(0, columnNames.length)
                .filter(i -> columnNames[i].equals(columnName))
                .findFirst()
                .orElse(-1);
    }

    /**
     * Returns the entity at a given row
     * @param rowIndex the row index of the entity to be returned
     * @return the entity corresponding to the given row index
     */
    public abstract Object getEntityAtRow(int rowIndex);
}
