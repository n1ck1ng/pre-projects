import java.util.Objects;
import java.util.Vector;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coord) {
            Coord to = (Coord) o;
            return (to.x == this.x && to.y == this.y);
        } return super.equals(o);
    }

}
