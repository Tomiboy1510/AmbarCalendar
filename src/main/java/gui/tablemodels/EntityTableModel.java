package gui.tablemodels;

import javax.swing.table.AbstractTableModel;
import java.util.stream.IntStream;

public abstract class EntityTableModel extends AbstractTableModel {

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

    public int getColumnIndex(String columnName) {
        return IntStream.range(0, columnNames.length)
                .filter(i -> columnNames[i].equals(columnName))
                .findFirst()
                .orElse(-1);
    }

    public abstract Object getEntityAtRow(int rowIndex);
}
