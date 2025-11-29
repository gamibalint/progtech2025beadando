package hu.gamibalint.progtech;

public class Tabla {

    private final int sorok;
    private final int oszlopok;
    private final char[][] mezok;

    public Tabla(int sorok, int oszlopok) {
        this.sorok = sorok;
        this.oszlopok = oszlopok;
        this.mezok = new char[sorok][oszlopok];
        for (int sor = 0; sor < sorok; sor++) {
            for (int oszlop = 0; oszlop < oszlopok; oszlop++) {
                mezok[sor][oszlop] = '.';
            }
        }
    }
}
