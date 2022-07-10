import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Miner extends JFrame {

    private Game game;

    private JPanel panel;
    private JLabel label;
    private final int COLS = 5;
    private final int ROWS = 5;
    private final int BOMBS = 7;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new Miner();
    }

    private Miner(){
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImage();
        initLabel();
        initPanel();
        initFrame();
    }
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Miner");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }
    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(coord);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }
    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }
    private Image getImage(String name) {
        String path = name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        return icon.getImage();
    }
    private void setImage() {
        for(Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }
    private String getMessage() {
        switch (game.getGameStatus()) {
            case BOMBED: return "You're looser! BOOOOOOM";
            case PLAYED: return "Make your choice";
            case WINNER: return "Congratulation! You are beautiful";
            default    : return "Welcome!";
        }
    }
}

