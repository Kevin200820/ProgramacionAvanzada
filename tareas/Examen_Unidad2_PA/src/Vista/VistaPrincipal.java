package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import Controlador.ControladorPrincipal;


import Modelo.*;

public class VistaPrincipal extends JFrame {

    private JComboBox<String> cbProfesor, cbAsignatura, cbGrupo;
    private JButton btnGuardar, btnCargar, btnEliminar, btnNuevo;

    private JLabel lblEstado;

    private VistaProductoIntegrador vistaProducto;
    private VistaRubrica vistaRubrica;
    private VistaListaCotejo vistaCotejo;

    private ControladorPrincipal controlador;

    public VistaPrincipal() {

        controlador = new ControladorPrincipal();

        setTitle("Sistema Evaluación");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 PANEL SUPERIOR
        JPanel panelTop = new JPanel();

        cbProfesor = new JComboBox<>();
        cbAsignatura = new JComboBox<>();
        cbGrupo = new JComboBox<>();

        btnGuardar = new JButton("Guardar");
        btnCargar = new JButton("Cargar");
        btnEliminar = new JButton("Eliminar");
        btnNuevo = new JButton("Nuevo");

      
        lblEstado = new JLabel("Sin iniciar");
        lblEstado.setOpaque(true);
        lblEstado.setBackground(Color.RED);
        lblEstado.setForeground(Color.WHITE);

        panelTop.add(new JLabel("Profesor"));
        panelTop.add(cbProfesor);
        panelTop.add(new JLabel("Materia"));
        panelTop.add(cbAsignatura);
        panelTop.add(new JLabel("Grupo"));
        panelTop.add(cbGrupo);

        panelTop.add(btnGuardar);
        panelTop.add(btnCargar);
        panelTop.add(btnEliminar);
        panelTop.add(btnNuevo);

        panelTop.add(lblEstado);

        add(panelTop, BorderLayout.NORTH);

    
        vistaProducto = new VistaProductoIntegrador();
        vistaRubrica = new VistaRubrica();
        vistaCotejo = new VistaListaCotejo();

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Producto", vistaProducto);
        tabs.add("Rúbrica", vistaRubrica);
        tabs.add("Cotejo", vistaCotejo);

        add(tabs, BorderLayout.CENTER);

        cargarProfesores();
        eventos();

        setVisible(true);
    }

  
    private void cargarProfesores() {
        ArrayList<String> profesores = controlador.obtenerProfesores();

        cbProfesor.removeAllItems();
        for (String p : profesores) {
            cbProfesor.addItem(p);
        }
    }


