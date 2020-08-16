package tictactoe;

public class HardAI implements Player {

    @Override
    public void move(Field field) {
        int[] bestMove = new int[2];
        System.out.println(field);
        System.out.println("Making move level \"hard\"");

        if (field.nextSymbol() == Symbol.X) {
            int bestScore = Integer.MIN_VALUE;
            int score;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        score = miniMax(field,false);
                        field.unset(i, j);
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            field.set(bestMove[0], bestMove[1]);
        } else if (field.nextSymbol() == Symbol.O) {
            int bestScore = Integer.MAX_VALUE;
            int score;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        score = miniMax(field,true);
                        field.unset(i, j);
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            field.set(bestMove[0], bestMove[1]);
        }
    }

    int getScore(Symbol s) {
        switch (s) {
            case X:
                return 1;
            case O:
                return -1;
            default:
                return 0;
        }
    }

    private int miniMax(Field field, boolean isMaximizing) {
        int score;
        Symbol winner = field.getWinner();
        if (winner != null) {
            score = getScore(winner);
            return score;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        score = miniMax(field,false);
                        field.unset(i, j);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field.isFree(i, j)) {
                        field.set(i, j);
                        score = miniMax(field,true);
                        field.unset(i, j);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
