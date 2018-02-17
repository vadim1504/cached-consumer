package by.vadim.task.service;

import by.vadim.task.model.Client;
import by.vadim.task.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    public Client createClient(Client client){
       return repository.save(client);
    }

    public List<Client> getClientByBalanceValue(int value){
        return repository.findByBalanceValueBefore(value);
    }

}
