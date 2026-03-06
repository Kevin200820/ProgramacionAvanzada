package vista;

import javax.swing.*;
import java.awt.*;

public class ExamView extends JFrame {

    public JLabel lblTitulo = new JLabel("Abre un archivo CSV para iniciar el examen.", SwingConstants.CENTER);
    public JLabel lblInstruccion = new JLabel("", SwingConstants.CENTER);
    public JLabel lblPregunta = new JLabel("", SwingConstants.CENTER);

    public JRadioButton[] opciones = new JRadioButton[4];
    public ButtonGroup grupo = new ButtonGroup();

    public JButton btnEmpezar = new JButton("Iniciar");
    public JButton btnResponder = new JButton("Responder");
    public JButton btnSiguiente = new JButton("Siguiente");

    public JMenu menuArchivo = new JMenu("Archivo");
    public JMenu menuOpciones = new JMenu("Opciones");

    public JMenuItem menuAbrir = new JMenuItem("Abrir CSV");
    public JMenuItem menuSalir = new JMenuItem("Salir");

    public JCheckBoxMenuItem checkInvertir = new JCheckBoxMenuItem("Invertir Pregunta / Respuesta");

    public ExamView() {

        setTitle("Sistema de Examen MVC");
        setSize(560, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(3,1));

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstruccion.setFont(new Font("Arial", Font.ITALIC, 14));
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 18));

        lblInstruccion.setForeground(Color.GRAY);

        panelSuperior.add(lblTitulo);
        panelSuperior.add(lblInstruccion);
        panelSuperior.add(lblPregunta);

        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        for(int i=0;i<4;i++){

            opciones[i] = new JRadioButton();
            opciones[i].setFont(new Font("Arial",Font.PLAIN,14));

            grupo.add(opciones[i]);
            panelCentro.add(opciones[i]);
            panelCentro.add(Box.createRigidArea(new Dimension(0,10)));
        }

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnEmpezar.setEnabled(false);
        btnResponder.setEnabled(false);
        btnSiguiente.setEnabled(false);

        panelBotones.add(btnEmpezar);
        panelBotones.add(btnResponder);
        panelBotones.add(btnSiguiente);

        add(panelBotones, BorderLayout.SOUTH);

        crearMenu();
    }

    private void crearMenu(){

        JMenuBar barra = new JMenuBar();

        menuArchivo.add(menuAbrir);
        menuArchivo.add(menuSalir);

        menuOpciones.add(checkInvertir);
        menuOpciones.setEnabled(false);

        barra.add(menuArchivo);
        barra.add(menuOpciones);

        setJMenuBar(barra);
    }

    public void limpiarSeleccion(){

        grupo.clearSelection();
    }

    public String getOpcionSeleccionada(){

        for(JRadioButton r : opciones){

            if(r.isSelected()){
                return r.getText();
            }
        }

        return null;
    }
}