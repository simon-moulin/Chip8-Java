import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Chip8 c = new Chip8(new Ecran(new boolean[64][32]), new CPU());
        c.start();
    }
}
