package gui.tablemodels;

import entity.AbstractEntity;
import persistence.Subscriber;
import persistence.dao.StandaloneEntityDAO;

import javax.swing.*;
import java.util.List;

public abstract class StandaloneEntityTableModel<T extends AbstractEntity>
        extends EntityTableModel implements Subscriber {

    protected List<T> data;
    protected final StandaloneEntityDAO<T> dao;

    private final int pageSize;
    private int currentPage, maxPage;

    protected boolean sortAscending = true;
    protected int sortingColumn = 0;

    public StandaloneEntityTableModel(StandaloneEntityDAO<T> dao, String[] columnNames, int pageSize) {
        super(columnNames);

        this.dao = dao;
        dao.subscribe(this);
        this.pageSize = pageSize;
        currentPage = 1;

        refresh();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public void refresh() {
        maxPage = ((int) Math.ceil((double) dao.getCount() / pageSize));
        if (maxPage == 0)
            maxPage = 1;
        List<T> newData = dao.getAll(currentPage, pageSize);
        data = newData == null ? data : newData;
        fireTableDataChanged();
    }

    @Override
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

    public void previousPage() {
        if (currentPage <= 1)
            return;
        currentPage --;
        refresh();
    }

    public void nextPage() {
        if (currentPage >= maxPage)
            return;
        currentPage ++;
        refresh();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public abstract void sortByColumn(int columnIndex);
}
