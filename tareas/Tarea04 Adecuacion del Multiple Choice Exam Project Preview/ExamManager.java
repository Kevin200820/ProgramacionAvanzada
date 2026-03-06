package controlador;

import modelo.*;
import vista.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public class ExamManager {

    private ExamView interfaz;
    private Test examenActual;
    private FileManager gestor = new FileManager();

    public ExamManager(ExamView interfaz) {
        this.interfaz = interfaz;
        configurarEventos();
    }

    private void configurarEventos() {

        interfaz.menuAbrir.addActionListener(e -> seleccionarArchivo());
        interfaz.menuSalir.addActionListener(e -> System.exit(0));
        interfaz.checkInvertir.addActionListener(e -> actualizarInstruccion());

        interfaz.btnEmpezar.addActionListener(e -> iniciar());
        interfaz.btnResponder.addActionListener(e -> responder());
        interfaz.btnSiguiente.addActionListener(e -> siguiente());
    }

    private void seleccionarArchivo() {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("CSV files", "csv");
        chooser.setFileFilter(filtro);

        if (chooser.showOpenDialog(interfaz) == JFileChooser.APPROVE_OPTION) {

            try {

                File archivo = chooser.getSelectedFile();
                gestor.cargarArchivo(archivo);

                interfaz.lblTitulo.setText(gestor.getTitulo());

                interfaz.menuOpciones.setEnabled(true);
                interfaz.btnEmpezar.setEnabled(true);

                interfaz.btnResponder.setEnabled(false);
                interfaz.btnSiguiente.setEnabled(false);

                interfaz.lblPregunta.setText("");
                interfaz.limpiarSeleccion();

                for (JRadioButton r : interfaz.opciones) {
                    r.setText("");
                }

                actualizarInstruccion();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(interfaz, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarInstruccion() {

        boolean invertido = interfaz.checkInvertir.isSelected();
        interfaz.lblInstruccion.setText(gestor.getInstruccion(invertido));
    }

    private void iniciar() {

        interfaz.menuArchivo.setEnabled(false);
        interfaz.menuOpciones.setEnabled(false);
        interfaz.btnEmpezar.setEnabled(false);

        List<Question> lista = gestor.generarPreguntas(interfaz.checkInvertir.isSelected());

        examenActual = new Test(lista);

        mostrarPregunta();
    }

    private void mostrarPregunta() {

        Question q = examenActual.getPreguntaActual();

        interfaz.lblPregunta.setText((examenActual.getIndiceActual() + 1) + ". " + q.getEnunciado());

        String[] ops = q.getOpciones();

        for (int i = 0; i < interfaz.opciones.length; i++) {
            interfaz.opciones[i].setText(ops[i]);
        }

        interfaz.limpiarSeleccion();

        interfaz.btnResponder.setEnabled(true);
        interfaz.btnSiguiente.setEnabled(false);
    }

    private void responder() {

        String seleccion = interfaz.getOpcionSeleccionada();

        if (seleccion == null) {
            JOptionPane.showMessageDialog(interfaz, "Selecciona una opción", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean correcta = examenActual.verificarRespuesta(seleccion);

        if (correcta) {
            JOptionPane.showMessageDialog(interfaz, "Correcto");
        } else {
            JOptionPane.showMessageDialog(
                    interfaz,
                    "Incorrecto\nCorrecta: " + examenActual.getPreguntaActual().getRespuestaCorrecta()
            );
        }

        interfaz.btnResponder.setEnabled(false);
        interfaz.btnSiguiente.setEnabled(true);
    }

    private void siguiente() {

        examenActual.siguientePregunta();

        if (examenActual.getIndiceActual() >= examenActual.getTotalPreguntas()) {

            JOptionPane.showMessageDialog(
                    interfaz,
                    "Examen terminado\nAciertos: " + examenActual.getCorrectas() + "/" + examenActual.getTotalPreguntas()
            );

            interfaz.btnSiguiente.setEnabled(false);
            interfaz.menuArchivo.setEnabled(true);
            return;
        }

        mostrarPregunta();
    }
}