package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton btnAnadirCuentaMenu;
    private JButton btnShowRetirarCuenta;
    private JButton btnShowAbonoCuenta;
    private JButton verCuentaEspecificaButton;
    private JButton verTodasMisCuentasButton;
    private JButton verTransaccionesButton;
    private JButton salirButton;
    private JPanel pnlMenu;
    ClienteBeans clienteBeans = new ClienteBeans();
    CuentaBeans cuentaBeans = new CuentaBeans();
    CuentasDatos cuentasDatos = new CuentasDatos();
    public Menu(ClienteBeans clienteBeansAlredy) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlMenu);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(getParent());
        clienteBeans = clienteBeansAlredy;
        btnAnadirCuentaMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCuenta();
            }
        });
        btnShowRetirarCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame retiro = new Retiro(clienteBeans);
                retiro.setVisible(true);
            }
        });
        btnShowAbonoCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame abono = new Abono(clienteBeans);
                abono.setVisible(true);
            }
        });
    }

    private void crearCuenta(){
        String id;
        int id_cliente;
        String id_cuenta = "";

        String last = validateExists();
        if(last.isEmpty()){
            id_cuenta = "C-00" + "-" + clienteBeans.getDUICliente().substring(1,6) + "-01";
        }else{
            id_cuenta = "C-00" + "-" + clienteBeans.getDUICliente().substring(1,6) + "-0" + (Integer.parseInt(String.valueOf(last.charAt(last.length()-1))) +1);
        }

        cuentaBeans.setIdCuenta(id_cuenta);
        cuentaBeans.setIdClienteCuenta(clienteBeans.getIdCliente());
        cuentaBeans.setSaldoCuenta(0);

        cuentasDatos.insert(cuentaBeans);
        JOptionPane.showMessageDialog(null,"N° " + id_cuenta + " Creada exitosamente");
    }


    private String validateExists(){
        String last = "";
        DefaultTableModel dtm = cuentasDatos.selectAll(clienteBeans.getIdCliente());

        if (dtm.getRowCount() > 0) {
            int lastRowIndex = dtm.getRowCount() - 1;
                Object value = dtm.getValueAt(lastRowIndex, 0);
                last = value.toString();
        }

        return last;
    }


}
