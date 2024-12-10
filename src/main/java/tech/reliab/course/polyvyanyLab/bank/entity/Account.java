package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Account {
    private static int currentId;
    protected int id;
    protected User user;
    protected Bank bank;

    private void initId() {
        id = currentId++;
    }

    public Account() {
        id = currentId++;
        user = null;
        bank = null;
    }

    public Account(User _user, Bank _bank) {
        id = currentId++;
        user = _user;
        bank = _bank;
    }

    public Account(Account account) {
        id = account.id;
        user = account.user;
        bank = account.bank;
    }

    @Override
    public String toString() {
        return "Account:{" +
                "\n id='" + getId() + "'" +
                ",\n client='" + getUser() + "'" +
                ",\n bank='" + getBank().getName() + "'" +
                "\n}";
    }
}