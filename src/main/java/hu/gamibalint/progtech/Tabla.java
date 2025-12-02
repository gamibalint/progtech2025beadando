package hu.gamibalint.progtech;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;
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
    public char[][] getMezok() {
        return mezok;
    }
    public int getSorok() {
        return sorok;
    }
    public int getOszlopok() {
        return oszlopok;
    }
    public void kiir() {
        for (int sor = 0; sor < sorok; sor++) {
            for (int oszlop = 0; oszlop < oszlopok; oszlop++) {
                System.out.print(mezok[sor][oszlop] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void lepes(int sor, int oszlop, char karakter) {
        mezok[sor][oszlop] = karakter;
    }

    public boolean uresmezo(int sor, int oszlop) {
        return mezok[sor][oszlop] == '.';
    }

    // Helyes lepes ellenorzes
    public boolean helyeslepes(int sor, int oszlop) {
        if (sor < 0 || sor >= sorok || oszlop < 0 || oszlop >= oszlopok) {
            return false;
        }
        if (mezok[sor][oszlop] != '.') {
            return false;
        }
        // https://stackoverflow.com/questions/2035522/get-adjacent-elements-in-a-two-dimensional-array
        for (int ds = -1; ds <= 1; ds++) {
            for (int dosz = -1; dosz <= 1; dosz++) {
                if (ds == 0 && dosz == 0) {
                    continue;
                }
                int ns = sor + ds;
                int no = oszlop + dosz;
                if (ns < 0 || ns >= sorok || no < 0 || no >= oszlopok) {
                    continue;
                }
                if (mezok[ns][no] != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean gyoztes(int sor, int oszlop, int ds, int doSz, char jel) {
        int db = 0;
        int s = sor;
        int o = oszlop;
        while (s >= 0 && s < sorok && o >= 0 && o < oszlopok) {
            if (mezok[s][o] == jel) {
                db++;
                if (db == 5) {
                    return true;
                }
            } else {
                db = 0;
            }
            s += ds;
            o += doSz;
        }
        return false;
    }

    public boolean gyoztes(char jel) {
        for (int sor = 0; sor < sorok; sor++) {
            for (int oszlop = 0; oszlop < oszlopok; oszlop++) {
                if (gyoztes(sor, oszlop, 0, 1, jel)) return true;//vizszint
                if (gyoztes(sor, oszlop, 1, 0, jel)) return true;//fuggo
                if (gyoztes(sor, oszlop, 1, 1, jel)) return true;//atlo 1
                if (gyoztes(sor, oszlop, 1, -1, jel)) return true;//atlo 2
            }
        }
        return false;
    }

    //https://www.w3schools.com/java/java_files_write.asp
    //mentes
    public void mentFajlba(String fajlnev) throws IOException {
        try (PrintWriter ki = new PrintWriter(fajlnev)) {
            for (int sor = 0; sor < sorok; sor++) {
                for (int oszlop = 0; oszlop < oszlopok; oszlop++) {
                    ki.print(mezok[sor][oszlop]);
                }
                ki.println();
            }
        }
    }
    //betoltes
    public void betoltFajlbol(String fajlnev) throws IOException {
        try (Scanner be = new Scanner(new File(fajlnev))) {
            for (int sor = 0; sor < sorok; sor++) {
                if (be.hasNextLine()) {
                    String sorszoveg = be.nextLine();
                    for (int oszlop = 0; oszlop < oszlopok && oszlop < sorszoveg.length(); oszlop++) {
                        char c = sorszoveg.charAt(oszlop);
                        mezok[sor][oszlop] = (c == '.' || c == 'X' || c == 'O') ? c : '.';
                    }
                }
            }
        }
    }
}