package hu.gamibalint.progtech;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TablaTest {
    @Test
    void uresTablaNemGyoztes() {
        Tabla tabla = new Tabla(10, 10);
        assertFalse(tabla.gyoztes('X'));
        assertFalse(tabla.gyoztes('O'));
    }
    @Test
    void otXVizszintesenGyoztes() {
        Tabla tabla = new Tabla(10, 10);
        for (int oszlop = 0; oszlop < 5; oszlop++) {
            tabla.lepes(0, oszlop, 'X');
        }
        assertTrue(tabla.gyoztes('X'));
        assertFalse(tabla.gyoztes('O'));
    }
}