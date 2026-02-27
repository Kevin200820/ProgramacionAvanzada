package VISTA;

import javax.swing.*;

public class VentanaHola extends JFrame {

    private JLabel mensaje;

    public VentanaHola() {
        setTitle("Bienvenida");
        setSize(420,220);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mensaje = new JLabel("Hola Mundo - Curso Swing");
        mensaje.setBounds(100,80,250,30);
        add(mensaje);
    }

    public JLabel getMensaje(){
        return mensaje;
    }
}