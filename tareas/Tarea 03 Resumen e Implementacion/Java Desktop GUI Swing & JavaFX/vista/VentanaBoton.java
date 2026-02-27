package VISTA;

import javax.swing.*;

public class VentanaBoton extends JFrame {

    private JButton boton;

    public VentanaBoton() {
        setTitle("Ejemplo Botón");
        setSize(380,200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        boton = new JButton("Presionar");
        boton.setBounds(110,70,150,35);
        add(boton);
    }

    public JButton getBoton(){
        return boton;
    }
}