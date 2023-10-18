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

                String strmonto = txtRetiroAmount.getText();

                boolean HasNoNumber = strmonto.matches("^[^0-9]*$");

                //Validación no letras ni cadena vacía
                if (strmonto.isEmpty() || HasNoNumber){
                    JOptionPane.showMessageDialog(null, "Los datos no pueden ser vacíos ni letras");
                }else {
                    float monto = Float.parseFloat(txtRetiroAmount.getText());
                    if(( monto >  saldo)){
                        JOptionPane.showMessageDialog(null,"El Monto a retirar debe ser menor al Saldo de la cuenta, revise los datos ingresados");
                    }else{
                        float saldoNow = saldo - monto;;
                        cuentasDatos.update(cmbCuentasRetiro.getSelectedItem().toString(),saldoNow);

                        JOptionPane.showMessageDialog(null,"Se realizó un retiro por $" + monto + " su nuevo saldo es de: $"+ saldoNow);

                        //Regresando al menu
                        JFrame menu = new Menu(clienteBeans);
                        menu.setVisible(true);
                        dispose();


                    }
                }
            }
        });
    }
}
