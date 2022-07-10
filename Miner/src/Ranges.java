import java.security.SecureRandom;
import java.util.ArrayList;

public class Ranges {
    private static Coord size;
    private static ArrayList<Coord> allCoords;
    static SecureRandom random = new SecureRandom();

    public static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<Coord>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++)
                allCoords.add(new Coord(x, y));
        }
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCoords;
    }
    public static boolean inRanges(Coord coord) {
        return (coord.y >= 0 && coord.x >= 0 && coord.x < size.x && coord.y < size.y);
    }
    public static Coord randomCoord() {
        return new Coord(random.nextInt(size.x), random.nextInt(size.y));
    }
    public static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        for (int x = coord.x - 1; x <= coord.x + 1; x++) {
            for (int y = coord.y - 1; y <= coord.y + 1; y++) {
                if (inRanges(around = new Coord(x,y))) {
                    if (!around.equals(coord)) {
                        list.add(around);
                    }
                }
            }
        }
        return list;
    }
}
