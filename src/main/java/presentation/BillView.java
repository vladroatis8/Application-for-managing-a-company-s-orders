package presentation;

import data_access.BillDAO;
import model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Clasa BillView reprezinta o interfata grafica pentru afisarea listei de facturi.
 * Aceasta interfata permite utilizatorului sa vizualizeze facturile existente intr-un tabel.
 */
public class BillView {
    private JFrame frame;
    private JTable table;
    private BillDAO billDAO;
    /**
     * Constructorul clasei BillView.
     * initializeaza interfata grafica BillDAO pentru manipularea datelor.
     */
    public BillView() {
        this.billDAO = new BillDAO();

        initialize();
    }

    private void initialize() {
        frame = new JFrame("Facturi");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

         refreshTable();
    }

    /**
     * Afiseaza lista de facturi in tabel.
     *
     */
    public void refreshTable() {
        List<Bill> facturi = billDAO.findAll();

        DefaultTableModel model = new DefaultTableModel();
        ReflectionTabel.creeazaAntetTabel(Bill.class, model);
        ReflectionTabel.populeazaTabelul(facturi, model);
        table.setModel(model);
        frame.setVisible(true);
    }

}

