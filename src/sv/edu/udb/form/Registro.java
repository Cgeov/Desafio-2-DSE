package sv.edu.udb.form;

import sv.edu.udb.beans.ClienteBeans;
import sv.edu.udb.datos.ClientesDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Registro extends JFrame{
    private JTextField txtNombreCreacion;
    private JTextField txtDUICreacion;
    private JButton btnCrearCliente;
    private JPanel pnlRegistro;

    ClienteBeans clienteBeans = new ClienteBeans();
    ClientesDatos clientesDatos = new ClientesDatos();

    public Registro() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(pnlRegistro);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(getParent());
        btnCrearCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionCliente();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new Registro();
        frame.setVisible(true);
    }

    private void adicionCliente() {
        int id=0 ;
        String titular;
        String DUI;
        int PIN;

        titular = txtNombreCreacion.getText();
        DUI = txtDUICreacion.getText();
        PIN =  Integer.parseInt(String.format("%04d", generarNumeroAleatorio()));

        clienteBeans.setIdCliente(id);
        clienteBeans.setTitularCliente(titular);
        clienteBeans.setDUICliente(DUI);
        clienteBeans.setPINCliente(PIN);

        clientesDatos.insert(clienteBeans);
    }

    public int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(10000);
    }
}
