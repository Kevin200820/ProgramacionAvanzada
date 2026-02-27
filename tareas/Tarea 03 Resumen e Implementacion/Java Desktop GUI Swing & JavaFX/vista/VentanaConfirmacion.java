package VISTA;

import javax.swing.*;

public class VentanaConfirmacion extends JFrame {

    private JButton confirmar;

    public VentanaConfirmacion() {
        setTitle("Ventana Confirmar");
        setSize(300,200);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        confirmar = new JButton("Aceptar");
        confirmar.setBounds(85,75,130,35);
        add(confirmar);
    }

    public JButton getConfirmar(){
        return confirmar;
    }
}