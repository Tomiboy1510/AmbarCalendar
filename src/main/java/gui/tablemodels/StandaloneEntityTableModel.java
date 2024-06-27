package gui.tablemodels;

import entity.AbstractEntity;
import persistence.Subscriber;
import persistence.dao.StandaloneEntityDAO;

import javax.swing.*;
import java.util.List;

/**
 * Generic table model to manage "standalone entities" and show them in a {@link JTable}.
 * (standalone entities are those that can be created independently of other entities)
 * @param <T> the type of the entity, which must extend {@link AbstractEntity}
 */
@SuppressWarnings("CallToPrintStackTrace")
public abstract class StandaloneEntityTableModel<T extends AbstractEntity>
        extends EntityTableModel implements Subscriber {

    /**
     * List of entities
     */
    protected List<T> data;

    /**
     * DAO to manage database operations on entities
     */
    protected final StandaloneEntityDAO<T> dao;

    /**
     * Quantity of rows shown in each page
     */
    private final int pageSize;

    /**
     * Current page number
     */
    private int currentPage;

    /**
     * Maximum page number
     */
    private int maxPage;

    /**
     * Use ascending order when sorting?
     */
    protected boolean sortAscending = true;

    /**
     * Column index of the column used for sorting
     */
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
        // Calculate max page number dividing quantity of entities by page size
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

    /**
     * Delete the entity at the given row index
     * @param rowIndex the row index of the entity to be deleted
     */
    public void delete(int rowIndex) {
        T entity = data.get(rowIndex);
        try {
            dao.delete(entity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Decrease page number by one
     */
    public void previousPage() {
        if (currentPage <= 1)
            return;
        currentPage --;
        refresh();
    }

    /**
     * Increase page number by one
     */
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

    /**
     * Sort table by the data at the given column index
     * @param columnIndex the index of the column to sort by
     */
    public abstract void sortByColumn(int columnIndex);
}
