package tech.reliab.course.polyvyanyLab.bank.service.impl;

import tech.reliab.course.polyvyanyLab.bank.entity.*;
import tech.reliab.course.polyvyanyLab.bank.service.BankOfficeService;
import tech.reliab.course.polyvyanyLab.bank.service.BankService;
import tech.reliab.course.polyvyanyLab.bank.service.UserService;
import tech.reliab.course.polyvyanyLab.utils.Random;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class BankServiceImpl implements BankService {
    private final Map<Integer, Bank> banksTable = new HashMap<>();
    private final Map<Integer, List<BankOffice>> officesByBankIdTable = new HashMap<>();
    private final Map<Integer, List<User>> clientsByBankIdTable = new HashMap<>();
    private BankOfficeService bankOfficeService;
    private UserService userService;

    @Override
    public void setBankOfficeService(BankOfficeService bankOfficeService) {
        this.bankOfficeService = bankOfficeService;
    }

    @Override
    public void setClientService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<BankOffice> getAllOfficesByBankId(int id) {
        Bank bank = getBankById(id);
        if (bank != null) {
            return officesByBankIdTable.get(id);
        }
        return new ArrayList<>();
    }

    @Override
    public Bank create(Bank bank) {
        if (bank == null) {
            return null;
        }

        Bank newBank = new Bank(bank.getId(), bank.getName());

        newBank.setRating((byte) Random.getRandomNumber(10000));
        newBank.setTotalMoney(Random.getRandomNumber(1000000));
        calculateInterestRate(newBank);

        banksTable.put(newBank.getId(), newBank);
        officesByBankIdTable.put(newBank.getId(), new ArrayList<>());
        clientsByBankIdTable.put(newBank.getId(), new ArrayList<>());

        return newBank;
    }

    @Override
    public boolean addEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            employee.setBank(bank);
            bank.setEmployeeCount(bank.getEmployeeCount() + 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEmployee(Bank bank, Employee employee) {
        if (bank != null && employee != null) {
            final int newEmployeeCount = bank.getEmployeeCount() - 1;

            if (newEmployeeCount < 0) {
                System.err.println("[-] Bank - cannot remove employee, no employees");
                return false;
            }

            bank.setEmployeeCount(newEmployeeCount);

            return true;
        }
        return false;
    }

    @Override
    public Bank getBankById(int bankId) {
        Bank bank = banksTable.get(bankId);
        if (bank == null) {
            System.err.println("Bank with id " + bankId + " is not found");
        }
        return bank;
    }

    @Override
    public void printBankData(int bankId) {
        Bank bank = getBankById(bankId);
        if (bank == null) {
            return;
        }
        System.out.println("=====================");
        System.out.println(bank);

        List<BankOffice> offices = officesByBankIdTable.get(bankId);
        if (offices != null) {
            System.out.println("Offices:");
            offices.forEach((BankOffice office) -> {
                bankOfficeService.printBankOfficeData(office.getId());
            });
        }
        List<User> clients = clientsByBankIdTable.get(bankId);
        if (clients != null) {
            System.out.println("Clients:");
            clients.forEach((User user) -> {
                userService.printClientData(user.getId(), false);
            });
        }
        System.out.println("=====================");
    }

    @Override
    public boolean deleteBankById(int bankId) {
        return true;
    }

    @Override
    public List<Bank> getAllBanks() {
        return new ArrayList<>(banksTable.values());
    }

    @Override
    public boolean addOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        if (bank != null && bankOffice != null) {
            bankOffice.setBank(bank);
            bank.setOfficeCount(bank.getOfficeCount() + 1);
            bank.setAtmCount(bank.getAtmCount() + bankOffice.getAtmCount());
            depositMoney(bankId, bankOffice.getTotalMoney());
            List<BankOffice> bankOffices = getAllOfficesByBankId(bankId);
            bankOffices.add(bankOffice);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeOffice(int bankId, BankOffice bankOffice) {
        Bank bank = getBankById(bankId);
        int officeIndex = officesByBankIdTable.get(bankId).indexOf(bankOffice);
        if (bank != null && officeIndex >= 0) {
            final int newOfficeCount = bank.getOfficeCount() - 1;

            if (newOfficeCount < 0) {
                System.err.println("[-] Bank - cannot remove office, no offices");
                return false;
            }

            bank.setOfficeCount(newOfficeCount);

            bank.setAtmCount(bank.getAtmCount() - officesByBankIdTable.get(bankId).get(officeIndex).getAtmCount());
            officesByBankIdTable.get(bankId).remove(officeIndex);

            return true;
        }
        return false;
    }

    @Override
    public boolean addClient(int id, User user) {
        Bank bank = getBankById(id);
        if (bank != null && user != null) {
            user.setBank(bank);
            bank.setUserCount(bank.getUserCount() + 1);
            List<User> clients = clientsByBankIdTable.get(id);
            clients.add(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeClient(Bank bank, User user) {
        if (bank != null && user != null) {
            int newClientCount = bank.getUserCount() - 1;

            if (newClientCount < 0) {
                System.err.println("[-] Bank - cannot remove user, no clients");
                return false;
            }

            bank.setUserCount(newClientCount);
            return true;
        }
        return false;
    }

    @Override
    public boolean approveCredit(Bank bank, CreditAccount account, Employee employee) {
        return false;
    }

    @Override
    public double calculateInterestRate(Bank bank) {
        if (bank != null) {
            double rating = bank.getRating();

            java.util.Random random = new java.util.Random();

            double centralBankInterestRate = random.nextDouble() * 20;

            double maxBankInterestRateMargin = 20 - centralBankInterestRate;

            double bankInterestRateMargin = (random.nextDouble() * maxBankInterestRateMargin) * ((110.0 - rating) / 100.0);

            double interestRate = centralBankInterestRate + bankInterestRateMargin;

            bank.setInterestRate(interestRate);
            return interestRate;
        }
        return 0;
    }

    @Override
    public boolean depositMoney(int id, double amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("[-] Bank - cannot deposit money to uninitialized bank");
            return false;
        }

        if (amount <= 0.0) {
            System.err.println("[-] Bank - cannot deposit money - deposit amount must be positive");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney()+amount);
        return true;
    }

    @Override
    public boolean withdrawMoney(int id, double amount) {
        Bank bank = getBankById(id);
        if (bank == null) {
            System.err.println("[-] Bank - cannot withdraw money, bank is null");
            return false;
        }

        if (amount <= 0.0) {
            System.err.println("[-] Bank - cannot withdraw money - withdraw amount must be positive");
            return false;
        }

        if ((bank.getTotalMoney() - amount) < 0) {
            System.err.println("[-] Bank - cannot withdraw money - bank does not have enough money");
            return false;
        }

        bank.setTotalMoney(bank.getTotalMoney() - amount);
        return true;

    }
}