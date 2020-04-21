import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Son {

    private String path;
    private File fichier;

    public Son (String path) {
        this.path = path;
        this.fichier = new File(path);
    }

    public void play() {
        try{
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(this.fichier));
            c.start();
            Thread.sleep(c.getMicrosecondLength()/1000);
        } catch (Exception e){

        }
    }

}
