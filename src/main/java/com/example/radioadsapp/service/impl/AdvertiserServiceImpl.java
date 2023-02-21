package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Advertiser;
import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.repository.AdvertiserRepository;
import com.example.radioadsapp.service.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdvertiserServiceImpl implements AdvertiserService {

        private final AdvertiserRepository advertiserRepository;

        public AdvertiserServiceImpl(AdvertiserRepository advertiserRepository) {
            this.advertiserRepository = advertiserRepository;
        }



        public List<Advertiser> getAll() {
            return advertiserRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }

        public Advertiser save(Advertiser client) {
            return advertiserRepository.save(client);
        }

        public void delete(Long id) {
            advertiserRepository.deleteById(id);
        }

        public Advertiser updateAdvertiser(Advertiser updatedAdvertiser) {

            return advertiserRepository.findById(updatedAdvertiser.getId())
                    .map(advertiser-> {

                        advertiser.setFirstName(updatedAdvertiser.getFirstName());
                        advertiser.setLastName(updatedAdvertiser.getLastName());
                        advertiser.setEmail(updatedAdvertiser.getEmail());
                        return advertiserRepository.save(advertiser);
                    })
                    .orElse(null);

        }

        public Advertiser getAdvertiser(Long id) {
            return advertiserRepository.findById(id).orElse(null);

        }



        public List<Payment> getPaymentHistory(Long id) {
            return advertiserRepository.findById(id).map(advertiser-> advertiser.getPayments()).orElse(null);
        }

    }
