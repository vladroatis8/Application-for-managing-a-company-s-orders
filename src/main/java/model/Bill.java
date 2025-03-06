package model;
/**
 * Clasa Bill reprezinta o factura in sistem.
 */

/**
 * Constructorul clasei Bill.
 *
 * @param idclient   ID-ul clientului asociat facturii
 * @param nume       Numele clientului
 * @param telefon    Numarul de telefon al clientului
 * @param idprodus   ID-ul produsului achizitionat
 * @param cantitate  Cantitatea achizitionata
 * @param pret       Pretul total al facturii
 */
public record Bill(int idclient,String nume, String telefon,int idprodus,int cantitate,int pret) {

}
