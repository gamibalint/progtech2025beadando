package hu.gamibalint.progtech;
import java.util.Scanner;
import java.util.Random;

public class App 
{
    public static void main( String[] args)
    {
        System.out.println("Amoba Jatek indul");
        Tabla tabla = new Tabla(10, 10);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        //jatekos
        for (int kor=0; kor<5; kor++) {
            System.out.print("Add meg a sor szamat (0-9):");
            int sor = scanner.nextInt() - 1;

            System.out.print("Add meg az oszlop szamat (0-9):");
            int oszlop = scanner.nextInt() - 1;
            tabla.lepes(sor, oszlop, 'X');
            //gep
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
