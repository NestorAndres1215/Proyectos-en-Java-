package proyecto.java.ImpuestoRenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculoImpuestoRentaGUI extends JFrame {

    public CalculoImpuestoRentaGUI() {
        // Configuración del JFrame
        setTitle("Cálculo de Impuesto sobre la Renta en Soles");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear la tabla de tramos impositivos
        String[] columnNames = {"Renta (S/)", "Tipo Impositivo"};
        Object[][] data = {
            {"Menos de S/ 10,000", "5%"},
            {"Entre S/ 10,000 y S/ 20,000", "15%"},
            {"Entre S/ 20,000 y S/ 35,000", "20%"},
            {"Entre S/ 35,000 y S/ 60,000", "30%"},
            {"Más de S/ 60,000", "45%"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Agregar la tabla al JFrame
        add(scrollPane, BorderLayout.CENTER);

        // Panel para la entrada de datos
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel rentaLabel = new JLabel("Introduce tu renta anual en soles:");
        JTextField rentaField = new JTextField(10);
        JButton calcularButton = new JButton("Calcular Tipo Impositivo");
        JLabel resultLabel = new JLabel("");

        inputPanel.add(rentaLabel);
        inputPanel.add(rentaField);
        inputPanel.add(calcularButton);

        // Agregar el panel de entrada al JFrame
        add(inputPanel, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.NORTH);

        // Action Listener para el botón
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rentaStr = rentaField.getText();
                try {
                    double renta = Double.parseDouble(rentaStr);
                    double tipoImpositivo = calcularTipoImpositivo(renta);
                    resultLabel.setText("Tu tipo impositivo es: " + tipoImpositivo + "%");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
                }
            }
        });
    }

    // Método para calcular el tipo impositivo según la renta
    private double calcularTipoImpositivo(double renta) {
        if (renta < 10000) {
            return 5;
        } else if (renta >= 10000 && renta < 20000) {
            return 15;
        } else if (renta >= 20000 && renta < 35000) {
            return 20;
        } else if (renta >= 35000 && renta < 60000) {
            return 30;
        } else {
            return 45;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculoImpuestoRentaGUI frame = new CalculoImpuestoRentaGUI();
            frame.setVisible(true);
        });
    }
}
