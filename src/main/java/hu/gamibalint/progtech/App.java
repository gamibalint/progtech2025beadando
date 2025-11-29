package hu.gamibalint.progtech;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Amoba Jatek indul");
        Tabla tabla = new Tabla(10, 10);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Add meg a sor szamat (0-9):");
        int sor = scanner.nextInt()-1;

        System.out.print("Add meg az oszlop szamat (0-9):");
        int oszlop = scanner.nextInt()-1;

        tabla.lepes(sor,oszlop,'X');
        tabla.kiir();
    }
}
