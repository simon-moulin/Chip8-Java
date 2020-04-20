public class Jump {

    private short[] masque;
    private short[] id;

    public Jump() {
        this.masque = new short[35];
        this.id = new short[35];
        initialiserJump();
    }

    public short[] getMasque() {
        return masque;
    }

    public short[] getId() {
        return id;
    }

    private void initialiserJump ()
    {
        this.masque[0]= 0x0000; this.id[0]=0x0FFF;          /* 0NNN */
        this.masque[1]= (short) 0xFFFF; this.id[1]= (short)0x00E0;          /* 00E0 */
        this.masque[2]= (short)0xFFFF; this.id[2]=(short)0x00EE;          /* 00EE */
        this.masque[3]= (short)0xF000; this.id[3]=(short)0x1000;          /* 1NNN */
        this.masque[4]= (short)0xF000; this.id[4]=(short)0x2000;          /* 2NNN */
        this.masque[5]= (short)0xF000; this.id[5]=(short)0x3000;          /* 3XNN */
        this.masque[6]= (short)0xF000; this.id[6]=(short)0x4000;          /* 4XNN */
        this.masque[7]= (short)0xF00F; this.id[7]=(short)0x5000;          /* 5XY0 */
        this.masque[8]= (short)0xF000; this.id[8]=(short)0x6000;          /* 6XNN */
        this.masque[9]= (short)0xF000; this.id[9]=(short)0x7000;          /* 7XNN */
        this.masque[10]= (short)0xF00F; this.id[10]=(short)0x8000;          /* 8XY0 */
        this.masque[11]= (short)0xF00F; this.id[11]=(short)0x8001;          /* 8XY1 */
        this.masque[12]= (short)0xF00F; this.id[12]=(short)0x8002;          /* 8XY2 */
        this.masque[13]= (short)0xF00F; this.id[13]=(short)0x8003;          /* BXY3 */
        this.masque[14]= (short)0xF00F; this.id[14]=(short)0x8004;          /* 8XY4 */
        this.masque[15]= (short)0xF00F; this.id[15]=(short)0x8005;          /* 8XY5 */
        this.masque[16]= (short)0xF00F; this.id[16]=(short)0x8006;          /* 8XY6 */
        this.masque[17]= (short)0xF00F; this.id[17]=(short)0x8007;          /* 8XY7 */
        this.masque[18]= (short)0xF00F; this.id[18]=(short)0x800E;          /* 8XYE */
        this.masque[19]= (short)0xF00F; this.id[19]=(short)0x9000;          /* 9XY0 */
        this.masque[20]= (short)0xF000; this.id[20]=(short)0xA000;          /* ANNN */
        this.masque[21]= (short)0xF000; this.id[21]=(short)0xB000;          /* BNNN */
        this.masque[22]= (short)0xF000; this.id[22]=(short)0xC000;          /* CXNN */
        this.masque[23]= (short)0xF000; this.id[23]=(short)0xD000;          /* DXYN */
        this.masque[24]= (short)0xF0FF; this.id[24]=(short)0xE09E;          /* EX9E */
        this.masque[25]= (short)0xF0FF; this.id[25]=(short)0xE0A1;          /* EXA1 */
        this.masque[26]= (short)0xF0FF; this.id[26]=(short)0xF007;          /* FX07 */
        this.masque[27]= (short)0xF0FF; this.id[27]=(short)0xF00A;          /* FX0A */
        this.masque[28]= (short)0xF0FF; this.id[28]=(short)0xF015;          /* FX15 */
        this.masque[29]= (short)0xF0FF; this.id[29]=(short)0xF018;          /* FX18 */
        this.masque[30]= (short)0xF0FF; this.id[30]=(short)0xF01E;          /* FX1E */
        this.masque[31]= (short)0xF0FF; this.id[31]=(short)0xF029;          /* FX29 */
        this.masque[32]= (short)0xF0FF; this.id[32]=(short)0xF033;          /* FX33 */
        this.masque[33]= (short)0xF0FF; this.id[33]=(short)0xF055;          /* FX55 */
        this.masque[34]= (short)0xF0FF; this.id[34]=(short)0xF065;          /* FX65 */

    }

}
