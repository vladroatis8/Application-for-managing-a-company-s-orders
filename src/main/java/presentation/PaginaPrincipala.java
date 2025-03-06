package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa PaginaPrincipala reprezinta fereastra principala a aplicatiei de gestionare a depozitului.
 * Aceasta clasa contine butoane pentru navigarea catre diferitele funcționalitati ale aplicatiei.
 */
public class PaginaPrincipala {
    private JFrame frame;

    public PaginaPrincipala() {
        initialize();
    }
    /**
     * Metoda initialize configureaza fereastra principala a aplicatiei si adauga butoanele necesare.
     */
    private void initialize() {
        frame = new JFrame("Gestionare Depozit");
        frame.setBounds(100, 100, 450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        JButton btnClient = new JButton("Client");
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new ClientView();
            }
        });
        frame.getContentPane().add(btnClient);

        JButton btnProdus = new JButton("Produs");
        btnProdus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ProdusView();
            }
        });
        frame.getContentPane().add(btnProdus);

        JButton btnComanda = new JButton("Comanda");
        btnComanda.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ComandaView();
            }
        });
        frame.getContentPane().add(btnComanda);

        JButton veziFacturileButton = new JButton("Vezi Facturile");
        veziFacturileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BillView();
            }
        });
        frame.getContentPane().add(veziFacturileButton);

        frame.setVisible(true);
    }

    /**
     * Metoda principala care porneste aplicaaia.
     * Creeaza o noua instanta a clasei PaginaPrincipala si afiseaza interfata grafica.
     *
     * @param args Argumentele liniei de comanda (nu sunt utilizate).
     */
        public static void main(String[] args) {
            new PaginaPrincipala();
        }
    }

