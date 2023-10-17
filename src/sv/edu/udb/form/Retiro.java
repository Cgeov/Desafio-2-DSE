package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.CuentasDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Retiro extends JFrame {

    private JComboBox cmbCuentasRetiro;
    private JTextField txtRetiroAmount;
    private JButton btnRetirar;
    private JPanel pnlRetiro;

    CuentasDatos cuentasDatos = new CuentasDatos();

    public Retiro(ClienteBeans clienteBeans) {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlRetiro);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(getParent());
        cmbCuentasRetiro.setModel(cuentasDatos.selectCuentas(clienteBeans));
        btnRetirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float saldo = cuentasDatos.getsaldo(cmbCuentasRetiro.getSelectedItem().toString());
                if(Float.parseFloat(txtRetiroAmount.getText()) >  saldo){
                    JOptionPane.showMessageDialog(null,"El Monto a retirar debe ser menor al Saldo de la cuenta");
                }else{
                    float saldoNow = saldo - Float.parseFloat(txtRetiroAmount.getText());;
                    cuentasDatos.update(cmbCuentasRetiro.getSelectedItem().toString(),saldoNow);
                    JOptionPane.showMessageDialog(null,"Se realizó un retiro por $" + Float.parseFloat(txtRetiroAmount.getText()) + " su nuevo saldo es de: $"+ saldoNow);
                }
            }
        });
    }
}
