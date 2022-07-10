public class Matrix {
    private static Box [][] matrix;

    public Matrix(Box defaultBox) {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords()) {
            matrix [coord.x][coord.y] = defaultBox;
        }
    }

    public Box get(Coord coord) {
        if (Ranges.inRanges(coord)) {
            return matrix[coord.x][coord.y];
        }
        return null;
    }

    public void set(Coord coord, Box box) {
        if (Ranges.inRanges(coord)) {
            matrix[coord.x][coord.y] = box;
        }
    }
}
