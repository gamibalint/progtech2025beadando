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
    public void kiir() {
        for (int sor = 0; sor < sorok; sor++) {
            for (int oszlop = 0; oszlop < oszlopok; oszlop++) {
                System.out.print(mezok[sor][oszlop] + " ");
            }
            System.out.println();
        }
    }
    public void lepes(int sor, int oszlop, char karakter) {
        mezok[sor][oszlop] = karakter;
    }
}
