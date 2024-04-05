package Display;

import Prelucrari.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class PolynomialDisplay extends JFrame {
    private final JLabel firstPolynomialLabel = new JLabel("Primul polinom");
    private final JLabel secondPolynomialLabel = new JLabel("Al doilea polinom");
    private final JButton multiplicateButton = new JButton("Inmultire");
    private final JButton divideButton = new JButton("Impartire");
    private final JButton addButton = new JButton("Adunare");
    private final JButton subtractButton = new JButton("Scadere");
    private final JButton integrateButton = new JButton("Integreaza(pt Primul polinom)");
    private final JButton derivateButton = new JButton("Deriveaza(pt Primul polinom)");
    private final JButton exitButton = new JButton("Exit");
    private final JButton x = new JButton("x");
    private final JButton plus = new JButton("+");
    private final JButton minus = new JButton("-");
    private final JButton power = new JButton("^");
    private final JButton point = new JButton(".");
    private final JButton clear = new JButton("C");
    private JTextField firstPolynomial;
    private JTextField secondPolynomial;
    private JPanel mainPanel;
    private JPanel writePanel1;
    private JPanel writePanel2;
    private JPanel buttonsPanel;
    private JButton[] numbers;
    private int focus = 0;

    public PolynomialDisplay() {
        super("Calculator de POLINOAME by Fabisor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 950);
        this.set();
        this.addListenersForButtons();
    }

    public void set() {
        //Instatineri obiecte din interfata
        mainPanel = new JPanel();
        writePanel1 = new JPanel();
        writePanel2 = new JPanel();
        buttonsPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(4, 1));
        //Seteaza un layout
        writePanel1.setLayout(new BoxLayout(writePanel1, BoxLayout.Y_AXIS));
        writePanel2.setLayout(new BoxLayout(writePanel2, BoxLayout.Y_AXIS));
        buttonsPanel.setLayout(new GridLayout(4, 2));

        firstPolynomial = new JTextField(1);
        secondPolynomial = new JTextField(1);

        firstPolynomialLabel.setFont(new Font("Serif", Font.BOLD, 20));
        secondPolynomialLabel.setFont(new Font("Serif", Font.BOLD, 20));

        //Adauga label-uri la writePanel1 si la writePanel2
        writePanel1.add(firstPolynomialLabel);
        writePanel1.add(firstPolynomial);
        writePanel2.add(secondPolynomialLabel);
        writePanel2.add(secondPolynomial);

        buttonsPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(writePanel1);
        mainPanel.add(writePanel2);
        mainPanel.add(buttonsPanel);

        JPanel numbersPanel = new JPanel(new GridLayout(1, 2));
        JPanel numbersPanel1 = new JPanel(new GridLayout(3, 3));
        JPanel numbersPanel2 = new JPanel(new GridLayout(3, 3));

        numbers = new JButton[10];
        for (int i = 1; i < 10; i++) {
            numbers[i] = new JButton(String.valueOf(i));
            numbersPanel1.add(numbers[i]);
        }

        numbers[0] = new JButton("0");
        numbersPanel2.add(numbers[0]);
        numbersPanel2.add(clear);
        numbersPanel2.add(point);
        numbersPanel2.add(x);
        numbersPanel2.add(plus);
        numbersPanel2.add(minus);
        numbersPanel2.add(power);

        numbersPanel.add(numbersPanel1);
        numbersPanel.add(numbersPanel2);

        //Adauga butoane la paneluri
        JPanel operatorsPanel1 = new JPanel(new FlowLayout());
        JPanel operatorsPanel2 = new JPanel(new FlowLayout());
        operatorsPanel1.add(multiplicateButton);
        operatorsPanel1.add(divideButton);
        operatorsPanel1.add(addButton);
        operatorsPanel1.add(subtractButton);
        operatorsPanel2.add(integrateButton);
        operatorsPanel2.add(derivateButton);
        operatorsPanel2.add(exitButton);

        buttonsPanel.add(numbersPanel);
        buttonsPanel.add(operatorsPanel1);
        buttonsPanel.add(operatorsPanel2);


        this.add(mainPanel);
    }

    public void addListenersForButtons() {
        //Adauga un Lisener la fiecare buton
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            numbers[i].addActionListener(e -> {
                if (focus == 0)
                    firstPolynomial.setText(firstPolynomial.getText() + finalI);
                else
                    secondPolynomial.setText(secondPolynomial.getText() + finalI);
            });
        }

        x.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText(firstPolynomial.getText() + "x");
            else
                secondPolynomial.setText(secondPolynomial.getText() + "x");
        });

        plus.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText(firstPolynomial.getText() + "+");
            else
                secondPolynomial.setText(secondPolynomial.getText() + "+");
        });

        minus.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText(firstPolynomial.getText() + "-");
            else
                secondPolynomial.setText(secondPolynomial.getText() + "-");
        });

        power.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText(firstPolynomial.getText() + "^");
            else
                secondPolynomial.setText(secondPolynomial.getText() + "^");
        });

        point.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText(firstPolynomial.getText() + ".");
            else
                secondPolynomial.setText(secondPolynomial.getText() + ".");
        });

        clear.addActionListener(e -> {
            if (focus == 0)
                firstPolynomial.setText("");
            else
                secondPolynomial.setText("");
        });


        firstPolynomial.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                focus = 1;
            }

            @Override
            public void focusLost(FocusEvent e) {
                focus = 0;
            }
        });

        secondPolynomial.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                focus = 0;
            }

            @Override
            public void focusLost(FocusEvent e) {
                focus = 1;
            }
        });

        multiplicateButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("") || secondPolynomial.getText().equals("") || verifAspectMonom(firstPolynomial.getText()) == false || verifAspectMonom(secondPolynomial.getText()) == false)
                JOptionPane.showMessageDialog(null, "Trebuie sa scrieti doua polinoame!");
            else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial second = new Polynomial(secondPolynomial.getText());
                Polynomial result = first.multiply(second);
                if (result.toString().equals(""))
                    JOptionPane.showMessageDialog(null, "0");
                else
                    JOptionPane.showMessageDialog(null, result.toString());
            }
        });

        divideButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("") || secondPolynomial.getText().equals("") || verifAspectMonom(firstPolynomial.getText()) == false || verifAspectMonom(secondPolynomial.getText()) == false)
                JOptionPane.showMessageDialog(null, "Trebuie sa scrieti doua polinoame!");
            else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial second = new Polynomial(secondPolynomial.getText());

                if (second.getPolynomial().size() == 0 || (second.getPolynomial().size() == 1 && second.getPolynomial().values().toArray()[0].equals(0.0))) {
                    JOptionPane.showMessageDialog(null, "Nu poti imparti la 0!");
                } else {
                    List<Polynomial> result = first.divide(second);
                    if (result.size() == 1)
                        JOptionPane.showMessageDialog(null, result.get(0).toString());
                    else
                        JOptionPane.showMessageDialog(null, "Cat: " + result.get(0).toString() + " \n " + "Rest: " + result.get(1).toString());
                }
            }
        });

        addButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("")) {
                if (verifAspectMonom(secondPolynomial.getText()) == false)
                    JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
                else
                    JOptionPane.showMessageDialog(null, secondPolynomial.getText());
            } else if (secondPolynomial.getText().equals("")) {
                if (verifAspectMonom(firstPolynomial.getText()) == false)
                    JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
                else
                    JOptionPane.showMessageDialog(null, firstPolynomial.getText());
            } else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial second = new Polynomial(secondPolynomial.getText());
                Polynomial result = first.plus(second);
                if (result.toString().equals(""))
                    JOptionPane.showMessageDialog(null, "0");
                else
                    JOptionPane.showMessageDialog(null, result.toString());
            }
        });

        subtractButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("")) {
                if (verifAspectMonom(secondPolynomial.getText()) == false)
                    JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
                else {
                    Polynomial second = new Polynomial(secondPolynomial.getText());
                    second.negat();
                    JOptionPane.showMessageDialog(null, second.toString());
                }
            } else if (secondPolynomial.getText().equals("")) {
                if (verifAspectMonom(firstPolynomial.getText()) == false)
                    JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
                else
                    JOptionPane.showMessageDialog(null, firstPolynomial.getText());
            } else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial second = new Polynomial(secondPolynomial.getText());
                Polynomial result = first.minus(second);
                if (result.toString().equals(""))
                    JOptionPane.showMessageDialog(null, "0");
                else
                    JOptionPane.showMessageDialog(null, result.toString());
            }
        });

        integrateButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("") || verifAspectMonom(firstPolynomial.getText()) == false)
                JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
            else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial result = first.integrate();
                if (result.toString().equals(""))
                    JOptionPane.showMessageDialog(null, "0");
                else
                    JOptionPane.showMessageDialog(null, result.toString());
            }
        });

        derivateButton.addActionListener(e -> {
            if (firstPolynomial.getText().equals("") || verifAspectMonom(firstPolynomial.getText()) == false)
                JOptionPane.showMessageDialog(null, "Trebuie sa rescrieti un polinom!");
            else {
                Polynomial first = new Polynomial(firstPolynomial.getText());
                Polynomial result = first.derivate();
                if (result.toString().equals("")) JOptionPane.showMessageDialog(null, "0");
                else
                    JOptionPane.showMessageDialog(null, result.toString());
            }
        });

        exitButton.addActionListener(e -> {
            this.dispose();
        });
    }

    public boolean verifAspectMonom(String a) {
        for (int i = 0; i < a.length() - 1; i++)
          /*  if ((a.charAt(i) == 'x' && a.charAt(i + 1) != '+' && a.charAt(i + 1) != '-' && a.charAt(i + 1) != '*' && a.charAt(i + 1) != '^') ||
                    ((a.charAt(i) == '+' || a.charAt(i) == '-' || a.charAt(i) == '*' || a.charAt(i) == '^') && (a.charAt(i + 1) == '+' || a.charAt(i + 1) == '-' ||
                            a.charAt(i + 1) == '*' || a.charAt(i + 1) == ' ' || a.charAt(i + 1) == '^')) || (a.charAt(a.length() - 1) == '+' || a.charAt(a.length() - 1) ==
                    '-' || a.charAt(a.length() - 1) == '*' || a.charAt(a.length() - 1) == ' ' || a.charAt(a.length() - 1) == '^'))*/
            if (a.charAt(i) == 'x' && a.charAt(i + 1) != '+' && a.charAt(i + 1) != '-' && a.charAt(i + 1) != '*' && a.charAt(i + 1) != '^')
                return false;
        return true;
    }
}