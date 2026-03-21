package Vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class VistaTienda extends JInternalFrame {

   
    public JTextField  txtId, txtNombre, txtPrecio, txtUrlImagen;
    public JComboBox<String> cbCategoria;
    public JRadioButton rbDisponible, rbAgotado;

   
    public JButton btnCrear, btnConsultar, btnModificar, btnEliminar, btnLimpiar;


    public JButton btnReporteGeneral, btnReporteCategoria;

    
    public JTable tabla;
    public DefaultTableModel modeloTabla;

    
    public JLabel lblImagen;
    public JTextArea txtInfoExtra;

    public static final String[] CATEGORIAS = {
        "Abarrotes", "Bebidas", "Lácteos y Huevo",
        "Frutas y Verduras", "Carnes y Pescados",
        "Salchichonería", "Panadería y Tortillería",
        "Limpieza del Hogar", "Cuidado Personal",
        "Snacks y Dulcería", "Mascotas"
    };

    public VistaTienda() {
        super("Gestión de Inventario", true, true, true, true);
        setSize(1150, 680);
        setLayout(new BorderLayout(5, 5));
        add(panelFormulario(), BorderLayout.NORTH);
        add(panelCentro(),     BorderLayout.CENTER);
        add(panelReportes(),   BorderLayout.SOUTH);
    }

 
    private JPanel panelFormulario() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,6,4,6); c.fill = GridBagConstraints.HORIZONTAL;

       
        c.gridx=0; c.gridy=0; p.add(new JLabel("ID:"),c);
        c.gridx=1; txtId=new JTextField(10); p.add(txtId,c);
        c.gridx=2; p.add(new JLabel("Nombre:"),c);
        c.gridx=3; c.gridwidth=2; txtNombre=new JTextField(25); p.add(txtNombre,c); c.gridwidth=1;

        c.gridx=0; c.gridy=1; p.add(new JLabel("Precio:"),c);
        c.gridx=1; txtPrecio=new JTextField(10); p.add(txtPrecio,c);
        c.gridx=2; p.add(new JLabel("Categoría:"),c);
        c.gridx=3; c.gridwidth=2; cbCategoria=new JComboBox<>(CATEGORIAS); p.add(cbCategoria,c); c.gridwidth=1;

   
       
       
        rbDisponible=new JRadioButton("Disponible",true);
        rbAgotado=new JRadioButton("Agotado");
        ButtonGroup bg=new ButtonGroup(); bg.add(rbDisponible); bg.add(rbAgotado);
        JPanel pEst=new JPanel(); pEst.add(rbDisponible); pEst.add(rbAgotado);
        c.gridx=0; c.gridy=3; p.add(pEst,c);

        btnCrear     =new JButton("Guardar");
        btnConsultar =new JButton(" Buscar");
        btnModificar =new JButton("Editar");
        btnEliminar  =new JButton(" Borrar");
        btnLimpiar   =new JButton(" Nuevo");
        JPanel pBtn=new JPanel(new FlowLayout(FlowLayout.LEFT,6,0));
        pBtn.add(btnCrear); pBtn.add(btnConsultar); pBtn.add(btnModificar);
        pBtn.add(btnEliminar); pBtn.add(btnLimpiar);
        c.gridx=1; c.gridwidth=4; p.add(pBtn,c); c.gridwidth=1;
        return p;
    }

  
    private JSplitPane panelCentro() {
      
        modeloTabla = new DefaultTableModel(
            new String[]{"ID","Nombre","Categoría","Precio","Estado"},0) {
            @Override public boolean isCellEditable(int r,int c){return false;}
        };
        tabla=new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);

      
        JPanel pRight = new JPanel(new BorderLayout(5,5));
        pRight.setBorder(BorderFactory.createTitledBorder("Vista del Producto"));

        lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(200,200));
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblImagen.setOpaque(true);
        lblImagen.setBackground(new Color(248,248,248));

        txtInfoExtra = new JTextArea(4,20);
        txtInfoExtra.setEditable(false);
        txtInfoExtra.setFont(new Font("Monospaced",Font.PLAIN,11));
        txtInfoExtra.setBackground(new Color(250,250,240));
        txtInfoExtra.setLineWrap(true);
        txtInfoExtra.setWrapStyleWord(true);

        pRight.add(lblImagen, BorderLayout.CENTER);
        pRight.add(new JScrollPane(txtInfoExtra), BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(tabla), pRight);
        split.setResizeWeight(0.75);
        return split;
    }

    
    private JPanel panelReportes() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT,8,4));
        p.setBorder(BorderFactory.createTitledBorder("Reportes Excel"));
        btnReporteGeneral   = new JButton("📊 Listado General");
        btnReporteCategoria = new JButton("📂 Por Categoría");
        p.add(btnReporteGeneral);
        p.add(btnReporteCategoria);
        return p;
    }

    
    public void mostrarImagen(String urlStr) {
        lblImagen.setIcon(null);
        lblImagen.setText("Cargando...");
        if (urlStr == null || urlStr.isBlank()) { lblImagen.setText("Sin imagen"); return; }
        new SwingWorker<ImageIcon,Void>() {
            @Override protected ImageIcon doInBackground() throws Exception {
                Image img = new ImageIcon(new URL(urlStr))
                    .getImage().getScaledInstance(190,190,Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
            @Override protected void done() {
                try { lblImagen.setIcon(get()); lblImagen.setText(""); }
                catch (Exception e) { lblImagen.setText("Imagen no disponible"); }
            }
        }.execute();
    }

    public void mostrarInfoExtra(String info) {
        txtInfoExtra.setText(info != null ? info : "");
    }
}