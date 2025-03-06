package presentation;

import business_logic.ProdusBLL;
import data_access.ProdusDAO;
import model.Produs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * Clasa ProdusView reprezinta interfata grafica pentru gestionarea produselor.
 */
public class ProdusView {
    private JFrame frame;
    private JTable table;
    private ProdusDAO produsDAO;
    private ProdusBLL produsBLL;
    /**
     * Constructorul clasei ProdusView.
     * Initializeaza interfata grafica si obiectele DAO si BLL necesare.
     */
    public ProdusView() {
        produsDAO = new ProdusDAO();
        produsBLL = new ProdusBLL(produsDAO);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Gestionare Produse");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

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
                    int produsId = (int) table.getValueAt(row, 0);
                    int produsCantitate = (int) table.getValueAt(row, 1);
                    int produsPret = (int) table.getValueAt(row, 2);

                    JFrame updateFrame = new JFrame("Actualizare Produs");
                    updateFrame.setBounds(200, 200, 300, 200);
                    updateFrame.setLayout(new GridLayout(4, 2));

                    JLabel lblId = new JLabel("ID:");
                    JTextField txtId = new JTextField(String.valueOf(produsId));
                    JLabel lblCantitate = new JLabel("Cantitate:");
                    JTextField txtCantitate = new JTextField(produsCantitate);
                    JLabel lblPret = new JLabel("Pret:");
                    JTextField txtPret = new JTextField(produsPret);

                    JButton btnSave = new JButton("Salveaza");
                    btnSave.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String newIdStr = txtId.getText();
                            String newCantitat = txtCantitate.getText();
                            String newPre= txtPret.getText();

                            if (newCantitat.isEmpty() || newPre.isEmpty()) {
                                JOptionPane.showMessageDialog(updateFrame, "Introduceti date.", "Eroare", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            try {
                                int newId = Integer.parseInt(newIdStr);
                                int newCantitate = Integer.parseInt(newCantitat);
                                int newPret = Integer.parseInt(newPre);

                                Produs updatedProdus = new Produs(newId, newCantitate, newPret);

                                produsBLL.updateProdus(updatedProdus,produsId);

                                updateFrame.dispose();

                                refreshTable();
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(updateFrame, "Eroare date", "Eroare", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    updateFrame.add(lblId);
                    updateFrame.add(txtId);
                    updateFrame.add(lblCantitate);
                    updateFrame.add(txtCantitate);
                    updateFrame.add(lblPret);
                    updateFrame.add(txtPret);
                    updateFrame.add(btnSave);

                    updateFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecteaza un produs pentru a-l actualiza.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnUpdate);



        JButton btnInsert = new JButton("Insert");
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame insertFrame = new JFrame("Introducere Produs");
                insertFrame.setBounds(200, 200, 300, 200);
                insertFrame.setLayout(new GridLayout(4, 2));

                JLabel lblId = new JLabel("ID:");
                JTextField txtId = new JTextField();
                JLabel lblCantitate = new JLabel("Cantitate:");
                JTextField txtCantitate = new JTextField();
                JLabel lblPret = new JLabel("Pret:");
                JTextField txtPret = new JTextField();

                JButton btnSave = new JButton("Salveaza");
                btnSave.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obtine valorile introduse de utilizator
                        String idString = txtId.getText();
                        String newCant = txtCantitate.getText();
                        String newPre = txtPret.getText();

                        if (idString.isEmpty() || newCant.isEmpty() || newPre.isEmpty()) {
                            JOptionPane.showMessageDialog(insertFrame, "Introdu date", "Eroare", JOptionPane.ERROR_MESSAGE);
                            return; // Oprire pentru a nu continua cu inserarea
                        }


                            int newId = Integer.parseInt(idString);
                            int newCantitate = Integer.parseInt(newCant);
                            int newPret = Integer.parseInt(newPre);



                            Produs newProdus = new Produs(newId, newCantitate, newPret);

                            produsBLL.insertProdus(newProdus);

                            insertFrame.dispose();

                            refreshTable();

                    }
                });

                insertFrame.add(lblId);
                insertFrame.add(txtId);
                insertFrame.add(lblCantitate);
                insertFrame.add(txtCantitate);
                insertFrame.add(lblPret);
                insertFrame.add(txtPret);
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
                    int produsId = (int) table.getValueAt(row, 0); // Id-ul clientului selectat

                    produsBLL.deleteProdus(produsId);

                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecteaza un produs pentru a-l sterge.", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(btnDelete);

        frame.setVisible(true);
    }

    /**
     * Metoda pentru actualizarea datelor din tabel.
     */    private void refreshTable() {
        List<Produs> produse = produsDAO.findAll();
        DefaultTableModel model = new DefaultTableModel();
        ReflectionTabel.creeazaAntetTabel(Produs.class, model);
        ReflectionTabel.populeazaTabelul(produse, model);
        table.setModel(model);
    }
}
