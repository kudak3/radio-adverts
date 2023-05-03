package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.repository.ClientRepository;
import com.example.radioadsapp.service.ClientService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> getAll() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Client> clients = new ArrayList<>();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if ("RADIOSTATION".equals(authority.getAuthority())) {
                for (Client client : clientRepository.findAll()) {
                    for (Advert advert : client.getAdverts()) {
                        if (advert.getCreatedBy().equals(auth.getName())) {
                            clients.add(client);
                            break;
                        }
                    }
                }

            } else {
                clients = clientRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            }
        }
        return clients;
    }


    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Client updateClient(Client updatedClient) {

        return clientRepository.findById(updatedClient.getId())
                .map(client -> {
                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());
                    client.setPhoneNumber(updatedClient.getPhoneNumber());
                    client.setGender(updatedClient.getGender());
                    client.setPayments(updatedClient.getPayments());
                    client.setNewEntry(updatedClient.isNewEntry());
                    client.setAdverts(updatedClient.getAdverts());
                    return clientRepository.save(client);
                })
                .orElse(null);

    }

    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);

    }


    public List<Payment> getPaymentHistory(Long id) {
        return clientRepository.findById(id).map(client -> client.getPayments()).orElse(null);
    }

}
