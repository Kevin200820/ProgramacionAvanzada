package VISTA;

import javax.swing.*;
import java.awt.*;

public class VentanaGrid extends JFrame {

    public VentanaGrid() {
        setTitle("Teclado Numérico");
        setSize(300,300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,3));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        for(int i=1;i<=9;i++){
            add(new JButton("N° " + i));
        }
    }
}