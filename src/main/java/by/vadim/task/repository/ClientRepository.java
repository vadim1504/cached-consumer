package by.vadim.task.repository;

import by.vadim.task.model.Client;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@N1qlSecondaryIndexed(indexName = "idx_Balance_Value")
public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findByBalanceValueBefore(int value);
}
