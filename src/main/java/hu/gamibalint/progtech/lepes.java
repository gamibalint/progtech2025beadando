package hu.gamibalint.progtech;

public final class lepes {
    private final int sor;
    private final int oszlop;
    private final char jel;

    public lepes(int sor, int oszlop, char jel) {
        this.sor = sor;
        this.oszlop = oszlop;
        this.jel = jel;
    }
    public int getSor() {
        return sor;
    }
    public int getOszlop() {
        return oszlop;
    }
    public char getJel() {
        return jel;
    }

    @Override
    public String toString() {
        return "lepes{" +
                "sor=" + sor +
                ", oszlop=" + oszlop +
                ", jel=" + jel +
                '}';
    }
}
