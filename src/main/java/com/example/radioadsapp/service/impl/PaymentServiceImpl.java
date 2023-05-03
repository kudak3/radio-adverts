package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.repository.PaymentRepository;
import com.example.radioadsapp.service.PaymentsService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentsService {

//    @Autowired
//    private NotificationRepository notificationRepository;

    final
    PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public List<Payment> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Payment> payments = new ArrayList<>();
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if ("ADVERTISER".equals(authority.getAuthority())) {
                payments = paymentRepository.findAllByCreatedBy(auth.getName());
            } else if ("RADIOSTATION".equals(authority.getAuthority())) {
                payments = paymentRepository.findAllByRadioStation_CreatedBy(auth.getName());
            }else{
                payments = paymentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            }

        }
        return payments;
    }

    public Payment save(Payment payment) {
//        NotificationEntity notificationEntity = new NotificationEntity("New Payment created by " + payment.getAccountNumber(), new Date(), "adverts", false, true);

        payment.setNewEntry(true);
        payment.setDate(new Date());
//        notificationRepository.save(notificationEntity);
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        try {
            paymentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }


}

