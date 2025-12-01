package hu.gamibalint.progtech;
import java.util.Scanner;
import java.util.Random;

public class Jatek {
    private boolean mentettjatek() {
        java.io.File file = new java.io.File("jatekallas.txt");
        return file.exists();
    }
    public void start() {
        System.out.println("Amoba Jatek indul");
        if (mentettjatek()) {
            System.out.println("Van jatekallas.txt fajl");
        } else {
            System.out.println("Nincs jatekallas fajl");
        }
        Tabla tabla = new Tabla(10, 10);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        java.util.Random random = new java.util.Random();
        int kozepSor = 5;
        int kozepOszlop = 5;
        tabla.lepes(kozepSor, kozepOszlop, 'X');
        tabla.kiir();
        System.out.println();

        while (true) {
            // jatekos
            int sor;
            int oszlop;
            while (true) {
                System.out.print("Add meg a sor szamat (0-9): ");
                sor = scanner.nextInt();
                System.out.print("Add meg az oszlop szamat (0-9): ");
                oszlop = scanner.nextInt();
                if (tabla.helyeslepes(sor, oszlop)) {
                    break;
                } else {
                    System.out.println("Ervenytelen lepes...");
                }
            }
            tabla.lepes(sor, oszlop, 'X');
            if (tabla.gyoztes('X')) {
                tabla.kiir();
                System.out.println("Te nyertel");
                break;
            }
            // gep
            int gepsor;
            int geposzlop;
            do {
                gepsor = random.nextInt(10);
                geposzlop = random.nextInt(10);
            } while (!tabla.uresmezo(gepsor, geposzlop));

            tabla.lepes(gepsor, geposzlop, 'O');
            if (tabla.gyoztes('X')) {
                tabla.kiir();
                System.out.println("A gep nyert");
                break;
            }

            tabla.kiir();
            System.out.println();
        }
    }
}
