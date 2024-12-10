package tech.reliab.course.polyvyanyLab.bank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.reliab.course.polyvyanyLab.bank.entity.PaymentAccount;
import tech.reliab.course.polyvyanyLab.bank.service.PaymentAccountService;
import tech.reliab.course.polyvyanyLab.bank.service.UserService;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final Map<Integer, PaymentAccount> paymentAccountsTable = new HashMap<>();
    private final UserService userService;

    public PaymentAccountServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public PaymentAccount create(PaymentAccount paymentAccount) {
        if (paymentAccount == null) {
            return null;
        }

        if (paymentAccount.getBalance() < 0.0) {
            System.err.println("[-] PaymentAccount - payment account balance must be non-negative");
            return null;
        }

        PaymentAccount newAccount = new PaymentAccount(paymentAccount);
        paymentAccountsTable.put(newAccount.getId(), newAccount);
        userService.addPaymentAccount(newAccount.getUser().getId(), newAccount);

        return newAccount;
    }

    @Override
    public boolean depositMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("[-] PaymentAccount - non existing payment account");
            return false;
        }

        if (amount <= 0.0) {
            System.err.println("[-] PaymentAccount - deposit amount must be positive");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance()+amount);
        return true;
    }

    @Override
    public boolean withdrawMoney(PaymentAccount paymentAccount, double amount) {
        if (paymentAccount == null) {
            System.err.println("[-] PaymentAccount - non existing payment account");
            return false;
        }

        if (amount <= 0.0) {
            System.err
                    .println("[-] PaymentAccount - withdrawal amount must be positive");
            return false;
        }

        if ((paymentAccount.getBalance()-amount) < 0.0) {
            System.err.println("[-] PaymentAccount - not enough money");
            return false;
        }

        paymentAccount.setBalance(paymentAccount.getBalance()-amount);

        return true;
    }

    @Override
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<PaymentAccount>(paymentAccountsTable.values());
    }

    @Override
    public PaymentAccount getPaymentAccountById(int id) {
        PaymentAccount account = paymentAccountsTable.get(id);
        if (account == null) {
            System.err.println("Payment account with id " + id + " is not found");
        }
        return account;
    }

    @Override
    public void printPaymentData(int id) {
        PaymentAccount account = getPaymentAccountById(id);
        if (account == null) {
            return;
        }

        System.out.println(account);
    }
}