package hu.gamibalint.progtech;

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Jatek {

    private Tabla tabla = new Tabla(10, 10);
    private jatekos jatekos;   //jatekos nev + jel
    private boolean vannyertes = false;
    private boolean vanMentettJatek() {
        File file = new File("jatekallas.txt");
        return file.exists();
    }
    public Tabla getTabla() {
        return tabla;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Add meg a jatekos nevet: ");
        String jatekosNev = scanner.nextLine();
        jatekos = new jatekos(jatekosNev, 'X');
        System.out.println("Nyomj Entert a kezdeshez vagy q-t a kilepeshez");
        String valasz = scanner.nextLine();
        if (valasz.equalsIgnoreCase("q")) {
            return;
        }
        System.out.println("Amoba Jatek indul");
        System.out.println();
        // induláskor: ha van fajl, betolt, ha nincs, uj tabla kozepe X-szel
        boolean folytatjuk = false;
        if (vanMentettJatek()) {
            String status = "folyamatban";
            File statusFile = new File("status.txt");
            if (statusFile.exists()) {
                try (Scanner be = new Scanner(statusFile)) {
                    if (be.hasNextLine()) {
                        status = be.nextLine().trim();
                    }
                } catch (Exception e) {
                    System.out.println("Status fajl olvasasi hiba, folytatni probalunk.");
                }
            }
            if (status.equals("folyamatban")) {
                try {
                    tabla.betoltFajlbol("jatekallas.txt");
                    System.out.println("Folyamatban levo jatek folytatasa fajlbol...");
                    folytatjuk = true;
                } catch (IOException e) {
                    System.out.println("Betoltesi hiba, uj jatek indul: " + e.getMessage());
                }
            }
        }
        if (!folytatjuk) {
            System.out.println("Új játék indul.");
            // tiszta tábla + X középen
            tabla = new Tabla(10, 10);
            tabla.lepes(5, 5, 'X');
        }
        tabla.kiir();
        System.out.println();
        while (true) {
            // jatekos
            int sor, oszlop;
            while (true) {
                System.out.print("Add meg a sor szamat (0-9): ");
                sor = scanner.nextInt();
                System.out.print("Add meg az oszlop szamat (0-9): ");
                oszlop = scanner.nextInt();
                if (tabla.helyeslepes(sor, oszlop)) {
                    break;
                } else {
                    System.out.println("Érvénytelen lépés...");
                }
            }
            tabla.lepes(sor, oszlop, 'X');
            tabla.kiir();
            // gyozelem ellenorzes
            if (tabla.gyoztes('X')) {
                System.out.println("Te nyertel!");
                vannyertes = true;
                filebaMentes();
                nyerestMent();
                break;
            }
            filebaMentes();
            //gep
            int gepsor, geposzlop;
            do {
                gepsor = random.nextInt(10);
                geposzlop = random.nextInt(10);
            } while (!tabla.uresmezo(gepsor, geposzlop));
            tabla.lepes(gepsor, geposzlop, 'O');
            tabla.kiir();

            // gyozelem ellenorzes
            if (tabla.gyoztes('O')) {
                System.out.println("A gep nyert");
                vannyertes = true;
                filebaMentes();
                break;
            }
            filebaMentes();
        }
    }

    //Fajlba menti a jatekallast
    private void filebaMentes() {
        try {
            // tabla mentese
            tabla.mentFajlba("jatekallas.txt");
            // statusz mentese kulon fajlba
            try (PrintWriter ki = new PrintWriter("status.txt", "UTF-8")) {
                ki.println(vannyertes ? "nyert" : "folyamatban");
            }
        } catch (IOException e) {
            System.out.println("Nem sikerült a játékállás fájlba írása: " + e.getMessage());
        }
    }
    private void nyerestMent() {
        if (jatekos == null) {
            return;
        }
        String fajl = "highscore.txt";
        java.util.Map<String, Integer> stat = new java.util.HashMap<>();
        // meeglevo adatok
        File f = new File(fajl);
        if (f.exists()) {
            try (Scanner be = new Scanner(f)) {
                while (be.hasNextLine()) {
                    String sor = be.nextLine().trim();
                    if (sor.isEmpty()) continue;
                    String[] darabok = sor.split(";");
                    if (darabok.length == 2) {
                        String nev = darabok[0];
                        int db = Integer.parseInt(darabok[1]);
                        stat.put(nev, db);
                    }
                }
            } catch (Exception e) {
                System.out.println("Highscore beolvasasi hiba: " + e.getMessage());
            }
        }
        // aktualis jatekos
        String nev = jatekos.getNev();
        int regi = stat.getOrDefault(nev, 0);
        stat.put(nev, regi + 1);

        // visszairas
        try (PrintWriter ki = new PrintWriter(fajl, "UTF-8")) {
            for (var entry : stat.entrySet()) {
                ki.println(entry.getKey() + ";" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Highscore mentesi hiba: " + e.getMessage());
        }
    }
    public void highScoreKiir() {
        File f = new File("highscore.txt");
        if (!f.exists()) {
            System.out.println("Meg nincs highscore adat.");
            return;
        }
        java.util.Map<String, Integer> stat = new java.util.HashMap<>();
        try (Scanner be = new Scanner(f)) {
            while (be.hasNextLine()) {
                String sor = be.nextLine().trim();
                if (sor.isEmpty()) continue;
                String[] darabok = sor.split(";");
                if (darabok.length == 2) {
                    String nev = darabok[0];
                    int db = Integer.parseInt(darabok[1]);
                    stat.put(nev, db);
                }
            }
        } catch (Exception e) {
            System.out.println("Highscore beolvasasi hiba: " + e.getMessage());
            return;
        }

        System.out.println("\n HIGH SCORE ");
        System.out.println("Jatekos\tNyeresek");
        for (var entry : stat.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        System.out.println("-----------------\n");
    }
}