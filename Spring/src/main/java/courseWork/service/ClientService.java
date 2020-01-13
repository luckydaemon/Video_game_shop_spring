package courseWork.service;

import courseWork.logic.Client;
import courseWork.storage.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ClientService {

    private Client currentClient;
    private ClientRepo clientRepository;
    @Autowired
    public ClientService(ClientRepo clientRepository) {
        this.clientRepository = clientRepository;
    }

    public  Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client findClient (@NotNull final String userId, @NotNull final String password) throws NoSuchElementException {
        if (clientRepository.getClientForLogin(userId,  password).isPresent()) {
            Client client = clientRepository.getClientForLogin(userId,password).get();
            return  client;
        }
        else {
            throw new NoSuchElementException("User not found!");
        }
    }

    public Client findClient1 (@NotNull final String userId, @NotNull final String password) throws NoSuchElementException {
        if (clientRepository.getClientForLogin1(userId).isPresent()) {
            Client client = clientRepository.getClientForLogin1(userId).get();
            if (password.equals(client.getPass())) {
                //if (client.getPass() == password)
                this.currentClient = client;
                return client;
            }
            else throw new NoSuchElementException("User not found/ wrong pass!");
        }
        else {
            throw new NoSuchElementException("User not found!");
        }
    }

    public  String getCurrentClientName()
    {
        return this.currentClient.getFirstName();
    }
    public  Client getCurrentClient()
    {
        return this.currentClient;
    }
    public String getClientPassword  (@NotNull final String userId)
    {
        if (clientRepository.getClientForLogin1(userId).isPresent()) {
            Client client = clientRepository.getClientForLogin1(userId).get();
            return client.getPass();
        }
        else {
            throw new NoSuchElementException("cant get password client");
        }
    }
}
