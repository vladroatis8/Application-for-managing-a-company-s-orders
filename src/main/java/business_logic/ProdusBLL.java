package business_logic;

import data_access.AbstractDAO;
import model.Produs;
import java.util.List;
/**
 * Clasa ProdusBLL este responsabila pentru logica asociata entitatii Produs.
 * Face legatura intre interfata si AbstractDAO, de aici se apeleaza metodele
 */
public class ProdusBLL {
    private final AbstractDAO<Produs> produsDAO;
    /**
     * Constructor pentru obiectele ProdusBLL.
     *
     * @param produsDAO obiectul care ofera acces la operatiile CRUD pentru entitatea Produs
     */
    public ProdusBLL(AbstractDAO<Produs> produsDAO) {
        this.produsDAO = produsDAO;
    }

    public Produs findProdusById(int id) {
        return produsDAO.findById(id);
    }

    public List<Produs> findAllProduse() {
        return produsDAO.findAll();
    }

    public Produs insertProdus(Produs produs) {
        return produsDAO.insert(produs);
    }

    public Produs updateProdus(Produs produs, int oldId) {
        return produsDAO.update(produs, oldId);
    }

    public void deleteProdus(int id) {
        produsDAO.delete(id);
    }

}
