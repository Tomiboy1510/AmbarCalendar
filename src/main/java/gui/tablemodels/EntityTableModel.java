package gui.tablemodels;

import entity.AbstractEntity;
import persistence.Subscriber;
import persistence.dao.HibernateDAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityTableModel<T extends AbstractEntity>
        extends AbstractTableModel implements Subscriber {

    protected List<T> data;
    protected final HibernateDAO<T> dao;
    private final String[] columnNames;
    private final TableRowSorter<TableModel> sorter;
    private boolean ascending = true;

    public EntityTableModel(HibernateDAO<T> dao, String[] columnNames) {
        this.dao = dao;
        dao.subscribe(this);
        refresh();
        sorter = new TableRowSorter<>(this);
        this.columnNames = columnNames;
    }

    @Override
    public void refresh() {
        data = dao.getAll();
        fireTableDataChanged();
    }

    public void sortByColumn(int columnIndex) {
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(
                        columnIndex,
                        ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING
                ));
        sorter.setSortKeys(sortKeys);
        ascending = (! ascending);
    }

    public TableRowSorter<TableModel> getSorter() {
        return sorter;
    }

    public T getEntityAtRow(int rowIndex) {
        return data.get(rowIndex);
    }

    public void delete(int rowIndex) {
        T entity = data.get(rowIndex);
        try {
            dao.delete(entity);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
