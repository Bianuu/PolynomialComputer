import Prelucrari.Polynomial;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class OperationsTest {
    @Test
    public void plus() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.plus(p2);
        Assert.assertEquals("Rezultatul este corect !", " - 3.0 + 3.0x - 3.0x^2 + 3.0x^3 + 7.0x^4 + 4.0x^6 + 2.0x^7", rez.toString());

    }

    @Test
    public void plus_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.plus(p2);
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

    @Test
    public void minus() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.minus(p2);
        Assert.assertEquals("Rezultatul este corect !", "15.0 + 3.0x - 3.0x^2 + 3.0x^3 - 7.0x^4 + 4.0x^6 - 2.0x^7", rez.toString());

    }

    @Test
    public void minus_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.multiply(p2);
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

    @Test
    public void inmultire() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.multiply(p2);
        Assert.assertEquals("Rezultatul este corect !", " - 54.0 - 27.0x + 27.0x^2 - 27.0x^3 + 42.0x^4 + 21.0x^5 - 57.0x^6 + 33.0x^7 + 6.0x^8 - 6.0x^9 + 34.0x^10 + 8.0x^13", rez.toString());

    }

    @Test
    public void inmultire_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.minus(p2);
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

    @Test
    public void impartire() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        List<Polynomial> rez = p1.divide(p2);
        Assert.assertEquals("Rezultatul este corect !", "[, 6.0 + 3.0x - 3.0x^2 + 3.0x^3 + 4.0x^6]", rez.toString());

    }

    @Test
    public void impartire_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        List<Polynomial> rez = p1.divide(p2);
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

    @Test
    public void derivare() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.derivate();
        Assert.assertEquals("Rezultatul este corect !", "3.0 - 6.0x + 9.0x^2 + 24.0x^5", rez.toString());

    }

    @Test
    public void derivare_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.derivate();
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

    @Test
    public void integrare() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.integrate();
        Assert.assertEquals("Rezultatul este corect !", "6.0x + 1.5x^2 - x^3 + 0.75x^4 + 0.5714285714285714x^7", rez.toString());

    }

    @Test
    public void integrare_not() {
        Polynomial p1 = new Polynomial();
        p1.getPolynomial().put(6, 4.0);
        p1.getPolynomial().put(1, 3.0);
        p1.getPolynomial().put(2, -3.0);
        p1.getPolynomial().put(3, 3.0);
        p1.getPolynomial().put(0, 6.0);

        Polynomial p2 = new Polynomial();
        p2.getPolynomial().put(4, 7.0);
        p2.getPolynomial().put(7, 2.0);
        p2.getPolynomial().put(0, -9.0);

        Polynomial rez = p1.integrate();
        Assert.assertNotEquals("Rezultatul nu este corect !", " - 2.0 + 3.0x^2 + 2.0x^3 + 2.0x^4 + 5.0x^5", rez.toString());

    }

}