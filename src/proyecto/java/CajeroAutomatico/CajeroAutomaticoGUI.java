package proyecto.java.CajeroAutomatico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CajeroAutomaticoGUI {
    private double saldo;
    private JFrame frame;
    private JTextField cantidadField;
    private JTextArea displayArea;

    public CajeroAutomaticoGUI(double saldoInicial) {
        this.saldo = saldoInicial;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Cajero Automático");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblSaldo = new JLabel("Saldo Actual: S/ " + saldo);
        lblSaldo.setBounds(30, 20, 200, 25);
        frame.add(lblSaldo);

        JLabel lblCantidad = new JLabel("Ingrese cantidad:");
        lblCantidad.setBounds(30, 60, 150, 25);
        frame.add(lblCantidad);

        cantidadField = new JTextField();
        cantidadField.setBounds(150, 60, 200, 25);
        frame.add(cantidadField);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setBounds(30, 100, 150, 25);
        btnDepositar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositar();
                lblSaldo.setText("Saldo Actual: S/ " + saldo);
            }
        });
        frame.add(btnDepositar);

        JButton btnRetirar = new JButton("Retirar");
        btnRetirar.setBounds(200, 100, 150, 25);
        btnRetirar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retirar();
                lblSaldo.setText("Saldo Actual: S/ " + saldo);
            }
        });
        frame.add(btnRetirar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(150, 150, 100, 25);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.add(btnSalir);

        displayArea = new JTextArea();
        displayArea.setBounds(30, 190, 340, 50);
        displayArea.setEditable(false);
        frame.add(displayArea);

        frame.setVisible(true);
    }

    private void depositar() {
        try {
            double cantidad = Double.parseDouble(cantidadField.getText());
            if (cantidad > 0) {
                saldo += cantidad;
                displayArea.setText("Ha depositado: S/ " + cantidad);
            } else {
                displayArea.setText("Ingrese una cantidad válida para depositar.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Error: Ingrese un número válido.");
        }
        cantidadField.setText("");
    }

    private void retirar() {
        try {
            double cantidad = Double.parseDouble(cantidadField.getText());
            if (cantidad > 0 && cantidad <= saldo) {
                saldo -= cantidad;
                displayArea.setText("Ha retirado: S/ " + cantidad);
            } else if (cantidad > saldo) {
                displayArea.setText("Saldo insuficiente.");
            } else {
                displayArea.setText("Ingrese una cantidad válida para retirar.");
            }
        } catch (NumberFormatException e) {
            displayArea.setText("Error: Ingrese un número válido.");
        }
        cantidadField.setText("");
    }

    public static void main(String[] args) {
        new CajeroAutomaticoGUI(1000.00); 
    }
}
