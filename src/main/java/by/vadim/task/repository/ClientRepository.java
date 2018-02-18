package by.vadim.task.repository;

import by.vadim.task.model.Client;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@N1qlPrimaryIndexed
public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findByBalanceValueBefore(int value);
}