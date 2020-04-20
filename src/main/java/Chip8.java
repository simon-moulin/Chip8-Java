import javax.swing.*;
import java.io.IOException;

public class Chip8 {

    private Ecran ecran;
    private CPU CPU;
    private long endTime;
    private long initTime;

    public Chip8(Ecran ecran){
        this.CPU = new CPU(ecran);
    }

    public void start() throws IOException {
        CPU.start();
    }

}
