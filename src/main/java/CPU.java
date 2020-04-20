import javax.swing.*;
import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class CPU {

    private byte[] memoire;
    private byte[] V; // registe
    private short I; // stocke une adresse memoire
    private short[] saut; //pile
    private byte nbSaut;
    private byte compteurJeu;
    private byte compteurSon;
    private short pc;
    private Jump jump;
    private Ecran ecran;
    private Random random ;
    private long endTime;
    private long initTime;

    public CPU (Ecran ecran){
        this.memoire = new byte[4096];
        this.V = new byte[16];
        this.saut = new short[16];

        this.pc = 512;
        this.nbSaut = 0;
        this.compteurJeu = 0;
        this.compteurSon = 0;
        this.I = 0;
        this.ecran = ecran;
        this.random = new Random();

        this.jump = new Jump();
        chargerFont();
    }
    private void chargerFont()
    {
        this.memoire[0]= (byte) 0xF0;this.memoire[1]= (byte) 0x90;this.memoire[2]= (byte) 0x90;this.memoire[3]= (byte) 0x90; this.memoire[4]= (byte) 0xF0; // O

        this.memoire[5]= (byte)0x20;this.memoire[6]= (byte) 0x60;this.memoire[7]=(byte)0x20;this.memoire[8]= (byte) 0x20;this.memoire[9]= (byte)0x70; // 1

        this.memoire[10]= (byte) 0xF0;this.memoire[11]= (byte) 0x10;this.memoire[12]= (byte) 0xF0;this.memoire[13]= (byte) 0x80; this.memoire[14]= (byte) 0xF0; // 2

        this.memoire[15]= (byte) 0xF0;this.memoire[16]= (byte) 0x10;this.memoire[17]= (byte) 0xF0;this.memoire[18]= (byte)0x10;this.memoire[19]= (byte) 0xF0; // 3

        this.memoire[20]= (byte) 0x90;this.memoire[21]= (byte) 0x90;this.memoire[22]= (byte) 0xF0;this.memoire[23]=(byte) 0x10;this.memoire[24]=(byte)0x10; // 4

        this.memoire[25]= (byte) 0xF0;this.memoire[26]= (byte) 0x80;this.memoire[27]= (byte) 0xF0;this.memoire[28]= (byte) 0x10;this.memoire[29]= (byte) 0xF0; // 5

        this.memoire[30]= (byte) 0xF0;this.memoire[31]= (byte) 0x80;this.memoire[32]= (byte) 0xF0;this.memoire[33]= (byte) 0x90;this.memoire[34]= (byte) 0xF0; // 6

        this.memoire[35]= (byte) 0xF0;this.memoire[36]= (byte)0x10;this.memoire[37]= (byte) 0x20;this.memoire[38]= (byte) 0x40;this.memoire[39]=(byte)0x40; // 7

        this.memoire[40]= (byte) 0xF0;this.memoire[41]= (byte) 0x90;this.memoire[42]= (byte) 0xF0;this.memoire[43]= (byte) 0x90;this.memoire[44]= (byte) 0xF0; // 8

        this.memoire[45]= (byte) 0xF0;this.memoire[46]= (byte) 0x90;this.memoire[47]= (byte) 0xF0;this.memoire[48]= (byte) 0x10;this.memoire[49]= (byte) 0xF0; // 9

        this.memoire[50]= (byte) 0xF0;this.memoire[51]= (byte) 0x90;this.memoire[52]= (byte) 0xF0;this.memoire[53]= (byte) 0x90;this.memoire[54]= (byte) 0x90; // A

        this.memoire[55]= (byte) 0xE0;this.memoire[56]= (byte) 0x90;this.memoire[57]= (byte) 0xE0;this.memoire[58]= (byte) 0x90;this.memoire[59]= (byte) 0xE0; // B

        this.memoire[60]= (byte) 0xF0;this.memoire[61]= (byte) 0x80;this.memoire[62]= (byte) 0x80;this.memoire[63]= (byte) 0x80;this.memoire[64]= (byte) 0xF0; // C

        this.memoire[65]= (byte) 0xE0;this.memoire[66]= (byte) 0x90;this.memoire[67]= (byte) 0x90;this.memoire[68]= (byte) 0x90;this.memoire[69]= (byte) 0xE0; // D

        this.memoire[70]= (byte) 0xF0;this.memoire[71]= (byte) 0x80;this.memoire[72]= (byte) 0xF0;this.memoire[73]= (byte) 0x80;this.memoire[74]= (byte) 0xF0; // E

        this.memoire[75]= (byte) 0xF0;this.memoire[76]= (byte) 0x80;this.memoire[77]= (byte) 0xF0;this.memoire[78]= (byte) 0x80;this.memoire[79]= (byte) 0x80; // F

    }

    private void prepareGUI(){
        JFrame f = new JFrame("CHIP-8 emulator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this.ecran);
        f.pack();
        f.setVisible(true);
        ecran.paintScreen();
    }


    public void chargerJeu() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("roms/C8PIC.ch8"));
        short currentAddress = (short)0x200;
        int loadedBytes = 0;
        for(byte b: bytes){
            memoire[currentAddress] = b;
            loadedBytes++;
            currentAddress = (short)(currentAddress +0x1);

        }
        System.out.println("[INFO] ROM \" Maze \" loaded in memory starting at 0x200 ("+loadedBytes+" Bytes).");

    }

    public void start() throws IOException {

        prepareGUI();
        chargerJeu();
        do {
            this.initTime = System.nanoTime();

            //operation

            for (int i = 0; i < 4; i++) {
                interpreterOpcode(recupererOpcode());
            }
            ecran.paintScreen();
            decompter();
            endTime = System.nanoTime();
            waitForCompleteCycle(endTime,initTime);
        } while (true);
    }


    private void waitForCompleteCycle(long endTime, long initTime){

        long nanosToWait= 16000000 - (endTime - initTime);
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

    public void decompter() {
        if(this.compteurJeu>0)
            this.compteurJeu--;

        if(this.compteurSon>0)
            this.compteurSon--;
    }

    public short recupererOpcode(){
        return (short) ((this.memoire[this.pc]<<8)+this.memoire[this.pc+1]);
    }

    private byte recupererAction(short opcode) {
        byte action;
        short resultat;

        for(action=0; action< 34; action++){
            resultat = (short) (jump.getMasque()[action] & opcode);

            if(resultat == jump.getId()[action]) {
                break;
            }
        }

        return action;
    }

    private void interpreterOpcode(short opcode) {
        byte b4,b3,b2,b1;

        b3= (byte) ((opcode&(0x0F00))>>8);  //on prend les 4 bits, b3 représente X
        b2= (byte) ((opcode&(0x00F0))>>4);  //idem, b2 représente Y
        b1= (byte) (opcode&(0x000F));     //on prend les 4 bits de poids faible

        b4 = recupererAction(opcode);

        System.out.println( b4+ " " +b3+ " "+ b2+ " "+ b1);

        switch(b4) {
            case 0: {
                //Cet opcode n'est pas implémenté
                break;
            }
            case 1: {
                //00E0 : efface l'écran
                ecran.effacerEcran();
                break;
            }

            case 2: {//00EE : revient du saut
                if(nbSaut > 0) {
                    nbSaut--;
                    pc = saut[nbSaut];
                }

                break;
            }
            case 3: { //1NNN : effectue un saut à l'adresse 1NNN
                pc= (short) ((b3<<8)+(b2<<4)+b1);
                pc-=2;
                break;
            }
            case 4: {
                //2NNN : appelle le sous-programme en NNN, mais on revient ensuite
                saut[nbSaut] = pc;

                if(nbSaut< 15) {
                    nbSaut++;
                }
                pc= (short) ((b3<<8)+(b2<<4)+b1); //on prend le nombre NNN (pour le saut)
                pc-=2; //n'oublions pas le pc+=2 à la fin du block switch


                break;
            }
            case 5:{//3XNN saute l'instruction suivante si VX est égal à NN.

                if(V[b3] == ((b2<<4) + b1)) {
                    pc +=2;
                }


                break;
            }
            case 6:{//4XNN saute l'instruction suivante si VX et NN ne sont pas égaux.
                if(V[b3] != (b2<<4+ b1)) {
                    pc +=2;
                }

                break;
            }
            case 7:{
                //5XY0 saute l'instruction suivante si VX et VY sont égaux.

                if(V[b3] == V[b2]) {
                    pc+=2;
                }

                break;
            }

            case 8:{
                //6XNN définit VX à NN.

                V[b3] = (byte) ((b2 <<4) + b1);

                break;
            }
            case 9:{
                //7XNN ajoute NN à VX.

                V[b3] += (b2<< 4) + b1;

                break;
            }
            case 10:{
                //8XY0 définit VX à la valeur de VY.
                V[b3]=V[b2];
                break;
            }
            case 11:{
                //8XY1 définit VX à VX OR VY.
                V[b3] = (byte) (V[b3] | V[b2]);

                break;
            }
            case 12:{
                //8XY2 définit VX à VX AND VY.

                V[b3] = (byte) (V[b3] & V[b2]);
                break;
            }
            case 13:{
                //8XY3 définit VX à VX XOR VY.
                V[b3] = (byte) (V[b3] ^ V[b2]);

                break;
            }
            case 14:{
                //8XY4 ajoute VY à VX. VF est mis à 1 quand il y a un dépassement de mémoire (carry), et à 0 quand il n'y en pas.
                if((V[b3] + V[b2]) > 255) {
                    V[0xF] = 1;
                }else {
                    V[0xF] = 0;
                }
                V[b3] += V[b2];

                break;
            }
            case 15:{
                //8XY5 VY est soustraite de VX. VF est mis à 0 quand il y a un emprunt, et à 1 quand il n'y a en pas.
                if((V[b3]<V[b2]))
                {
                    V[0xF]=0; //cpu.V[15]
                }
                else
                {
                    V[0xF]=1; //cpu.V[15]
                }
                V[b3]-=V[b2];

                break;
            }
            case 16:{

                //8XY6 décale (shift) VX à droite de 1 bit. VF est fixé à la valeur du bit de poids faible de VX avant le décalage.
                V[0xF]= (byte) (V[b3]&(0x01));
                V[b3]= (byte) (V[b3]>>1);

                break;
            }
            case 17:{
                //8XY7 VX = VY - VX. VF est mis à 0 quand il y a un emprunt et à 1 quand il n'y en a pas.

                if((V[b2] < V[b3])) {
                    V[0xF] = 0;
                }else {
                    V[0xF] = 1;
                }

                V[b3] = (byte) (V[b2] - V[b3]);

                break;
            }
            case 18:{
                //8XYE décale (shift) VX à gauche de 1 bit. VF est fixé à la valeur du bit de poids fort de VX avant le décalage.
                V[0xF]= (byte) (V[b3]>>7);
                V[b3]= (byte) (V[b3]<<1);

                break;
            }

            case 19:{

                //9XY0 saute l'instruction suivante si VX et VY ne sont pas égaux.

                if(V[b3]!=V[b2])
                {
                    pc+=2;
                }
                break;
            }
            case 20:{
                //ANNN affecte NNN à I.
                I = (short) ((b3 << 8) + (b2 << 4) + b1);


                break;
            }
            case 21:{
                //BNNN passe à l'adresse NNN + V0.

                pc= (short) ((b3<<8)+(b2<<4)+b1+V[0]);
                pc-=2;

                break;

            }
            case 22:{

                //CXNN définit VX à un nombre aléatoire inférieur à NN.
                V[b3] = (byte) ((byte) random.nextInt(266) & ((b2<<4) +b1));
                break;

            }

            case 23:{
                //DXYN dessine un sprite aux coordonnées (VX, VY).

                dessinerEcran(b1,b2,b3);

                break;

            }
            case 24:{
                //EX9E saute l'instruction suivante si la clé stockée dans VX est pressée.


                break;
            }
            case 25:{
                //EXA1 saute l'instruction suivante si la clé stockée dans VX n'est pas pressée.

                break;
            }

            case 26:{
                //FX07 définit VX à la valeur de la temporisation.
                V[b3]=compteurJeu;

                break;
            }
            case 27:{
                //FX0A attend l'appui sur une touche et la stocke ensuite dans VX.


                break;
            }


            case 28:{
                //FX15 définit la temporisation à VX.
                compteurJeu=V[b3];

                break;
            }
            case 29:{
                //FX18 définit la minuterie sonore à VX.
                compteurSon=V[b3];

                break;
            }
            case 30:{
                //FX1E ajoute à VX I. VF est mis à 1 quand il y a overflow (I+VX>0xFFF), et à 0 si tel n'est pas le cas.
                if((I+V[b3])>0xFFF)
                {
                    V[0xF]=1;
                }
                else
                {
                    V[0xF]=0;
                }
                I+=V[b3];

                break;
            }

            case 31:{
                //FX29 définit I à l'emplacement du caractère stocké dans VX. Les caractères 0-F (en hexadécimal) sont représentés par une police 4x5.

                I= (short) (V[b3]*5);
                break;
            }

            case 32:{
                //FX33 stocke dans la mémoire le code décimal représentant VX (dans I, I+1, I+2).

                memoire[I]= (byte) ((V[b3]-V[b3]%100)/100); //stocke les centaines
                memoire[I+1]= (byte) (((V[b3]-V[b3]%10)/10)%10);//les dizaines
                memoire[I+2]= (byte) (V[b3]-memoire[I]*100-memoire[I+1]*10);//les unités

                break;
            }
            case 33:{

                //FX55 stocke V0 à VX en mémoire à partir de l'adresse I.
                for(byte i=0;i<=b3;i++)
                {
                    memoire[I+i]=V[i];
                }

                break;
            }
            case 34:{
                //FX65 remplit V0 à VX avec les valeurs de la mémoire à partir de l'adresse I.
                for(byte i=0;i<=b3;i++)
                {
                    V[i]=memoire[I+i];
                }


                break;
            }

            default: {
                break;
            }

        }

        pc+=2;
    }

    void dessinerEcran(byte b1,byte b2, byte b3)
    {
        byte x=0,y=0,k=0,codage=0,j=0,decalage=0;
        this.V[0xF]=0;

        for(k=0;k<b1;k++)
        {
            codage=memoire[I+k];//on récupère le codage de la ligne à dessiner

            y= (byte) ((V[b2]+k)%32);//on calcule l'ordonnée de la ligne à dessiner, on ne doit pas dépasser L

            for(j=0,decalage=7;j<8;j++,decalage--)
            {
                x= (byte) ((V[b3]+j)%64); //on calcule l'abscisse, on ne doit pas dépasser l

                if(((codage)&(0x1<<decalage))!=0)//on récupère le bit correspondant
                {   //si c'est blanc
                    if(ecran.getPixel(x, y))// On eteint
                    {
                        ecran.setPixels(false, x,y);
                        V[0xF]=1; //il y a donc collusion

                    }
                    else //sinon
                    {
                        ecran.setPixels(true, x,y); // On allume
                    }


                }

            }
        }

    }

}
