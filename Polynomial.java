package Prelucrari;

import javax.swing.*;
import java.util.*;

public class Polynomial {

    // Expresie folosita pentru a valida polinomul de intrare
    private static final String MonomRegex = "([+-]?\\d*\\.?\\d*)x\\^?(\\d*)";
    private Map<Integer, Double> GradCoef = new HashMap<Integer, Double>();
    // Map care conține coeficienti pentru fiecare grad al polinomului

    public Polynomial() {

    }

    // preia un sir de intrare (polinomul)
    public Polynomial(String input) {

        input = input.replace(" ", "");
        // Elimina spatiul din sirul de intrare

        String[] monoame = input.split("(?=[+-])");
        // Imparte sirul de intrare în monomii pe baza semnului + sau -

        // Itereaza prin fiecare monom
        for (String monom : monoame) {
            int grad = 0;
            double coef = 0;

            // Daca monomul se potriveste cu formatul asteptat, extrageti coeficientul si gradul
            if (monom.matches(MonomRegex)) {
                // Extrage coeficientul din monom
                //primul grup
                if (monom.replaceAll(MonomRegex, "$1").equals(""))
                    coef = 1;
                else {
                    // Extrage gradul din monom
                    if (monom.replaceAll(MonomRegex, "$1").equals("-")) coef = -1;
                    else if (monom.replaceAll(MonomRegex, "$1").equals("+")) coef = 1;
                    else coef = Double.parseDouble(monom.replaceAll(MonomRegex, "$1"));
                }

                // Extrage coeficientul din monom
                if (monom.replaceAll(MonomRegex, "$2").equals("")) {
                    grad = 1;
                } else {
                    // Extrage gradul din monom
                    if (monom.replaceAll(MonomRegex, "$2").equals("-")) grad = -1;
                    else if (monom.replaceAll(MonomRegex, "$2").equals("+")) grad = 1;
                    else grad = Integer.parseInt(monom.replaceAll(MonomRegex, "$2"));

                }

                // Adauga coeficientul in MAP la gradul corespunzator
                if (GradCoef.containsKey(grad)) {
                    GradCoef.put(grad, GradCoef.get(grad) + coef);
                } else {
                    GradCoef.put(grad, coef);
                }
            } else {
                // Daca monomul nu contine un x (adica este doar o constanta), adaugal ca termen de grad 0
                if (!monom.contains("x")) {
                    if (monom.contains("-")) {
                        GradCoef.put(0, -Double.parseDouble(monom.replace("-", "")));
                    } else {
                        GradCoef.put(0, Double.parseDouble(monom));
                    }
                } else {

                    // Daca monomul nu este valid (adica nu se potriveste cu formatul asteptat si nu contine un x), afiseaza un mesaj
                    JOptionPane.showMessageDialog(null, "Monom invalid : " + monom);
                }

            }
        }
    }


