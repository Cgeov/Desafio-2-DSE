package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.ClientesDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
    private JTextField txtDUILogin;
    private JPasswordField txtPINLogin;
    private JButton btnIniciarSesionLogin;
    private JButton btnCrearUsuarioLogin;
    private JPanel pnlLogin;
    ClienteBeans clienteBeans = new ClienteBeans();
    ClientesDatos clientesDatos = new ClientesDatos();
    public Login() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlLogin);
        this.setMinimumSize(new Dimension(350, 450));
        this.setLocationRelativeTo(getParent());
        btnIniciarSesionLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dui = txtDUILogin.getText();
                String pin = txtPINLogin.getText();
                if (dui.isEmpty() || pin.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");
                }else {
                    validateClient();
                    dispose();
                }


            }
        });
        btnCrearUsuarioLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registro = new Registro();
                registro.setVisible(true);
                dispose();
            }
        });
    }


    private void validateClient() {
        clienteBeans.setDUICliente(txtDUILogin.getText());
        clienteBeans.setPINCliente(Integer.parseInt(txtPINLogin.getText()));

            if (clientesDatos.select(clienteBeans)) {
                JOptionPane.showMessageDialog(null,"Bienvenido " + clienteBeans.getTitularCliente());
                JFrame cuenta = new Menu(clienteBeans);
                cuenta.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,"Inicio de sesión fallido. Verifique los datos de inicio de sesión.");
            }

    }

    public static void main(String[] args) {
        JFrame frame = new Login();
        frame.setVisible(true);
    }
}
