package presentation;

import business_logic.ClientBLL;
import data_access.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Clasa ClientView reprezinta o interfata grafica pentru gestionarea clientilor.
 * Aceasta interfata permite utilizatorului sa vizualizeze, sa adauge, sa actualizeze si sa stearga clienti dintr-o baza de date.
 */

public class ClientView {
    private JFrame frame;
    private JTable table;
    private ClientDAO clientDAO;
    private ClientBLL clientBLL;
    /**
     * Constructorul clasei ClientView.
     * Initializeaza interfata grafica si obiectele necesare pentru interactiunea cu baza de date.
     */
    public ClientView() {
        clientDAO = new ClientDAO();
        clientBLL = new ClientBLL(clientDAO);
        initialize();
    }
    /**
     * Initializeaza interfata grafica.
     * Creeaza o fereastra JFrame care contine un tabel pentru afisarea clientilor si butoane pentru operatii CRUD.
     */
    private void initialize() {
        frame = new JFrame("Gestionare Clienti");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Tabelul pentru afisarea clientilor
        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        refreshTable();

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton btnBack = new JButton("BACK");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        buttonPanel.add(btnBack);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int clientId = (int) table.getValueAt(row, 0);
                    String clientName = (String) table.getValueAt(row, 1);
                    String clientPhone = (String) table.getValueAt(row, 2);

                    JFrame updateFrame = new JFrame("Actualizare Client");
                    updateFrame.setBounds(200, 200, 300, 200);
                    updateFrame.setLayout(new GridLayout(4, 2));

                    JLabel lblId = new JLabel("ID:");
                    JTextField txtId = new JTextField(String.valueOf(clientId));
                    JLabel lblName = new JLabel("Nume:");
                    JTextField txtName = new JTextField(clientName);
                    JLabel lblPhone = new JLabel("Telefon:");
                    JTextField txtPhone = new JTextField(clientPhone);

                    JButton btnSave = new JButton("Salveaza");
                    btnSave.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newIdStr = txtId.getText();
                            String newName = txtName.getText();
                            String newPhone = txtPhone.getText();

                            if (newName.isEmpty() || newPhone.isEmpty()) {
                                JOptionPane.showMessageDialog(updateFrame, "Introduceti numele si numarul de telefon.", "Eroare", JOptionPane.ERROR_MESSAGE);
                                return; // Oprire pentru a nu continua cu actualizarea
                            }


                            try {
                                int newId = Integer.parseInt(newIdStr);
                                if (newPhone.length() != 10 || !newPhone.matches("[0-9]+")) {
                                    throw new NumberFormatException();
                                }
                                Client updatedClient = new Client(newId, newName, newPhone);

                                clientBLL.updateClient(updatedClient,clientId);

                                updateFrame.dispose();

                                refreshTable();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(updateFrame, "ID-ul trebuie sa fie un numar intreg.", "Eroare", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    updateFrame.add(lblId);
                    updateFrame.add(txtId);
                    updateFrame.add(lblName);
                    updateFrame.add(txtName);
                    updateFrame.add(lblPhone);
                    updateFrame.add(txtPhone);
                    updateFrame.add(btnSave);

                    updateFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecteaza un client pentru a-l actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnUpdate);


        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame insertFrame = new JFrame("Introducere Client");
                insertFrame.setBounds(200, 200, 300, 200);
                insertFrame.setLayout(new GridLayout(4, 2));

                JLabel lblId = new JLabel("ID:");
                JTextField txtId = new JTextField();
                JLabel lblName = new JLabel("Nume:");
                JTextField txtName = new JTextField();
                JLabel lblPhone = new JLabel("Telefon:");
                JTextField txtPhone = new JTextField();

                JButton btnSave = new JButton("Salveaza");
                btnSave.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String idString = txtId.getText();
                        String newName = txtName.getText();
                        String newPhone = txtPhone.getText();

                        if (idString.isEmpty() || newName.isEmpty() || newPhone.isEmpty()) {
                            JOptionPane.showMessageDialog(insertFrame, "Introdu ID-ul, numele si numarul de telefon.", "Eroare", JOptionPane.ERROR_MESSAGE);
                            return; // Oprire pentru a nu continua cu inserarea
                        }

                        try {
                            int newId = Integer.parseInt(idString);

                            if (newPhone.length() != 10 || !newPhone.matches("[0-9]+")) {
                                throw new NumberFormatException();
                            }

                            Client newClient = new Client(newId, newName, newPhone);

                            clientBLL.insertClient(newClient);

                            insertFrame.dispose();

                            refreshTable();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(insertFrame, "Eroare date", "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                insertFrame.add(lblId);
                insertFrame.add(txtId);
                insertFrame.add(lblName);
                insertFrame.add(txtName);
                insertFrame.add(lblPhone);
                insertFrame.add(txtPhone);
                insertFrame.add(btnSave);

                insertFrame.setVisible(true);
            }
        });
        buttonPanel.add(btnInsert);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    int clientId = (int) table.getValueAt(row, 0);

                    clientBLL.deleteClient(clientId);

                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecteaza un client pentru a-l sterge.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnDelete);

        frame.setVisible(true);
    }


    /**
     * Actualizeaza tabelul cu datele clientilor din baza de date.
     */    private void refreshTable() {
        List<Client> clients = clientDAO.findAll();
        DefaultTableModel model = new DefaultTableModel();
        ReflectionTabel.creeazaAntetTabel(Client.class, model);
        ReflectionTabel.populeazaTabelul(clients, model);
        table.setModel(model);
    }
}
