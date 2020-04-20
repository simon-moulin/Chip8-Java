import javax.swing.*;

public class Chip8 {

    private Ecran ecran;
    private CPU CPU;
    private long endTime;
    private long initTime;

    public Chip8(Ecran ecran, CPU CPU){
        this.ecran = ecran;
        this.CPU = CPU;
    }

    private void prepareGUI(){
        JFrame f = new JFrame("CHIP-8 emulator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this.ecran);
        f.pack();
        f.setVisible(true);
        ecran.paintScreen();
    }

    public void start() {

        prepareGUI();
        do {
            this.initTime = System.nanoTime();

            //operation

            ecran.paintScreen();


            endTime = System.nanoTime();
            waitForCompleteCycle(endTime,initTime);
        } while (true);
    }


    private void waitForCompleteCycle(long endTime, long initTime){

        long nanosToWait= 16000000 - (endTime - initTime);
        System.out.println(nanosToWait);
        long initNanos = System.nanoTime();
        long targetNanos = initNanos + nanosToWait;
        while(System.nanoTime()<targetNanos){
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
