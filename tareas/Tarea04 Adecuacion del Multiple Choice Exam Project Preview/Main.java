package Exam_Project_Preview;

import vista.ExamView;
import controlador.ExamManager;

public class Main {

    public static void main(String[] args) {

        ExamView interfaz = new ExamView();
        new ExamManager(interfaz);

        interfaz.setVisible(true);
    }
}