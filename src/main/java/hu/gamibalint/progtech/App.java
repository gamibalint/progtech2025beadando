package hu.gamibalint.progtech;
import java.util.Scanner;
import java.util.Random;

public class App {
        public static void main(String[] args) {
            System.out.println("Amoba Jatek indul");
            Tabla tabla = new Tabla(10, 10);
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

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
                // gep
                int gepsor;
                int geposzlop;
                do {
                    gepsor = random.nextInt(10);
                    geposzlop = random.nextInt(10);
                } while (!tabla.uresmezo(gepsor, geposzlop));

                tabla.lepes(gepsor, geposzlop, 'O');

                tabla.kiir();
                System.out.println();
            }
        }
    }
