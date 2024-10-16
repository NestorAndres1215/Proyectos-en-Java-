package proyecto.java.Vacaciones;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



import javax.swing.table.DefaultTableModel;

import java.util.*;

public class VacacionesPersonas extends JFrame {
    // Meses del año y sus días
    private static final Map<String, Integer> MESES = new LinkedHashMap<>() {{
        put("Enero", 31);
        put("Febrero", 28); // Considerando años no bisiestos
        put("Marzo", 31);
        put("Abril", 30);
        put("Mayo", 31);
        put("Junio", 30);
        put("Julio", 31);
        put("Agosto", 31);
        put("Septiembre", 30);
        put("Octubre", 31);
        put("Noviembre", 30);
        put("Diciembre", 31);
    }};

    // Nombres, apellidos y cargos de las personas
    private static final Object[][] PERSONAS = {
            {"Juan", "Pérez", "Desarrollador", 28},
            {"María", "Gómez", "Analista", 32},
            {"Carlos", "López", "Gerente", 45},
            {"Ana", "Torres", "Diseñadora", 27},
            {"Luis", "Martínez", "Tester", 30},
            {"Sofía", "Ríos", "Administradora", 35}
    };

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public VacacionesPersonas() {
        setTitle("Asignación de Vacaciones");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Apellido", "Cargo", "Edad", "Mes de Vacaciones", "Fechas de Vacaciones"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnGenerar = new JButton("Generar Vacaciones");
        btnGenerar.addActionListener(e -> generarVacaciones());

        add(btnGenerar, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void generarVacaciones() {
        // Limpiar la tabla antes de agregar nuevos datos
        modeloTabla.setRowCount(0);

        // Barajar los meses
        List<String> mesesDisponibles = new ArrayList<>(MESES.keySet());
        Collections.shuffle(mesesDisponibles); // Barajar los meses

        // Asignar un mes de vacaciones a cada persona y agregarlo a la tabla
        for (int i = 0; i < PERSONAS.length; i++) {
            String mesVacaciones = mesesDisponibles.get(i);
            String fechasVacaciones = calcularFechasVacaciones(mesVacaciones);

            modeloTabla.addRow(new Object[]{
                    PERSONAS[i][0], PERSONAS[i][1], PERSONAS[i][2], PERSONAS[i][3], mesVacaciones, fechasVacaciones
            });
        }
    }

    private String calcularFechasVacaciones(String mes) {
        // Obtener el número de días para el mes seleccionado
        int diasEnMes = MESES.get(mes);
        StringBuilder rangoFechas = new StringBuilder();

        // Si el mes es febrero, agregar los días faltantes al siguiente mes
        if (mes.equals("Febrero")) {
            rangoFechas.append("Del 1 al ").append(diasEnMes).append(" de ").append(mes).append(" y del 1 al 2 de Marzo");
        } else {
            // Para los demás meses, simplemente devolver el rango
            rangoFechas.append("Del 1 al ").append(diasEnMes).append(" de ").append(mes);
        }

        return rangoFechas.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VacacionesPersonas::new);
    }
}