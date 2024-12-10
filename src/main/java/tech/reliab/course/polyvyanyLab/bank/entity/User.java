package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class User extends Person {
    private String placeOfWork;
    private double monthlyIncome;
    private Bank bank;
    private double creditRating;

    public User() {
        placeOfWork = "null";
        monthlyIncome = 0.0;
        bank = null;
        creditRating = 0.0;
    }

    public User(User user) {
        super(user.id, user.name, user.birthDate);
        placeOfWork = user.placeOfWork;
        monthlyIncome = user.monthlyIncome;
        bank = new Bank(user.bank);
        creditRating = user.creditRating;
    }

    public User(String _name, LocalDate _birthDate, String _placeOfWork, double _monthlyIncome, Bank _bank,
                double _creditRating) {
        super(_name, _birthDate);
        name = _name;
        birthDate = _birthDate;
        placeOfWork = _placeOfWork;
        monthlyIncome = _monthlyIncome;
        bank = _bank;
        creditRating = _creditRating;
    }

    @Override
    public String toString() {
        return "Client:{" +
                "\n person='" + super.toString() + "'" +
                ",\n placeOfWork='" + getPlaceOfWork() + "'" +
                ",\n monthlyIncome='" + String.format("%.2f", getMonthlyIncome()) + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                ",\n creditRating='" + String.format("%.2f", getCreditRating()) + "'" +
                "\n}";
    }
}