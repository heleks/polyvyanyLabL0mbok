package tech.reliab.course.polyvyanyLab.bank.service.impl;

import tech.reliab.course.polyvyanyLab.bank.entity.BankAtm;
import tech.reliab.course.polyvyanyLab.bank.service.AtmService;
import tech.reliab.course.polyvyanyLab.bank.service.BankOfficeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class AtmServiceImpl implements AtmService {
    private final Map<Integer, BankAtm> atmsTable = new HashMap<>();
    private final BankOfficeService bankOfficeService;

    @Override
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(atmsTable.values());
    }

    @Override
    public BankAtm getBankAtmById(int id) {
        BankAtm atm = atmsTable.get(id);
        if (atm == null) {
            System.err.println("Atm with id " + id + " is not found");
        }

        return atm;
    }

    public AtmServiceImpl(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public BankAtm create(BankAtm bankAtm) {
        if (bankAtm == null) {
            return null;
        }
        if (bankAtm.getTotalMoney() < 0.0) {
            System.err.println("[-] Cannot create BankAtm - totalMoney must be non-negative");
            return null;
        }
        if (bankAtm.getMaintenanceCost() < 0.0) {
            System.err.println("[-] Cannot create BankAtm - maintenanceCost must be non-negative");
            return null;
        }
        if (bankAtm.getBankOffice() == null) {
            System.err.println("[-] Cannot create BankAtm - bankOffice cannot be null");
            return null;
        }
        BankAtm atm = new BankAtm(bankAtm);
        atmsTable.put(atm.getId(), atm);
        bankOfficeService.installAtm(atm.getBankOffice().getId(), atm);
        return atm;
    }

    @Override
    public boolean depositMoney(BankAtm bankAtm, double amount) {
        if (bankAtm == null) {
            System.err.println("[-] BankAtm cannot deposit money - non existing ATM");
            return false;
        }

        if (amount <= 0.0) {
            System.err.println("[-] BankAtm cannot deposit money - amount is not positive");
            return false;
        }

        bankAtm.setTotalMoney(bankAtm.getTotalMoney() + amount);

        return true;
    }

    @Override
    public boolean withdrawMoney(BankAtm bankAtm, double amount) {
        if (bankAtm == null) {
            System.err.println("[-] BankAtm cannot withdraw money - non existing ATM");
            return false;
        }

        if (amount <= 0.0) {
            System.err.println("[-] BankAtm cannot withdraw money - amount is not positive");
            return false;
        }

        if ((bankAtm.getTotalMoney() - amount) < 0.0) {
            System.err.println("[-] BankAtm cannot withdraw money - ATM does not have enough money");
            return false;
        }

        bankAtm.setTotalMoney(bankAtm.getTotalMoney() - amount);

        return true;
    }
}