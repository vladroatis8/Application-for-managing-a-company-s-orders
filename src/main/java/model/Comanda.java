package model;

/**
 * Clasa care reprezinta o comanda.
 * Are campuri identice cu cele din baza de date
 */
public class Comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    /**
     * Constructorul cu parametri pentru obiectele de tip Comanda.
     * @param id ID-ul comenzii
     * @param idClient ID-ul clientului asociat comenzii
     * @param idProdus ID-ul produsului asociat comenzii
     * @param cantitate Cantitatea comenzii
     */
    public Comanda(int id, int idClient, int idProdus, int cantitate) {
        this.id = id;
        this.idClient = idClient;
        this.idProdus = idProdus;
        this.cantitate = cantitate;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public Comanda() {
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProdus=" + idProdus +
                '}';
    }
}
