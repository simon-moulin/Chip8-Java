public class CPU {

    private byte memoire[];
    private byte V[]; // registe
    private short I; // stocke une adresse memoire
    private short saut[]; //pile
    private byte nbSaut;
    private byte compteurJeu;
    private byte compteurSon;
    private short pc;

    public CPU (){
        this.memoire = new byte[4096];
        this.V = new byte[16];
        this.saut = new short[16];

        this.pc = 512;
        this.nbSaut = 0;
        this.compteurJeu = 0;
        this.compteurSon = 0;
        this.I = 0;

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


}
