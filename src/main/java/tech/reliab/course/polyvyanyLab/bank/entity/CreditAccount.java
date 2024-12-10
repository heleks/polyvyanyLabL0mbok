package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class CreditAccount extends Account {
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int monthCount;
    private double creditAmount;
    private double remainingCreditAmount;
    private double monthlyPayment;
    private double interestRate;
    private Employee employee;
    private PaymentAccount paymentAccount;

    public CreditAccount() {
        super();
        initWithDefaults();
    }

    public CreditAccount(CreditAccount creditAccount) {
        super(creditAccount.id, creditAccount.user, creditAccount.bank);
        dateStart = creditAccount.dateStart;
        dateEnd = creditAccount.dateEnd;
        monthCount = creditAccount.monthCount;
        creditAmount = creditAccount.creditAmount;
        remainingCreditAmount = creditAccount.remainingCreditAmount;
        monthlyPayment = creditAccount.monthlyPayment;
        interestRate = creditAccount.interestRate;
        employee = new Employee(creditAccount.employee);
        paymentAccount = new PaymentAccount(creditAccount.paymentAccount);
    }

    public CreditAccount(User _user, Bank _bank, LocalDate _dateStart, LocalDate _dateEnd, int _monthCount,
                         double _creditAmount, double _remainingCreditAmount, double _monthlyPayment,
                         double _interestRate, Employee _employee, PaymentAccount _paymentAccount) {
        super(_user, _bank);
        dateStart = _dateStart;
        dateEnd = _dateEnd;
        monthCount = _monthCount;
        creditAmount = _creditAmount;
        remainingCreditAmount = _remainingCreditAmount;
        monthlyPayment = _monthlyPayment;
        interestRate = _interestRate;
        employee = _employee;
        paymentAccount = _paymentAccount;
    }

    @Override
    public String toString() {
        return "CreditAccount:{" +
                "\n account='" + super.toString() + "'" +
                ",\n dateStart='" + getDateStart() + "'" +
                ",\n dateEnd='" + getDateEnd() + "'" +
                ",\n monthCount='" + getMonthCount() + "'" +
                ",\n creditAmount='" + String.format("%.2f", getCreditAmount()) + "'" +
                ",\n remainingCreditAmount='" + String.format("%.2f", getRemainingCreditAmount()) + "'" +
                ",\n montlyPayment='" + String.format("%.2f", getMonthlyPayment()) + "'" +
                ",\n interestRate='" + String.format("%.2f", getInterestRate()) + "'" +
                ",\n employee='" + getEmployee() + "'" +
                ",\n paymentAccount='" + getPaymentAccount() + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        dateStart = null;
        dateEnd = null;
        monthCount = 0;
        creditAmount = 0.0;
        remainingCreditAmount = 0.0;
        monthlyPayment = 0.0;
        interestRate = 0.0;
        employee = null;
        paymentAccount = null;
    }
}