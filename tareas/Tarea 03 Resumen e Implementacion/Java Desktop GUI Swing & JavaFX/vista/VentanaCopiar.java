package VISTA;

import javax.swing.*;

public class VentanaCopiar extends JFrame {

    private JTextField origen;
    private JTextField destino;
    private JButton copiar;

    public VentanaCopiar() {
        setTitle("Transferir Texto");
        setSize(460,260);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        origen = new JTextField();
        origen.setBounds(60,60,150,30);
        add(origen);

        destino = new JTextField();
        destino.setBounds(250,60,150,30);
        add(destino);

        copiar = new JButton("Transferir");
        copiar.setBounds(170,130,120,35);
        add(copiar);
    }

    public JTextField getOrigen(){ return origen; }
    public JTextField getDestino(){ return destino; }
    public JButton getCopiar(){ return copiar; }
}