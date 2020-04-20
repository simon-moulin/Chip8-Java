import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Chip8 c = new Chip8(new Ecran(new boolean[64][32]));
        c.start();
    }
}
