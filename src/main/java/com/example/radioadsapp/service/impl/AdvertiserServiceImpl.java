package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.repository.ClientRepository;
import com.example.radioadsapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

        private final ClientRepository clientRepository;

        public ClientServiceImpl(ClientRepository clientRepository) {
            this.clientRepository = clientRepository;
        }



        public List<Client> getAll() {
            return clientRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        public Client save(Client client) {
            return clientRepository.save(client);
        }

        public void delete(Long id) {
            clientRepository.deleteById(id);
        }

        public Client updateClient(Client updatedClient) {

            return clientRepository.findById(updatedClient.getId())
                    .map(client-> {

                        client.setFirstName(updatedClient.getFirstName());
                        client.setLastName(updatedClient.getLastName());
                        client.setEmail(updatedClient.getEmail());
                        return clientRepository.save(client);
                    })
                    .orElse(null);

        }

        public Client getClient(Long id) {
            return clientRepository.findById(id).orElse(null);

        }



        public List<Payment> getPaymentHistory(Long id) {
            return clientRepository.findById(id).map(client-> client.getPayments()).orElse(null);
        }

    }
