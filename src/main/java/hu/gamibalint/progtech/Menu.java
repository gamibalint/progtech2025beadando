package hu.gamibalint.progtech;
import java.util.Scanner;
import java.io.IOException;
public class Menu {
    private Jatek jatek;
    private Scanner scanner = new Scanner(System.in);
    public Menu(Jatek jatek) {
        this.jatek = jatek;
    }
    public void indit() {
        while (true) {
            System.out.println("\nAMOBA");
            System.out.println("1. Jatek inditasa");
            System.out.println("2. Betoltes fajlbol");
            System.out.println("3. Mentes fajlba");
            System.out.println("4. Kilepes");
            System.out.println("5. High score megjelenitese");
            System.out.print("Valassz: ");
            String valasz = scanner.nextLine();
            switch (valasz) {
                case "1":
                    jatek.start();
                    break;
                case "2":
                    System.out.print("Fajlnev: ");
                    String betoltFajl = scanner.nextLine();
                    try {
                        jatek.getTabla().betoltFajlbol(betoltFajl);
                        jatek.getTabla().kiir();
                    } catch (IOException e) {
                        System.out.println("Hiba: " + e.getMessage());
                    }
                    break;
                case "3":
                    System.out.print("Fajlnev (alap: jatekallas.txt): ");
                    String mentFajl = scanner.nextLine();
                    if (mentFajl.isBlank()) {
                        mentFajl = "jatekallas.txt";
                    }
                    try {
                        jatek.getTabla().mentFajlba(mentFajl);
                        System.out.println("Mentve: " + mentFajl);
                    } catch (IOException e) {
                        System.out.println("Hiba: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Kilepes.");
                    return;
                case "5":
                    jatek.highScoreKiir();
                    break;
                default:
                    System.out.println("Ervenytelen valasztas.");
            }
        }
    }
}