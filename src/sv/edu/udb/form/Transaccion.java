package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;
import sv.edu.udb.datos.TransacionesDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaccion extends JFrame{
    private JComboBox cbmCuentasTransaccion;
    private JButton btnObtenerTransaccion;
    private JTable tblDatosTransaccion;
    private JPanel pnlTransacciones;
    private JButton btnMenu;

    DefaultTableModel modelo = null;

    TransacionesDatos transacionesDatos = new TransacionesDatos();

    CuentasDatos cuentasDatos = new CuentasDatos();

    public Transaccion(ClienteBeans clienteBeans) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlTransacciones);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(getParent());

        cbmCuentasTransaccion.setModel(cuentasDatos.selectCuentas(clienteBeans));
        btnObtenerTransaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cbmCuentasTransaccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblDatosTransaccion.setModel(transacionesDatos.selectTransactions(cbmCuentasTransaccion.getSelectedItem().toString()));
            }
        });
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu(clienteBeans);
                menu.setVisible(true);
                dispose();
            }
        });
    }


}
