package Vista;
import javax.swing.*;
import java.awt.*;

public class VistaListaCotejo extends JPanel {

    public JCheckBox[] checks;

    public VistaListaCotejo() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(0,1));

        String[] indicadores = {
                "Cumple requisito 1",
                "Cumple requisito 2",
                "Cumple requisito 3",
                "Cumple requisito 4"
        };

        checks = new JCheckBox[indicadores.length];

        for (int i = 0; i < indicadores.length; i++) {
            checks[i] = new JCheckBox(indicadores[i]);
            panel.add(checks[i]);
        }

        JButton btnMarcarTodo = new JButton("Marcar todos");

        btnMarcarTodo.addActionListener(e -> {
            for (JCheckBox c : checks) {
                c.setSelected(true);
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnMarcarTodo, BorderLayout.SOUTH);
    }
}