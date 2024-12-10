package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BankOffice {
    private static int currentId;
    private int id;
    private String name;
    private String address;
    private Bank bank;
    private boolean isWorking;
    private boolean isAtmPlaceable;
    private int atmCount;
    private boolean isCreditAvailable;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private double totalMoney;
    private double rentPrice;

    private void initId() {
        id = currentId++;
    }

    public BankOffice(String _name, String _address) {
        initId();
        initWithDefaults();
        name = _name;
        address = _address;
    }

    public BankOffice(String _name, String _address, Bank _bank, boolean _isWorking, boolean _isAtmPlaceable,
                      int _atmCount, boolean _isCreditAvailable, boolean _isCashWithdrawalAvailable, boolean _isCashDepositAvailable,
                      double _totalMoney, double _rentPrice) {
        initId();
        initWithDefaults();
        name = _name;
        address = _address;
        bank = _bank;
        isWorking = _isWorking;
        isAtmPlaceable = _isAtmPlaceable;
        atmCount = _atmCount;
        isCreditAvailable = _isCreditAvailable;
        isCashWithdrawalAvailable = _isCashWithdrawalAvailable;
        isCashDepositAvailable = _isCashDepositAvailable;
        totalMoney = _totalMoney;
        rentPrice = _rentPrice;
    }

    public BankOffice(BankOffice bankOffice) {
        id = bankOffice.id;
        name = bankOffice.name;
        address = bankOffice.address;
        bank = new Bank(bankOffice.bank);
        isWorking = bankOffice.isWorking;
        isAtmPlaceable = bankOffice.isAtmPlaceable;
        atmCount = bankOffice.atmCount;
        isCreditAvailable = bankOffice.isCreditAvailable;
        isCashWithdrawalAvailable = bankOffice.isCashWithdrawalAvailable;
        isCashDepositAvailable = bankOffice.isCashDepositAvailable;
        totalMoney = bankOffice.totalMoney;
        rentPrice = bankOffice.rentPrice;
    }

    @Override
    public String toString() {
        return "BankOffice:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorking='" + isWorking() + "'" +
                ",\n isAtmPlaceable='" + isAtmPlaceable() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n rentPrice='" + String.format("%.2f", getRentPrice()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        bank = null;
        isWorking = false;
        isAtmPlaceable = false;
        atmCount = 0;
        isCreditAvailable = false;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = 0.0;
        rentPrice = 0.0;
    }
}