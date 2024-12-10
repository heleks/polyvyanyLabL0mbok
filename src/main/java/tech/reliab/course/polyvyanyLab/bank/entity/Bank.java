package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Bank {
    private static int currentId;
    private int id;
    private String name;
    private int officeCount;
    private int atmCount;
    private int employeeCount;
    private int userCount;
    private byte rating;
    private double totalMoney;
    private double interestRate;

    public Bank() {
        id = currentId++;
        name = "null";
        officeCount = 0;
        atmCount = 0;
        employeeCount = 0;
        userCount = 0;
        rating = 0;
        totalMoney = 0.0;
        interestRate = 0.0;
    }

    public Bank(Bank bank) {
        id = bank.id;
        name = bank.name;
        officeCount = bank.officeCount;
        atmCount = bank.atmCount;
        employeeCount = bank.employeeCount;
        userCount = bank.userCount;
        rating = bank.rating;
        totalMoney = bank.totalMoney;
        interestRate = bank.interestRate;
    }

    public Bank(String _name) {
        this();
        name = _name;
    }

    public Bank(int _id, String _name) {
        this();
        id = _id;
        name = _name;
    }

    @Override
    public String toString() {
        return "Bank:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n officeCount='" + getOfficeCount() + "'" +
                ",\n atmCount='" + getAtmCount() + "'" +
                ",\n employeeCount='" + getEmployeeCount() + "'" +
                ",\n clientCount='" + getUserCount() + "'" +
                ",\n rating='" + getRating() + "'" +
                ",\n totalMoney='" + String.format("%.2f", getTotalMoney()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                "\n}";
    }
}