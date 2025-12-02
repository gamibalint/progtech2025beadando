package hu.gamibalint.progtech;

public class App {
    public static void main(String[] args) {
        Jatek jatek = new Jatek();
        Menu menu = new Menu(jatek);
        menu.indit();
    }
}
