public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameStatus gameStatus;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }


    public void start() {
        bomb.start();
        flag.start();
        gameStatus = GameStatus.PLAYED;
    }
    private void checkWinner() {
        if (gameStatus == GameStatus.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBomb()) {
                gameStatus = GameStatus.WINNER;
            }
        }
    }
    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }
    public void pressLeftButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        openBox(coord);
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) {
            return;
        }
        flag.setFlagToBox(coord);
    }


    private void openBox(Coord coord) {
         switch (flag.get(coord)) {
             case OPENED:
                 setOpenedToClosedBoxesAroundNumber(coord);
                 return;
             case FLAGED: return;
             case CLOSED:
                 switch (bomb.get(coord)) {
                     case BOMB:
                         openBombs(coord);
                         return;
                     case ZERO:
                         openBoxesAround(coord);
                         return;
                     default  :
                         flag.setOpenedToBox(coord);
                         return;

                 }

         }
    }

    private void openBombs(Coord bombed) {
        gameStatus = GameStatus.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMBED) {
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord); 
        for (Coord around : Ranges.getCoordsAround(coord)) {
            //flag.setOpenedToBox(around);
            openBox(around);
        }


    }

    void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (bomb.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coord around : Ranges.getCoordsAround(coord)) {
                    if (flag.get(around) == Box.CLOSED) {
                        openBox(around);
                    }
                }
            }
        }
    }

    private boolean gameOver() {
        if (gameStatus == GameStatus.PLAYED) {
            return false;
        }
        start();
        return true;
    }
}
