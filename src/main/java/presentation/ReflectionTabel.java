package presentation;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Clasa ReflectionTabel ofera metode pt manipularea tabelului Swing folosind reflection.
 */
public class ReflectionTabel {

    /**
     * Metoda populeazaTabelul este responsabila pentru adaugarea datelor in tabel.
     *
     * @param listaDate lista de obiecte care contine datele
     * @param model     modelul tabelului in care se adauga datele
     * @param <T>       tipul obiectelor din lista
     */
    public static <T> void populeazaTabelul(List<T> listaDate, DefaultTableModel model) {
        model.setRowCount(0);

        for (T data : listaDate) {
            Object[] rowData = extrageDateObiect(data);
            model.addRow(rowData);
        }
    }
    /**
     * Metoda extrageDateObiect este responsabila pentru extragerea datelor dintr-un obiect.
     *
     * @param obiect obiectul din care se extrag datele
     * @param <T>    tipul obiectului
     * @return un array de obiecte ce contine datele extrase
     */
    private static <T> Object[] extrageDateObiect(T obiect) {
        Field[] campuri = obiect.getClass().getDeclaredFields();
        Object[] randDate = new Object[campuri.length];
        for (int i = 0; i < campuri.length; i++) {
            campuri[i].setAccessible(true);
            try {
                randDate[i] = campuri[i].get(obiect);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return randDate;
    }
    /**
     * Metoda creeazaAntetTabel este responsabila pentru crearea antetului tabelului.
     *
     * @param clasa clasa pentru care se creeaza antetul tabelului
     * @param model modelul tabelului in care se adauga antetul
     * @param <T>   tipul clasei
     */
    public static <T> void creeazaAntetTabel(Class<T> clasa, DefaultTableModel model) {
        Field[] campuri = clasa.getDeclaredFields();
        for (Field camp : campuri) {
            model.addColumn(camp.getName());
        }
    }
}
