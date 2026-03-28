package Main;

import javax.swing.SwingUtilities;
import Vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaPrincipal();
            }
        });

    }
}