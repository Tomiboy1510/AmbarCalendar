package gui.tablepanels;

import entity.Venta;
import gui.MultiLineTableCellRenderer;
import gui.forms.VentaForm;
import gui.tablemodels.VentaTableModel;
import persistence.dao.ProductoDAO;
import persistence.dao.VentaDAO;

import javax.swing.table.TableColumnModel;

/**
 * GUI component used for displaying product sales ({@link Venta}) and allowing the user to
 * create new ones and modify or delete existing ones.
 */
public class VentaTablePanel extends StandaloneEntityTablePanel<Venta> {

    public VentaTablePanel(VentaDAO ventaDAO, ProductoDAO productoDAO) {
        super("Ventas", new VentaTableModel(ventaDAO));

        // Show form for new product sale
        addButton.addActionListener(_ -> new VentaForm(ventaDAO, productoDAO));

        // Does not allow modifying (updating stock values would be a pain in the ass)
        modifyButton.setVisible(false);

        VentaTableModel tableModel = (VentaTableModel) table.getModel();
        TableColumnModel columnModel = table.getTableHeader().getColumnModel();

        // Set multiline cell renderer for the "Items" column (show each item in a different line)
        columnModel.getColumn(tableModel.getColumnIndex("Items")).setCellRenderer(new MultiLineTableCellRenderer());
    }
}