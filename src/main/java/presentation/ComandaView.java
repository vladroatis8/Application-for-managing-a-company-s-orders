package presentation;

import data_access.ClientDAO;
import data_access.ComandaDAO;
import data_access.ProdusDAO;
import data_access.BillDAO;
import model.Bill;
import model.Client;
import model.Comanda;
import model.Produs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Clasa ComandaView reprezinta interfata grafica pentru plasarea comenzilor.
 * Permite utilizatorilor sa selecteze un produs si un client, sa introduca cantitatea dorita si sa plaseze comanda.
 */

public class ComandaView {
    private JFrame frame;
    private JComboBox<Produs> produsComboBox;
    private JComboBox<Client> clientComboBox;
    private JTextField cantitateTextField;
    private JTextField idComandaTextField;
    private JButton plaseazaComandaButton;
    private ComandaDAO comandaDAO;
    private ProdusDAO produsDAO;
    private ClientDAO clientDAO;

    /**
     * Constructorul clasei ComandaView
     * Initializeaza DAO-urile si configureaza interfata grafica
     */
    private BillDAO billDAO;

    public ComandaView() {
        comandaDAO = new ComandaDAO();
        produsDAO = new ProdusDAO();
        clientDAO = new ClientDAO();
        billDAO = new BillDAO();
        initialize();
        updateDropdowns(produsDAO.findAll(), clientDAO.findAll());
    }
    /**
     * Initializeaza interfata grafica.
     * Creeaza ferestrele, etichetele, campurile text si butoanele necesare pentru plasarea comenzilor.
     */
    private void initialize() {
        frame = new JFrame("Plasare Comanda");
        frame.setBounds(100, 100, 700, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(5, 2));

        JLabel lblProdus = new JLabel("Produs:");
        produsComboBox = new JComboBox<>();
        frame.getContentPane().add(lblProdus);
        frame.getContentPane().add(produsComboBox);

        JLabel lblClient = new JLabel("Client:");
        clientComboBox = new JComboBox<>();
        frame.getContentPane().add(lblClient);
        frame.getContentPane().add(clientComboBox);

        JLabel lblCantitate = new JLabel("Cantitate:");
        cantitateTextField = new JTextField();
        frame.getContentPane().add(lblCantitate);
        frame.getContentPane().add(cantitateTextField);

        JLabel lblIdComanda = new JLabel("ID Comanda:");
        idComandaTextField = new JTextField();
        frame.getContentPane().add(lblIdComanda);
        frame.getContentPane().add(idComandaTextField);

        plaseazaComandaButton = new JButton("Plaseaza Comanda");
        plaseazaComandaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                plaseazaComanda();
            }
        });
        frame.getContentPane().add(plaseazaComandaButton);

        frame.setVisible(true);
    }

    private void plaseazaComanda() {
        Produs selectedProdus = (Produs) produsComboBox.getSelectedItem();
        Client selectedClient = (Client) clientComboBox.getSelectedItem();
        String cantitateText = cantitateTextField.getText();
        String idComandaText = idComandaTextField.getText();
        int cantitate, idComanda;
        try {
            cantitate = Integer.parseInt(cantitateText);
            idComanda = Integer.parseInt(idComandaText);
            if (selectedProdus == null || selectedClient == null || cantitate <= 0 || idComanda < 0) {
                JOptionPane.showMessageDialog(frame, "Selectati un produs si un client valid si introduceti o cantitate valida si un ID de comanda pozitiv.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Introduceti o cantitate si un ID de comanda valide.");
            return;
        }

        if (selectedProdus.getCantitate() < cantitate) {
            JOptionPane.showMessageDialog(frame, "Stoc insuficient pentru produsul selectat.");
            return;
        }

        Comanda comanda = new Comanda(idComanda, selectedClient.getId(),selectedProdus.getId(), cantitate);
        comandaDAO.insert(comanda);

        Bill bill = new Bill(selectedClient.getId(), selectedClient.getNume(), selectedClient.getTelefon(), selectedProdus.getId(), cantitate, selectedProdus.getPret() * cantitate);
        billDAO.insert(bill);

        int newStock = selectedProdus.getCantitate() - cantitate;
        int oldId = selectedProdus.getId();
        selectedProdus.setCantitate(newStock);
        produsDAO.update(selectedProdus, oldId);

        JOptionPane.showMessageDialog(frame, "Comanda plasata cu succes!");
    }

    public void updateDropdowns(List<Produs> produse, List<Client> clienti) {
        produsComboBox.removeAllItems();
        for (Produs produs : produse) {
            produsComboBox.addItem(produs);
        }

        clientComboBox.removeAllItems();
        for (Client client : clienti) {
            clientComboBox.addItem(client);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComandaView();
            }
        });
    }
}
