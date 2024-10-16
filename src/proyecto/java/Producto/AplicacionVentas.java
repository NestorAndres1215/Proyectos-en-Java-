import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AplicacionVentas extends JFrame {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JCheckBox chkIGV;
    private JTextField txtDescuento;
    private JTextField txtCantidad;
    private JTable tablaProductos;
    private JLabel lblTotal;

    private Venta venta = new Venta();
    private DefaultTableModel modeloTabla;

    public AplicacionVentas() {
        setTitle("Aplicación de Ventas");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de entrada de productos
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new BoxLayout(panelEntrada, BoxLayout.Y_AXIS));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        chkIGV = new JCheckBox("Incluir IGV (18%)");
        txtDescuento = new JTextField();
        txtCantidad = new JTextField();

        panelEntrada.add(new JLabel("Nombre del Producto:"));
        panelEntrada.add(txtNombre);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(new JLabel("Precio:"));
        panelEntrada.add(txtPrecio);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(chkIGV);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(new JLabel("Descuento (%):"));
        panelEntrada.add(txtDescuento);
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(new JLabel("Cantidad:"));
        panelEntrada.add(txtCantidad);

        // Botón para agregar producto
        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
        panelEntrada.add(Box.createVerticalStrut(10));
        panelEntrada.add(btnAgregar);

        // Modelo de tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Precio", "Descuento", "Cantidad", "IGV", "Precio Final"}, 0);
        tablaProductos = new JTable(modeloTabla);

        // Panel inferior con el total de la venta
        JPanel panelTotal = new JPanel(new BorderLayout());
        lblTotal = new JLabel("Total: 0.00");
        panelTotal.add(lblTotal, BorderLayout.EAST);

        // Agregar los paneles a la ventana
        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);
        add(panelTotal, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarProducto() {
        String nombre = txtNombre.getText();
        double precio = 0;
        double descuento = 0;
        int cantidad = 0;
        boolean conIGV = chkIGV.isSelected();

        try {
            precio = Double.parseDouble(txtPrecio.getText());
            descuento = Double.parseDouble(txtDescuento.getText());
            cantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para el precio, descuento y cantidad.");
            return;
        }

        Producto producto = new Producto(nombre, precio, conIGV, descuento, cantidad);
        venta.agregarProducto(producto);

        // Actualizar tabla y total
        actualizarVista();
        limpiarCampos();
    }

    private void actualizarVista() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);

        // Agregar productos a la tabla
        for (Producto p : venta.getProductos()) {
            modeloTabla.addRow(new Object[]{
                    p.getNombre(),
                    String.format("%.2f", p.getPrecio()),
                    String.format("%.2f", p.getDescuento()) + "%",
                    p.getCantidad(),
                    p.isConIGV() ? "Sí" : "No",
                    String.format("%.2f", p.getPrecioFinal())
            });
        }

        // Calcular y mostrar el total
        double total = venta.calcularTotal();
        lblTotal.setText(String.format("Total: %.2f", total));
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtDescuento.setText("");
        txtCantidad.setText("");
        chkIGV.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicacionVentas();
            }
        });
    }
}