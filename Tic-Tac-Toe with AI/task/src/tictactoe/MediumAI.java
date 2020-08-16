package tictactoe;

import java.util.Random;

public class MediumAI implements Player {
    private Random random = new Random();

    @Override
    public void move(Field field) {
        System.out.println(field);
        System.out.println("Making move level \"medium\"");

        int x, y;

        do {
            x = random.nextInt(3) + 1;
            y = random.nextInt(3) + 1;
        } while (!field.isFree(x, y));

        if (field.nextSymbol() == Symbol.X) {
            checkFieldAndMove(field, x, y, Symbol.X);
        } else if (field.nextSymbol() == Symbol.O) {
            checkFieldAndMove(field, x, y, Symbol.O);
        }
    }

    private void checkFieldAndMove(Field field, int x, int y, Symbol symbol) {
        if (symbol.getSymbol().equals("X")) {
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.willWin(i, j, Symbol.X)) {
                        field.set(i, j);
                        return;
                    } else if (field.willWin(i, j, Symbol.O)) {
                        field.set(i, j);
                        return;
                    }
                }
            }
        } else if (symbol.getSymbol().equals("O")) {
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.willWin(i, j, Symbol.O)) {
                        field.set(i, j);
                        return;
                    } else if (field.willWin(i, j, Symbol.X)) {
                        field.set(i, j);
                        return;
                    }
                }
            }
        }
        field.set(x, y);
    }

}
