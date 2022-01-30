package ma.fstt.Clustuering.services;

import ma.fstt.Clustuering.entities.Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public interface ClientService {

    Client save(Client client);

    Collection<Client> list(int limit);

    Collection<Client> getALL();

    Client get(String id);

    Client update(Client client);

    Boolean delete(String id);

    HashMap<String, Integer> getGender();

    HashMap<String, Integer> getStatistic();

    HashMap<String, Integer> getChildren();

    HashMap<String, HashSet> getClusters();
}
