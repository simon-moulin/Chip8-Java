import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.Arrays;

public class Ecran extends JPanel {

    private Graphics g;
    private int scale = 10; //10 pixels for each emulated-system pixel.
    private int width = 64 * scale;
    private int height = 32 * scale;

    private boolean[][] pixels;

    public Ecran(boolean[][] pixels) {
        this.pixels = pixels;
    }


    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void dessinerPixel(boolean white, int x, int y) {
        if (white) {
            g.setColor(Color.WHITE);
        }else {
            g.setColor(Color.BLACK);
        }
        g.fillRect(x*scale, y*scale, scale,scale);
    }

    private void updateEcran(){
        for (int y = 0; y < 32; y++) {
            for (int x = 0; x < 64; x++) {
                boolean value = pixels[x][y];
                dessinerPixel(value, x, y);
            }
        }
    }

    public void  effacerEcran() {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j] = false;
            }
        }
    }

    public void setPixels(boolean value, int x,int y) {
        this.pixels[x][y] = value;
    }

    public void paintScreen() {
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        updateEcran();
    }

    public boolean getPixel(byte x, byte y) {
        return this.pixels[x][y];
    }
}
