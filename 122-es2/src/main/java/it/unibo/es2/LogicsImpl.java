package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics {
    List<List<Button>> buttons;
    int size;

    public LogicsImpl(int size) {
        this.size = size;
        this.buttons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Button> col = new ArrayList<>();
            buttons.add(col);
            for (int j = 0; j < size; j++) {
                col.add(new Button(i, j));
            }
        }
    }

    @Override
    public String hit(final Pair<Integer, Integer> elem) {
        buttons.get(elem.getX()).get(elem.getY()).switchVal();
        return buttons.get(elem.getX()).get(elem.getY()).getValue();
    }

    @Override
    public String getResult(final Pair<Integer, Integer> elem) {
        return buttons.get(elem.getX()).get(elem.getY()).getValue();
    }

    @Override
    public boolean toQuit(final Pair<Integer, Integer> elem) {
        boolean row = this.buttons.stream().flatMap(l -> l.stream()).filter(b -> b.getPair().getX().equals(elem.getX()))
                .allMatch(b -> b.getValue().equals("*"));
        boolean col = this.buttons.stream().flatMap(l -> l.stream()).filter(b -> b.getPair().getY().equals(elem.getY()))
                .allMatch(b -> b.getValue().equals("*"));
        return (row || col);
    }

    static class Button {
        Pair<Integer, Integer> pos;
        String value;

        Button(final int x, final int y) {
            this.pos = new Pair<>(x, y);
            this.value = " ";
        }

        String getValue() {
            return this.value;
        }

        Pair<Integer, Integer> getPair() {
            return this.pos;
        }

        void switchVal() {
            if (this.value == " ") {
                this.value = "*";
            } else if (this.value == "*") {
                this.value = " ";
            }
        }
    }
}
