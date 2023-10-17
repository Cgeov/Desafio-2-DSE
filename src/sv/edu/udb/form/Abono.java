package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Abono extends JFrame{
    private JTextField txtAbonoAmount;
    private JComboBox cmbCuentaAbono;
    private JButton btnAbonar;
    private JPanel jpnAbono;
    CuentasDatos cuentasDatos = new CuentasDatos();
    public Abono(ClienteBeans clienteBeans) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(jpnAbono);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(getParent());
        cmbCuentaAbono.setModel(cuentasDatos.selectCuentas(clienteBeans));
        btnAbonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float saldo = cuentasDatos.getsaldo(cmbCuentaAbono.getSelectedItem().toString());
                if(Float.parseFloat(txtAbonoAmount.getText()) <  0){
                    JOptionPane.showMessageDialog(null,"El Monto a abonar debe ser mayor a 0");
                }else{
                    float saldoNow = saldo + Float.parseFloat(txtAbonoAmount.getText());;
                    cuentasDatos.update(cmbCuentaAbono.getSelectedItem().toString(),saldoNow);
                    JOptionPane.showMessageDialog(null,"Se realizó un abono por $" + Float.parseFloat(txtAbonoAmount.getText()) + " su nuevo saldo es de: $"+ saldoNow);
                }
            }
        });
    }
}
