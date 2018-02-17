package by.vadim.task.service;

import by.vadim.task.model.Client;

import java.util.List;

public interface ClientService {

    Client createClient(Client client);

    List<Client> getClientByBalanceValue(int value);
}
