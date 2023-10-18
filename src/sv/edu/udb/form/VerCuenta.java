package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerCuenta extends JFrame{
    private JLabel lblVerCuenta;
    private JComboBox cmbCuentas;
    private JLabel lblCuentas;
    private JTable tblDatos;
    private JButton btnConsultar;
    private JButton btnMenu;
    private JPanel pnlVerCuenta;

    CuentasDatos cuentasDatos = new CuentasDatos();
    DefaultTableModel modelo = null;

    public VerCuenta(ClienteBeans clienteBeans) {

        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlVerCuenta);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(getParent());
        cmbCuentas.setModel(cuentasDatos.selectCuentas(clienteBeans));


        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




                JFrame menu = new Menu(clienteBeans);
                menu.setVisible(true);
            }
        });
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Usar SQL_SELECT_CUENTA
                modelo = cuentasDatos.selectCuentatbl(cmbCuentas.getSelectedItem().toString());
                tblDatos.setModel(modelo);
            }
        });
    }
}
