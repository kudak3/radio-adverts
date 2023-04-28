package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advert;
import com.example.radioadsapp.model.Client;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.model.User;
import com.example.radioadsapp.repository.ClientRepository;
import com.example.radioadsapp.service.ClientService;
import com.example.radioadsapp.utils.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            try {
                clientRepository.deleteById(id);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        public Client updateClient(Client updatedClient) {

            return clientRepository.findById(updatedClient.getId())
                    .map(client-> {
                        client.setFirstName(updatedClient.getFirstName());
                        client.setLastName(updatedClient.getLastName());
                        client.setEmail(updatedClient.getEmail());
                        client.setPhoneNumber(updatedClient.getPhoneNumber());
                        client.setGender(updatedClient.getGender());
                        client.setUser(updatedClient.getUser());
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
            return clientRepository.findById(id).map(client-> client.getPayments()).orElse(null);
        }

    }
