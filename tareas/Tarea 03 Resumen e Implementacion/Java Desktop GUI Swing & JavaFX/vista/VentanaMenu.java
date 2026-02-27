package VISTA;

import javax.swing.*;

public class VentanaMenu extends JFrame {

    private JMenuBar barra;
    private JMenu opciones;

    public VentanaMenu() {
        setTitle("Menú Principal");
        setSize(320,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        barra = new JMenuBar();
        setJMenuBar(barra);

        opciones = new JMenu("Archivo");
        barra.add(opciones);
    }

    public JMenu getOpciones(){
        return opciones;
    }
}