    public void negat() {
        // Metoda de a nega polinomul
        for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
            entry.setValue(entry.getValue() * -1);
        }
    }

    // Metoda pentru a obtine polinomul ca MAP
    public Map<Integer, Double> getPolynomial() {
        return GradCoef;
    }

    public List<Polynomial> divide(Polynomial polynomial) {
        // Metoda de impartire a polinomului la un alt polinom

        // Daca polinomul divizor este zero, afiseaza un mesaj si returneaza null
        if (polynomial.GradCoef.size() == 0) {
            JOptionPane.showMessageDialog(null, "Impartire la zero!");
            return null;
        }


        // Daca polinomul divizor are un singur termen, metoda efectueaza o impartire cu o valoare constanta
        if (polynomial.GradCoef.size() == 1) {
            int i = (int) polynomial.GradCoef.keySet().toArray()[0];
            double j = polynomial.GradCoef.get(i);

            if (i == 0) {

                //Daca divizorul constant este zero, metoda afiseaza un mesaj si returneaza null
                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Impartire la zero!");
                    return null;
                }


                //In caz contrar, calculeaza coeficientul si il returneaza ca o lista
                Polynomial p = new Polynomial();

                for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
                    p.addmonom(entry.getKey(), entry.getValue() / j);
                }

                List<Polynomial> list = new ArrayList<Polynomial>();
                list.add(p);
                return list;
            }

        }


        //In caz contrar, efectueaza impartirea polinomiala
        List<Polynomial> result = new ArrayList<>();
        Polynomial remainder = this;
        Polynomial quotient = new Polynomial();

        while (remainder.getDegree() >= polynomial.getDegree() && remainder.GradCoef.size() != 0 && polynomial.GradCoef.size() != 0) {
            double coef = remainder.GradCoef.get(remainder.getDegree()) / polynomial.GradCoef.get(polynomial.getDegree());
            int grad = remainder.getDegree() - polynomial.getDegree();

            quotient.addmonom(grad, coef);

            Polynomial temp = new Polynomial();
            temp.addmonom(grad, coef);
            temp.multiplyPolynomial(polynomial);
            remainder.subtractPolynomial(temp);
        }

        //Metoda returneaza o lista care contine catul si restul
        result.add(quotient);
        result.add(remainder);
        return result;
    }


    public void addmonom(int grad, double coef) {
        //Aceasta metoda adauga un termen monom la polinom

        if (GradCoef.containsKey(grad)) {
            GradCoef.put(grad, GradCoef.get(grad) + coef);
        } else {
            GradCoef.put(grad, coef);
        }
    }

    public int getDegree() {

        //Aceasta metoda returneaza gradul polinomului
        return GradCoef.get(GradCoef.keySet().stream().max(Integer::compareTo).get()) != 0 ? GradCoef.keySet().stream().max(Integer::compareTo).get() : 0;
    }

    public void multiplyPolynomial(Polynomial polynomial) {
        // Aceasta metoda inmulteste obiectul polinom curent cu un alt obiect polinom
        Map<Integer, Double> nouGradCoef = new HashMap<Integer, Double>();

        // Itereaza prin obiectul polinom curent si obiectul polinom dat.
        for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : polynomial.GradCoef.entrySet()) {


                if (nouGradCoef.containsKey(entry.getKey() + entry2.getKey())) {
                    // Daca rezultatul celor doi exponenti exista deja în harta, adaugati produsul valorilor lor
                    nouGradCoef.put(entry.getKey() + entry2.getKey(), nouGradCoef.get(entry.getKey() + entry2.getKey()) + entry.getValue() * entry2.getValue());
                } else {
                    // In caz contrar, pune rezultatul celor doi exponenti ca o cheie noua si produsul valorilor lor ca valoare
                    nouGradCoef.put(entry.getKey() + entry2.getKey(), entry.getValue() * entry2.getValue());
                }
            }
        }
        // Actualizeaza obiectul polinom curent cu rezultatul
        GradCoef = nouGradCoef;
    }

    public void subtractPolynomial(Polynomial polynomial) {
        // Aceasta metoda scade un alt obiect polinom din obiectul polinom curent
        for (Map.Entry<Integer, Double> entry : polynomial.GradCoef.entrySet()) {
            // Itereaza prin obiectul polinom dat.
            if (GradCoef.containsKey(entry.getKey())) {
                // Daca obiectul polinom curent are un exponent potrivit, scade valoarea data din valoarea sa
                GradCoef.put(entry.getKey(), GradCoef.get(entry.getKey()) - entry.getValue());
            } else {
                // In caz contrar, adauga negativul valorii date cu exponentul sau ca o cheie noua
                GradCoef.put(entry.getKey(), -entry.getValue());
            }
        }
    }

    public Polynomial plus(Polynomial other) {
        // Aceasta metoda returneaza un nou obiect polinom care este suma obiectelor polinomiale curente si date

        Polynomial result = new Polynomial();
        // Adauga valorile obiectelor polinomiale curente si date
        result.addPolynomial(this);
        result.addPolynomial(other);
        return result;
    }

    public void addPolynomial(Polynomial polynomial) {
        // Aceasta metoda adauga obiectul polinom dat la obiectul polinom curent

        // Itereaza prin obiectul polinom dat
        for (Map.Entry<Integer, Double> entry : polynomial.GradCoef.entrySet()) {

            if (GradCoef.containsKey(entry.getKey())) {
                // Daca obiectul polinom curent are un exponent potrivit, adaugati valoarea data la valoarea sa
                GradCoef.put(entry.getKey(), GradCoef.get(entry.getKey()) + entry.getValue());
            } else {
                // In caz contrar, adaugati valoarea data cu exponentul sau ca o cheie noua
                GradCoef.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Polynomial minus(Polynomial other) {
        // Aceasta metoda returneaza un nou obiect polinom care este diferenta dintre obiectele polinom curente si date

        Polynomial result = new Polynomial();

        result.addPolynomial(this);
        // Adauga valorile obiectului polinom curent
        result.subtractPolynomial(other);
        // Scade valorile obiectului polinom dat
        return result;
    }

    public Polynomial multiply(Polynomial other) {
        // Aceasta metoda returneaza un nou obiect polinom care este produsul dintre obiectele polinom curente si date

        Polynomial result = new Polynomial();

        result.addPolynomial(this);
        // Adauga valorile obiectului polinom curent
        result.multiplyPolynomial(other);
        // Inmulteste valorile obiectului polinom dat
        return result;
    }

    public Polynomial derivate() {
        // Aceasta metoda calculeaza derivata polinomului

        Polynomial result = new Polynomial();
        // Itereaza termenii din polinomul original
        for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
            // Verifica daca termenul are un exponent diferit de zero
            if (entry.getKey() != 0) {
                // Calculeaza derivata termenului si o adauga la polinomul rezultat
                result.addmonom(entry.getKey() - 1, entry.getKey() * entry.getValue());
            }
        }
        // Returneaza derivata polinomului
        return result;
    }

    public Polynomial integrate() {
        // Aceasta metoda calculeaza integrala nedefinita a polinomului

        Polynomial result = new Polynomial();
        // Itereaza termenii din polinomul original
        for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
            // Calculeaza integrala termenului si o adauga la polinomul rezultat
            result.addmonom(entry.getKey() + 1, entry.getValue() / (entry.getKey() + 1));
        }
        // Returneaza integrala polinomului
        return result;
    }

    public String toString() {
        //converteste obiectul curent la un sir de caractere

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Double> entry : GradCoef.entrySet()) {
            if (entry.getValue() != 0) {
                if (entry.getValue() > 0) {
                    if (sb.length() != 0) {
                        sb.append(" + ");
                    }
                } else {
                    sb.append(" - ");
                }
                if (Math.abs(entry.getValue()) != 1 || entry.getKey() == 0) {
                    sb.append(Math.abs(entry.getValue()));
                }
                if (entry.getKey() != 0) {
                    sb.append("x");
                    if (entry.getKey() != 1) {
                        sb.append("^");
                        sb.append(entry.getKey());
                    }
                }
            }
        }

        return sb.toString();
    }
}