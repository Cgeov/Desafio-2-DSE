package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VerCuentas extends JFrame {
    private JLabel lblVerTodasCuentas;
    private JTable tblCuentas;
    private JButton btnMenu;
    private JScrollPane scrollTable;
    private JPanel pnlVerCuentas;

    CuentasDatos cuentasDatos = new CuentasDatos();
    DefaultTableModel modelo = null;

    public VerCuentas(ClienteBeans clienteBeans){
        super();

        int id_cliente = clienteBeans.getIdCliente();
        //JOptionPane.showMessageDialog(null, clienteBeans.getIdCliente());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlVerCuentas);
        this.setMinimumSize(new Dimension(600, 300));
        this.setLocationRelativeTo(getParent());

        tblCuentas.setModel(cuentasDatos.selectAll(id_cliente));

        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menu = new Menu(clienteBeans);
                menu.setVisible(true);
            }
        });
    }
}
