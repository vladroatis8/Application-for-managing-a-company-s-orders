
package model;
/**
 * Clasa care reprezinta un produs
 * Are campuri identice cu cele din baza de date
 */

public class Produs {
    private int id;
    private int cantitate;
    private int pret;

    public Produs() {
    }
    /**
     * Constructorul cu parametri pentru obiectele de tip Produs.
     * @param id ID-ul produsului
     * @param cantitate Cantitatea produsului
     * @param pret Prețul produsului
     */
    public Produs(int id, int cantitate, int pret) {
        super();
        this.id = id;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public int getPret() {
        return pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "id=" + id +
                ", cantitate='" + cantitate + '\'' +
                ", pret=" + pret +
                '}';
    }
}
