package proyecto.java.Calendario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;

public class CalendarioTrabajo extends JFrame {
    private JComboBox<String> comboMes;
    private JTextField txtAño;
    private JPanel panelCalendario;

    public CalendarioTrabajo() {
        setTitle("Calendario de Trabajo Aleatorio");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de selección de mes y año
        JPanel panelSeleccion = new JPanel();
        panelSeleccion.add(new JLabel("Mes:"));
        
        // ComboBox para seleccionar el mes
        comboMes = new JComboBox<>(new String[]{
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        });

        panelSeleccion.add(comboMes);
        panelSeleccion.add(new JLabel("Año:"));
        
        // Campo de texto para el año
        txtAño = new JTextField(4);
        panelSeleccion.add(txtAño);
        
        // Botón para generar el calendario
        JButton btnGenerar = new JButton("Generar Calendario");
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarCalendario();
            }
        });
        panelSeleccion.add(btnGenerar);

        // Panel para el calendario
        panelCalendario = new JPanel();
        panelCalendario.setLayout(new GridLayout(0, 7)); // 7 columnas para los días de la semana

        add(panelSeleccion, BorderLayout.NORTH);
        add(new JScrollPane(panelCalendario), BorderLayout.CENTER);

        setVisible(true);
    }

    private void generarCalendario() {
        panelCalendario.removeAll(); // Limpiar panel

        // Obtener mes y año
        int mes = comboMes.getSelectedIndex(); // 0-11 para enero-diciembre
        int año;
        
        try {
            año = Integer.parseInt(txtAño.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un año válido.");
            return;
        }

        // Configurar el calendario
        Calendar calendar = Calendar.getInstance();
        calendar.set(año, mes, 1); // Establecer al primer día del mes

   
        int diasDelMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Llenar los días de la semana
        String[] diasSemana = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
        for (String dia : diasSemana) {
            panelCalendario.add(new JLabel(dia, SwingConstants.CENTER));
        }

        // Llenar el calendario con días
        Random random = new Random();
        boolean nochePrevio = false; // Para indicar si el día anterior fue noche

        for (int dia = 1; dia <= diasDelMes; dia++) {
            JLabel diaLabel = new JLabel(String.valueOf(dia), SwingConstants.CENTER);
            String horario;

            // Avanzar al siguiente día
            calendar.set(año, mes, dia); // Establecer la fecha actual

            // Si es domingo
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                // Se trabaja de noche
                horario = "Noche (20:00 - 21:00)";
                diaLabel.setForeground(Color.BLUE);
                nochePrevio = true; // Marcar que se trabajó de noche
            } else if (nochePrevio) {
                // Día de descanso si se trabajó la noche anterior
                horario = "Descanso";
                diaLabel.setForeground(Color.RED);
                nochePrevio = false; // Restablecer el estado
            } else {
                // Trabajar de forma aleatoria
                if (random.nextBoolean()) {
                    // Trabajar de día
                    horario = "Día (6:00 - 19:00)";
                    diaLabel.setForeground(Color.GREEN);
                } else {
                    // Trabajar de noche
                    horario = "Noche (19:00 - 6:00)";
                    diaLabel.setForeground(Color.BLUE);
                    nochePrevio = true; // Marcar que se trabajó de noche
                }
            }

            // Actualizar el texto del JLabel para mostrar el horario
            diaLabel.setText(dia + " - " + horario);
            panelCalendario.add(diaLabel);
        }

        panelCalendario.revalidate(); // Actualizar la vista
        panelCalendario.repaint(); // Redibujar el panel
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalendarioTrabajo());
    }
}
