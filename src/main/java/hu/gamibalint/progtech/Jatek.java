package hu.gamibalint.progtech;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//https://www.w3schools.com/java/java_files_write.asp
public class Jatek {
    private boolean vanMentettJatek() {
        File file = new File("jatekallas.txt");
        return file.exists();
    }
    public void start() {
        System.out.println("Amoba Jatek indul");
        String status="folyamatban";
        Tabla tabla = new Tabla(10, 10);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        if (vanMentettJatek()) {
            try (Scanner fileScanner = new Scanner(new File("jatekallas.txt"))) {
                if (fileScanner.hasNextLine()) {
                    String sor = fileScanner.nextLine();
                    System.out.println("Elozo jatek allapota: " + sor);
                }
            } catch (IOException e) {
                System.out.println("Hiba a jatekallas fajl olvasasakor: " + e.getMessage());
            }
        } else {
            System.out.println("Nincs korabbi jatekallas");}
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
                status = "jatekos";
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
            if (tabla.gyoztes('O')) {
                tabla.kiir();
                System.out.println("A gep nyert");
                status = "gep";
                break;
            }

            tabla.kiir();
            System.out.println();
        }
        try (PrintWriter ki = new PrintWriter("jatekallas.txt")) {
            ki.println("nyertes:" + status);
        } catch (IOException e) {
            System.out.println("Nem sikerult a jatekallas fajlba irasa: " + e.getMessage());
        }
    }
}