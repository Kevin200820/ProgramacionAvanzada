package VISTA;

import javax.swing.*;

public class VentanaDialogos extends JFrame {

    private JButton alerta;

    public VentanaDialogos() {
        setTitle("Mensajes");
        setSize(300,200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        alerta = new JButton("Mostrar Mensaje");
        alerta.setBounds(70,75,160,35);
        add(alerta);
    }

    public JButton getAlerta(){
        return alerta;
    }
}