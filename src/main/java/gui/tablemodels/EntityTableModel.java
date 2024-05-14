package gui.tablemodels;

import persistence.Subscriber;
import persistence.dao.HibernateDAO;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityTableModel<T> extends AbstractTableModel implements Subscriber {

    protected List<T> data;
    protected HibernateDAO<T> dao;
    protected String[] columnNames;
    private final TableRowSorter<TableModel> sorter;
    private boolean ascending = true;

    public EntityTableModel(HibernateDAO<T> dao) {
        this.dao = dao;
        dao.subscribe(this);
        refresh();
        sorter = new TableRowSorter<>(this);
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

    public void save(T entity) {
        try {
            dao.save(entity);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(T entity) {
        try {
            dao.update(entity);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete(int rowIndex) {
        T entity = data.get(rowIndex);
        try {
            dao.delete(entity);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public HibernateDAO<T> getDao() {
        return dao;
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
