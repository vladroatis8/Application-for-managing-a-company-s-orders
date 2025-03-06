package business_logic;

import data_access.AbstractDAO;
import model.Client;
import java.util.List;
/**
 * Clasa ClientBLL este responsabila pentru logica asociata entitatii Client.
 * Face legatura intre interfata si AbstractDAO, de aici se apeleaza metodele
 */
public class ClientBLL {
    private final AbstractDAO<Client> clientDAO;
    /**
     * Constructor pentru obiectele ClientBLL.
     *
     * @param clientDAO obiectul care ofera acces la operatiile CRUD pentru entitatea Client
     */
    public ClientBLL(AbstractDAO<Client> clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client findClientById(int id) {
        return clientDAO.findById(id);
    }

    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }

    public Client insertClient(Client client) {
        return clientDAO.insert(client);
    }

    public Client updateClient(Client client,int oldid) {
        return clientDAO.update(client,oldid);
    }

    public void deleteClient(int id) {
        clientDAO.delete(id);
    }

}
