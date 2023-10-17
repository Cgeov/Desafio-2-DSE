package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Abono extends JFrame{
    private JTextField txtAbonoAmount;
    private JComboBox cmbCuentaAbono;
    private JButton btnAbonar;
    CuentasDatos cuentasDatos = new CuentasDatos();
    public Abono(ClienteBeans clienteBeans) {
        btnAbonar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float saldo = cuentasDatos.getsaldo(cmbCuentaAbono.getSelectedItem().toString());
                if(Float.parseFloat(txtAbonoAmount.getText()) >  saldo){
                    JOptionPane.showMessageDialog(null,"El Monto a retirar debe ser menor al Saldo de la cuenta");
                }else{
                    float saldoNow = saldo + Float.parseFloat(txtAbonoAmount.getText());;
                    cuentasDatos.update(cmbCuentaAbono.getSelectedItem().toString(),saldoNow);
                    JOptionPane.showMessageDialog(null,"Se realizó un retiro por $" + Float.parseFloat(txtAbonoAmount.getText()) + " su nuevo saldo es de: $"+ saldoNow);
                }
            }
        });
    }
}
