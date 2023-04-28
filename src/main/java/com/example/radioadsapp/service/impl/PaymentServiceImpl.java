package com.example.radioadsapp.service.impl;

import com.example.radioadsapp.model.Payment;
import com.example.radioadsapp.repository.PaymentRepository;
import com.example.radioadsapp.service.PaymentsService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        return paymentRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
        }catch (Exception e){
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

