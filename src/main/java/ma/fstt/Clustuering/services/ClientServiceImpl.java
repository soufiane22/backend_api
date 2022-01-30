package ma.fstt.Clustuering.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.fstt.Clustuering.entities.Client;
import ma.fstt.Clustuering.reposotories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ClientServiceImpl  implements ClientService{

    private final ClientRepository clientRepository;
    @Override
    public Client save(Client client) {
        log.info("saving client:{}",client.get_id());
        return clientRepository.save(client);
    }

    @Override
    public Collection<Client> list(int limit) {
        log.info("fetching all client ");

        return  clientRepository.findAll(of(0,limit)).toList();
    }

    @Override
    public List<Client> getALL() {

        return null;
    }

    @Override
    public Client get(String id) {
        log.info("fetching client by id: {} ",id);
        return clientRepository.findById(id).get();
    }

    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        log.info("delete client by id: {} ", id);
        clientRepository.deleteById(id);
        return TRUE;
    }

    @Override
    public HashMap<String, Integer> getGender() {
        int nbrHpmme = clientRepository.countClientsBySex("Male");
        int nbrfemme = clientRepository.countClientsBySex("Female");
        int nbrsmoker = clientRepository.countClientsBySmoker("Yes");
        int nbrNosmoker = clientRepository.countClientsBySmoker("No");
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        list.put("homme", nbrHpmme);
        list.put("femme", nbrfemme);
        list.put("smokers", nbrsmoker);
        list.put("Nosmokers", nbrNosmoker);
        return list;
    }

    @Override
    public HashMap<String, Integer> getStatistic() {
        int nbrexpenses = 0;
        List<Client> l = clientRepository.findAll();
        int nbrClient = l.size();
        for (Client c : l) {
            nbrexpenses += c.getExpenses();
        }
        HashMap<String, Integer> list = new HashMap<String, Integer>();
        list.put("nbrClient", nbrClient);
        list.put("nbrexpenses", nbrexpenses);
        return list;
    }

    @Override
    public HashMap<String, Integer> getChildren() {
        HashMap<String, Integer> child = new HashMap<String, Integer>();
        child.put("c0", clientRepository.countClientsByChildren(0));
        child.put("c1", clientRepository.countClientsByChildren(1));
        child.put("c2", clientRepository.countClientsByChildren(2));
        child.put("c3", clientRepository.countClientsByChildren(3));
        child.put("c4", clientRepository.countClientsByChildren(3));
        child.put("c5", clientRepository.countClientsByChildren(5));
        return child;
    }

    @Override
    public HashMap<String, HashSet> getClusters() {
        Collection<Client> list = clientRepository.findAll(of(0, 1000)).toList();
        HashSet<Object> cluster0 = new HashSet<Object>();
        HashSet<Object> cluster1 = new HashSet<Object>();
        HashSet<Object> cluster2 = new HashSet<Object>();
        HashMap<String, HashSet> clusters = new HashMap<String, HashSet>();

        for (Client c : list) {
            if (c.getCluster() == 0) {
                HashMap<String, Integer> item = new HashMap<String, Integer>();
                item.put("x", c.getAge());
                item.put("y", c.getExpenses());
                cluster0.add(item);
            }
            if (c.getCluster() == 1) {
                HashMap<String, Integer> item = new HashMap<String, Integer>();
                item.put("x", c.getAge());
                item.put("y", c.getExpenses());
                cluster1.add(item);
            }
            if (c.getCluster() == 2) {
                HashMap<String, Integer> item = new HashMap<String, Integer>();
                item.put("x", c.getAge());
                item.put("y", c.getExpenses());
                cluster2.add(item);
            }
        }
        clusters.put("cluster0", cluster0);
        clusters.put("cluster1", cluster1);
        clusters.put("cluster2", cluster2);

        return clusters;
    }

}
