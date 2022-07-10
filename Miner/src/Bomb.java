class Bomb {
    private Matrix mapBomb;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }
    void start() {
        mapBomb = new Matrix(Box.ZERO);
        for (int i = 0 ; i < totalBomb; i++) {
            placeBomb();
        }
    }
    public int getTotalBomb() {
        return totalBomb;
    }

    Box get(Coord coord) {
        return mapBomb.get(coord);
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.randomCoord();
            if (mapBomb.get(coord) == Box.BOMB) {
                continue;
            }
            mapBomb.set(coord, Box.BOMB);
            incNumbersAround(coord);
            break;
        }
    }

    public void incNumbersAround(Coord coord) {
        for (Coord arround : Ranges.getCoordsAround(coord)) {
            if (mapBomb.get(arround) != Box.BOMB) {
                mapBomb.set(arround, mapBomb.get(arround).nextNumberBox());
            }
        }
    }
    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBomb > maxBombs) {
            totalBomb = maxBombs;
        }
    }
}
