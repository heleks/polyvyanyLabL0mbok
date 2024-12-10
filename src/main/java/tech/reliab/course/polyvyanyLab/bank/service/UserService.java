package tech.reliab.course.polyvyanyLab.bank.service;

import java.util.List;

import tech.reliab.course.polyvyanyLab.bank.entity.CreditAccount;
import tech.reliab.course.polyvyanyLab.bank.entity.PaymentAccount;
import tech.reliab.course.polyvyanyLab.bank.entity.User;

public interface UserService {
    User create(User user);

    public void printClientData(int id, boolean withAccounts);

    public User getClientById(int id);

    public List<User> getAllUsers();

    public boolean addPaymentAccount(int id, PaymentAccount account);

    public boolean addCreditAccount(int id, CreditAccount account);

    public List<PaymentAccount> getAllPaymentAccountsByUserId(int id);

    public List<CreditAccount> getAllCreditAccountsByUserId(int id);

    double calculateCreditRating(User user);
}