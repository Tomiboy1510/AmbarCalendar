package gui.tablepanels;

import entity.Venta;
import gui.MultiLineTableCellRenderer;
import gui.forms.VentaForm;
import gui.tablemodels.VentaTableModel;
import persistence.dao.ProductoDAO;
import persistence.dao.VentaDAO;

import javax.swing.table.TableColumnModel;

public class VentaTablePanel extends EntityTablePanel<Venta> {

    public VentaTablePanel(VentaDAO ventaDAO, ProductoDAO productoDAO) {
        super("Ventas", new VentaTableModel(ventaDAO));

        addButton.addActionListener(_ -> new VentaForm(ventaDAO, productoDAO));

        modifyButton.setVisible(false);

        VentaTableModel tableModel = (VentaTableModel) table.getModel();
        TableColumnModel columnModel = table.getTableHeader().getColumnModel();
        columnModel.getColumn(tableModel.getColumnIndex("Items")).setCellRenderer(new MultiLineTableCellRenderer());
    }
}