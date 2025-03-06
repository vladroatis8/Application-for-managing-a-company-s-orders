
package model;

/**
 * Clasa care reprezinta un client.
 * Are campuri identice cu cele din baza de date
 */

public class Client {
    private int id;
    private String nume;
    private String telefon;
    /**
     * Constructorul cu parametri pentru obiectele de tip Client.
     * @param id ID-ul clientului
     * @param nume Numele
     * @param telefon Nr de telefon
     */
    public Client(int id, String nume, String telefon) {
        super();
        this.id = id;
        this.nume = nume;
        this.telefon = telefon;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + nume + ", telefon=" + telefon
                + "]";
    }

}
