package proyecto.java.Notas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotasApp extends JFrame {

    // Componentes de la interfaz
    private JTextField txtNombre, txtApellido, txtCurso, txtExamen1, txtExamen2, txtExamen3, txtExamen4;
    private JButton btnAgregar;
    private JTable tablaNotas;
    private DefaultTableModel modeloTabla;

    public NotasApp() {
        setTitle("Registro de Notas");
        setSize(940, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 25);
        add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(120, 10, 150, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(10, 40, 100, 25);
        add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setBounds(120, 40, 150, 25);
        add(txtApellido);

        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(10, 70, 100, 25);
        add(lblCurso);
        
        txtCurso = new JTextField();
        txtCurso.setBounds(120, 70, 150, 25);
        add(txtCurso);

        JLabel lblExamen1 = new JLabel("Examen 1:");
        lblExamen1.setBounds(10, 100, 100, 25);
        add(lblExamen1);
        
        txtExamen1 = new JTextField();
        txtExamen1.setBounds(120, 100, 50, 25);
        add(txtExamen1);

        JLabel lblExamen2 = new JLabel("Examen 2:");
        lblExamen2.setBounds(180, 100, 100, 25);
        add(lblExamen2);
        
        txtExamen2 = new JTextField();
        txtExamen2.setBounds(260, 100, 50, 25);
        add(txtExamen2);

        JLabel lblExamen3 = new JLabel("Examen 3:");
        lblExamen3.setBounds(320, 100, 100, 25);
        add(lblExamen3);
        
        txtExamen3 = new JTextField();
        txtExamen3.setBounds(400, 100, 50, 25);
        add(txtExamen3);

        JLabel lblExamen4 = new JLabel("Examen 4:");
        lblExamen4.setBounds(460, 100, 100, 25);
        add(lblExamen4);
        
        txtExamen4 = new JTextField();
        txtExamen4.setBounds(540, 100, 50, 25);
        add(txtExamen4);

        // Botón para agregar las notas
        btnAgregar = new JButton("Agregar Nota");
        btnAgregar.setBounds(220, 150, 150, 25);
        add(btnAgregar);

        // Configuración de la tabla
        String[] columnas = {"Nombre", "Apellido", "Curso", "Examen 1", "Examen 2", "Examen 3", "Examen 4", "Total", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaNotas = new JTable(modeloTabla);

        JScrollPane scrollTabla = new JScrollPane(tablaNotas);
        scrollTabla.setBounds(10, 200, 860, 150);
        add(scrollTabla);

        // Acción al presionar el botón
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNota();
            }
        });
    }


    private void agregarNota() {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String curso = txtCurso.getText();
        double examen1 = Double.parseDouble(txtExamen1.getText());
        double examen2 = Integer.parseInt(txtExamen2.getText());
        double examen3 = Integer.parseInt(txtExamen3.getText());
        double examen4 = Integer.parseInt(txtExamen4.getText());
        

        double total = (examen1 + examen2 + examen3 + examen4) / 4.0; 
        String estado;
        if (total > 13) {
            estado = "APROBADO";
        } else {
            estado = "DESAPROBADO";
        }
 

        Object[] fila = {nombre, apellido, curso, examen1, examen2, examen3, examen4, total,estado};
        modeloTabla.addRow(fila);


        txtNombre.setText("");
        txtApellido.setText("");
        txtCurso.setText("");
        txtExamen1.setText("");
        txtExamen2.setText("");
        txtExamen3.setText("");
        txtExamen4.setText("");
    }

    public static void main(String[] args) {
        NotasApp ventana = new NotasApp();
        ventana.setVisible(true);
    }
}