    private void eventos() {

        cbProfesor.addActionListener(e -> {

            String profesor = (String) cbProfesor.getSelectedItem();

            if (profesor != null) {
                cbAsignatura.removeAllItems();

                for (String m : controlador.obtenerAsignaturas(profesor)) {
                    cbAsignatura.addItem(m);
                }
            }
        });

        cbAsignatura.addActionListener(e -> {

            String profesor = (String) cbProfesor.getSelectedItem();
            String materia = (String) cbAsignatura.getSelectedItem();

            if (profesor != null && materia != null) {
                cbGrupo.removeAllItems();

                for (String g : controlador.obtenerGrupos(profesor, materia)) {
                    cbGrupo.addItem(g);
                }
            }
        });

        cbGrupo.addActionListener(e -> {

            String profesor = (String) cbProfesor.getSelectedItem();
            String materia = (String) cbAsignatura.getSelectedItem();
            String grupo = (String) cbGrupo.getSelectedItem();

            if (profesor != null && materia != null && grupo != null) {

                ArrayList<String> alumnos =
                        controlador.obtenerAlumnos(profesor, materia, grupo);

                vistaRubrica.cargarAlumnos(alumnos);
                actualizarSemaforo();
            }
        });

        btnGuardar.addActionListener(e -> guardar());
        btnCargar.addActionListener(e -> cargar());
        btnEliminar.addActionListener(e -> eliminar());
        btnNuevo.addActionListener(e -> limpiar());

       
        vistaProducto.txtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                actualizarSemaforo();
            }
        });

        vistaProducto.txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                actualizarSemaforo();
            }
        });
    }

   
    private void guardar() {

        String profesor = (String) cbProfesor.getSelectedItem();
        String materia = (String) cbAsignatura.getSelectedItem();
        String grupo = (String) cbGrupo.getSelectedItem();

        if (profesor == null || materia == null || grupo == null) {
            JOptionPane.showMessageDialog(this, "Faltan datos");
            return;
        }

        Evaluacion ev = new Evaluacion();

        ev.setProfesor(new Profesor(profesor));
        ev.setAsignatura(new Asignatura(materia));
        ev.setGrupo(new Grupo(grupo));
        ev.setId();

        ProductoIntegrador pi = new ProductoIntegrador();
        pi.setFecha(vistaProducto.txtFecha.getText());
        pi.setObservaciones(vistaProducto.txtObservaciones.getText());
        ev.setProducto(pi);

        Rubrica r = new Rubrica();

        for (int i = 0; i < vistaRubrica.tabla.getRowCount(); i++) {
            for (int j = 1; j < vistaRubrica.tabla.getColumnCount(); j++) {

                Object val = vistaRubrica.tabla.getValueAt(i, j);

                try {
                    r.getCriterios().add(Float.parseFloat(val.toString()));
                } catch (Exception ex) {}
            }
        }

        r.calcularPromedio();
        ev.setRubrica(r);

        ListaCotejo lc = new ListaCotejo();

        for (JCheckBox c : vistaCotejo.checks) {
            lc.getIndicadores().add(c.isSelected());
        }

        ev.setLista(lc);

        controlador.guardarEvaluacion(ev);

        actualizarSemaforo();

        JOptionPane.showMessageDialog(this, "Guardado completo");
    }

    
    private void cargar() {

        Evaluacion ev = controlador.cargarEvaluacion(generarId());

        if (ev == null) {
            JOptionPane.showMessageDialog(this, "No existe");
            return;
        }

        if (ev.getProducto() != null) {
            vistaProducto.txtFecha.setText(ev.getProducto().getFecha());
            vistaProducto.txtObservaciones.setText(ev.getProducto().getObservaciones());
        }

        if (ev.getRubrica() != null) {

            int index = 0;

            for (int i = 0; i < vistaRubrica.tabla.getRowCount(); i++) {
                for (int j = 1; j < vistaRubrica.tabla.getColumnCount(); j++) {

                    if (index < ev.getRubrica().getCriterios().size()) {
                        vistaRubrica.tabla.setValueAt(
                                ev.getRubrica().getCriterios().get(index),
                                i, j
                        );
                        index++;
                    }
                }
            }

            vistaRubrica.lblPromedio.setText("Promedio: " + ev.getRubrica().getPromedio());
        }

        if (ev.getLista() != null) {

            for (int i = 0; i < vistaCotejo.checks.length; i++) {

                if (i < ev.getLista().getIndicadores().size()) {
                    vistaCotejo.checks[i].setSelected(
                            ev.getLista().getIndicadores().get(i)
                    );
                }
            }
        }

        actualizarSemaforo();

        JOptionPane.showMessageDialog(this, "Cargado completo");
    }

  
    private void eliminar() {
        controlador.eliminarEvaluacion(generarId());
        JOptionPane.showMessageDialog(this, "Eliminado");
    }

  
    private void limpiar() {

        vistaProducto.txtFecha.setText("");
        vistaProducto.txtObservaciones.setText("");

        vistaRubrica.cargarAlumnos(new ArrayList<>());
        vistaRubrica.lblPromedio.setText("Promedio: 0");

        for (JCheckBox c : vistaCotejo.checks) {
            c.setSelected(false);
        }

        actualizarSemaforo();
    }

   
    private void actualizarSemaforo() {

        boolean hayProducto = !vistaProducto.txtFecha.getText().isEmpty()
                || !vistaProducto.txtObservaciones.getText().isEmpty();

        boolean hayRubrica = vistaRubrica.tabla.getRowCount() > 0;

        boolean hayCotejo = false;
        for (JCheckBox c : vistaCotejo.checks) {
            if (c.isSelected()) {
                hayCotejo = true;
                break;
            }
        }

        if (!hayProducto && !hayRubrica && !hayCotejo) {
            lblEstado.setText("Sin iniciar");
            lblEstado.setBackground(Color.RED);
        }
        else if (!(hayProducto && hayRubrica && hayCotejo)) {
            lblEstado.setText("Incompleto");
            lblEstado.setBackground(Color.YELLOW);
        }
        else {
            lblEstado.setText("Completo");
            lblEstado.setBackground(Color.GREEN);
        }
    }

    private String generarId() {
        return cbAsignatura.getSelectedItem() + "_"
                + cbProfesor.getSelectedItem() + "_"
                + cbGrupo.getSelectedItem();
    }
}