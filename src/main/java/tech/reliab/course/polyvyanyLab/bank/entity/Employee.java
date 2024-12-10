package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Employee extends Person {

    public enum Job {
        CEO,
        VaultKeeper,
        Programmer,
        Lawyer,
        Cashier,
        Manager;

        public static Job getRandom() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    private Job job;
    private Bank bank;
    private boolean isWorkingFromHome;
    private BankOffice bankOffice;
    private boolean isCreditAvailable;
    private double salary;

    public Employee() {
        super();
        job = null;
        bank = null;
        isWorkingFromHome = false;
        bankOffice = null;
        isCreditAvailable = false;
        salary = 0.0;
    }

    public Employee(Employee employee) {
        super(employee.id, employee.name, employee.birthDate);
        job = employee.job;
        bank = new Bank(employee.bank);
        isWorkingFromHome = employee.isWorkingFromHome;
        bankOffice = new BankOffice(employee.bankOffice);
        isCreditAvailable = employee.isCreditAvailable;
        salary = employee.salary;
    }

    public Employee(String _name, LocalDate _birthDate, Job _job, Bank _bank, boolean _isWorkingFromHome,
                    BankOffice _bankOffice, boolean _isCreditAvailable, double _salary) {
        super(_name, _birthDate);
        job = _job;
        bank = _bank;
        isWorkingFromHome = _isWorkingFromHome;
        bankOffice = _bankOffice;
        isCreditAvailable = _isCreditAvailable;
        salary = _salary;
    }

    @Override
    public String toString() {
        return "Employee:{" +
                "\n person='" + super.toString() + "'" +
                ",\n job='" + getJob() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n isWorkingFromHome='" + isWorkingFromHome() + "'" +
                ",\n bankOffice='" + getBankOffice() + "'" +
                ",\n isCreditAvailable='" + isCreditAvailable() + "'" +
                ",\n salary='" + String.format("%.2f", getSalary()) + "'" +
                "\n}";
    }
}