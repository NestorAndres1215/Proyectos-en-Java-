package proyecto.java.IMC;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplicacionIMC extends JFrame {
    private JTextField txtNombre;
    private JTextField txtAltura;
    private JTextField txtPeso;
    private JTable tablaPersonas;
    private JLabel lblPromedioIMC;

    private Registro registro = new Registro();
    private DefaultTableModel modeloTabla;

    public AplicacionIMC() {
        setTitle("Aplicación de IMC");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada de personas
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.Y_AXIS));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtNombre = new JTextField();
        txtAltura = new JTextField();
        txtPeso = new JTextField();

        panelEntrada.add(new JLabel("Nombre:"));
        panelEntrada.add(txtNombre);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(new JLabel("Altura (m):"));
        panelEntrada.add(txtAltura);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(new JLabel("Peso (kg):"));
        panelEntrada.add(txtPeso);

        // Botón para agregar persona
        JButton btnAgregar = new JButton("Agregar Persona");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(btnAgregar);

        // Modelo de tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Altura", "Peso", "IMC", "Clasificación"}, 0);
        tablaPersonas = new JTable(modeloTabla);

        // Panel inferior con el promedio de IMC
        JPanel panelPromedio = new JPanel(new BorderLayout());
        lblPromedioIMC = new JLabel("Promedio IMC: 0.00");
        panelPromedio.add(lblPromedioIMC, BorderLayout.EAST);

        // Agregar los paneles a la ventana
        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(tablaPersonas), BorderLayout.CENTER);
        add(panelPromedio, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarPersona() {
        String nombre = txtNombre.getText();
        double altura = 0;
        double peso = 0;

        try {
            altura = Double.parseDouble(txtAltura.getText());
            peso = Double.parseDouble(txtPeso.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para altura y peso.");
            return;
        }

        Persona persona = new Persona(nombre, altura, peso);
        registro.agregarPersona(persona);

        // Actualizar tabla y promedio
        actualizarVista();
        limpiarCampos();
    }

    private void actualizarVista() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Agregar personas a la tabla
        for (Persona p : registro.getPersonas()) {
            modeloTabla.addRow(new Object[]{
                    p.getNombre(),
                    String.format("%.2f", p.getAltura()),
                    String.format("%.2f", p.getPeso()),
                    String.format("%.2f", p.getImc()),
                    p.clasificarIMC() // Añadir la clasificación del IMC
            });
        }

        // Calcular y mostrar el promedio de IMC
        double promedioIMC = registro.calcularPromedioIMC();
        lblPromedioIMC.setText(String.format("Promedio IMC: %.2f", promedioIMC));
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionIMC();
            }
        });
    }
}