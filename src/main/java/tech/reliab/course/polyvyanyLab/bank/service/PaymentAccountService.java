package tech.reliab.course.polyvyanyLab.bank.service;

import java.util.List;

import tech.reliab.course.polyvyanyLab.bank.entity.PaymentAccount;

public interface PaymentAccountService {
    PaymentAccount create(PaymentAccount paymentAccount);

    public void printPaymentData(int id);

    public PaymentAccount getPaymentAccountById(int id);

    public List<PaymentAccount> getAllPaymentAccounts();

    boolean depositMoney(PaymentAccount paymentAccount, double amount);

    boolean withdrawMoney(PaymentAccount paymentAccount, double amount);
}