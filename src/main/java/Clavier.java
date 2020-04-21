import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener {

    public boolean[] pressed;

    public Clavier(){
        pressed = new boolean[16];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        setKey(true, keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        setKey(false, keyEvent.getKeyCode());
    }

    private void setKey(boolean value, int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_1:
                pressed[0x1] = value;
                break;
            case KeyEvent.VK_2:
                pressed[0x2] = value;
                break;
            case KeyEvent.VK_3:
                pressed[0x3] = value;
                break;
            case KeyEvent.VK_4:
                pressed[0xC] = value;
                break;
            case KeyEvent.VK_A:
                pressed[0x4] = value;
                break;
            case KeyEvent.VK_Z:
                pressed[0x5] = value;
                break;
            case KeyEvent.VK_E:
                pressed[0x6] = value;
                break;
            case KeyEvent.VK_R:
                pressed[0xD] = value;
                break;
            case KeyEvent.VK_Q:
                pressed[0x7] = value;
                break;
            case KeyEvent.VK_S:
                pressed[0x8] = value;
                break;
            case KeyEvent.VK_D:
                pressed[0x9] = value;
                break;
            case KeyEvent.VK_F:
                pressed[0xE] = value;
                break;
            case KeyEvent.VK_W:
                pressed[0xA] = value;
                break;
            case KeyEvent.VK_X:
                pressed[0x0] = value;
                break;
            case KeyEvent.VK_C:
                pressed[0xB] = value;
                break;
            case KeyEvent.VK_V:
                pressed[0xF] = value;
                break;
        }
    }
}
