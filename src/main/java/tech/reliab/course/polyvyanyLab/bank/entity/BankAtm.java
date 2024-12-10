package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BankAtm {
    public enum Status {
        NOT_WORKING,
        WORKING,
        NO_MONEY
    }

    private static int currentId;
    private int id;
    private String name;
    private String address;
    private Status status;
    private Bank bank;
    private BankOffice bankOffice;
    private Employee employee;
    private boolean isCashWithdrawalAvailable;
    private boolean isCashDepositAvailable;
    private double totalMoney;
    private double maintenanceCost;

    private void initId() {
        id = currentId++;
    }

    public BankAtm(BankAtm bankAtm) {
        id = bankAtm.id;
        name = bankAtm.name;
        address = bankAtm.address;
        status = bankAtm.status;
        bank = new Bank(bankAtm.bank);
        bankOffice = new BankOffice(bankAtm.bankOffice);
        employee = new Employee(bankAtm.employee);
        isCashWithdrawalAvailable = bankAtm.isCashWithdrawalAvailable;
        isCashDepositAvailable = bankAtm.isCashDepositAvailable;
        totalMoney = bankAtm.totalMoney;
        maintenanceCost = bankAtm.maintenanceCost;
    }

    public BankAtm() {
        initId();
        initWithDefaults();
    }

    public BankAtm(String _name, String _address) {
        initId();
        initWithDefaults();
        name = _name;
        address = _address;
    }

    public BankAtm(String _name, String _address, Status _status, Bank _bank, BankOffice _bankOffice,
                   Employee _employee, boolean _isCashWithdrawalAvailable, boolean _isCashDepositAvailable, double _totalMoney,
                   double _maintenanceCost) {
        initId();
        initWithDefaults();
        name = _name;
        address = _address;
        status = _status;
        bank = _bank;
        bankOffice = _bankOffice;
        employee = _employee;
        isCashWithdrawalAvailable = _isCashWithdrawalAvailable;
        isCashDepositAvailable = _isCashDepositAvailable;
        totalMoney = _totalMoney;
        maintenanceCost = _maintenanceCost;
    }

    @Override
    public String toString() {
        return "BankAtm:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n address='" + getAddress() + "'" +
                ",\n status='" + getStatus() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n isCashWithdrawalAvailable='" + isCashWithdrawalAvailable() + "'" +
                ",\n isCashDepositAvailable='" + isCashDepositAvailable() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n maintenanceCost='" + String.format("%.2f", getMaintenanceCost()) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        address = "No address";
        status = Status.NOT_WORKING;
        bank = null;
        bankOffice = null;
        employee = null;
        isCashWithdrawalAvailable = false;
        isCashDepositAvailable = false;
        totalMoney = 0.0;
        maintenanceCost = 0.0;
    }
}