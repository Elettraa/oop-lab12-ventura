package it.unibo.es2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private final Logics logics;

    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        ActionListener al = (e) -> {
            final JButton buttonClicked = (JButton) e.getSource();
            final Pair<Integer, Integer> buttonPosition = buttons.get(buttonClicked);
            buttonClicked.setText(logics.hit(buttonPosition));
            if (logics.toQuit(buttonPosition)) {
                System.exit(1);
            }
        };

        LogicsImpl li = new LogicsImpl(size);
        li.buttons.stream().flatMap(l -> l.stream())
            .forEach(b -> {
            final JButton jb = new JButton(b.getValue());
            jb.addActionListener(al);
            this.buttons.put(jb, b.getPair());
            panel.add(jb);
        });

        this.setVisible(true);
    }
}
