package sv.edu.udb.form;

import com.mysql.cj.util.StringInspector;
import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.datos.CuentasDatos;
import sv.edu.udb.datos.TransacionesDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Abono extends JFrame{
    private JTextField txtAbonoAmount;
    private JComboBox cmbCuentaAbono;
    private JButton btnAbonar;
    private JPanel jpnAbono;
    private JButton btnMenu;
    CuentasDatos cuentasDatos = new CuentasDatos();

    TransacionesDatos transacionesDatos = new TransacionesDatos();

    String tipo_Transaccion = "Abono";
    public Abono(ClienteBeans clienteBeans) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpnAbono);
        this.setMinimumSize(new Dimension(400, 500));
        this.setLocationRelativeTo(getParent());
        cmbCuentaAbono.setModel(cuentasDatos.selectCuentas(clienteBeans));

        btnAbonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float saldo = cuentasDatos.getsaldo(cmbCuentaAbono.getSelectedItem().toString());
                String strmonto = txtAbonoAmount.getText();
                boolean HasNoNumber = strmonto.matches("^[^0-9]*$");

                if(strmonto.isEmpty() || HasNoNumber){
                    JOptionPane.showMessageDialog(null, "Los datos no pueden ser vacíos ni letras");
                }else {
                    float monto = Float.parseFloat(txtAbonoAmount.getText());
                    if (monto<0){
                        JOptionPane.showMessageDialog(null,"El Monto a abonar debe ser mayor a 0");
                    }else{
                        float saldoNow = saldo + monto;
                        cuentasDatos.update(cmbCuentaAbono.getSelectedItem().toString(),saldoNow);
                        transacionesDatos.insert(cmbCuentaAbono.getSelectedItem().toString(), saldo, monto, tipo_Transaccion);
                        JOptionPane.showMessageDialog(null,"Se realizó un abono por $" + monto + " su nuevo saldo es de: $"+ saldoNow);
                        JFrame menu = new Menu(clienteBeans);
                        menu.setVisible(true);
                        dispose();
                    }
                }
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


