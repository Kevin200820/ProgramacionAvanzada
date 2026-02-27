package VISTA;

import javax.swing.*;

public class VentanaConversor extends JFrame {

    private JTextField cantidad;
    private JComboBox<String> monedas;
    private JButton convertir;

    public VentanaConversor() {
        setTitle("Cambio de Moneda");
        setSize(420,260);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cantidad = new JTextField();
        cantidad.setBounds(130,40,160,30);
        add(cantidad);

        monedas = new JComboBox<>(new String[]{"USD","MXN","EUR"});
        monedas.setBounds(130,85,160,30);
        add(monedas);

        convertir = new JButton("Calcular");
        convertir.setBounds(150,140,120,35);
        add(convertir);
    }

    public JTextField getCantidad(){ return cantidad; }
    public JComboBox<String> getMonedas(){ return monedas; }
    public JButton getConvertir(){ return convertir; }
}