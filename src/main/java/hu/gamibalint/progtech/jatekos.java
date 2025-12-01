package hu.gamibalint.progtech;
public final class jatekos {
    private final String nev;
    private final char jel;
    public jatekos(String nev, char jel) {
        this.nev = nev;
        this.jel = jel;
    }
    public String getNev() {
        return nev;
    }
    public char getJel() {
        return jel;
    }
    @Override
    public String toString() {
        return "jatekos{" +
                "nev='" + nev + '\'' +
                ", jel=" + jel +
                '}';
    }
}
