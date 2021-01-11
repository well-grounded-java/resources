package ch04;

import java.time.LocalDate;

public class Deposit {
    private final double amount;
    private final LocalDate date;
    private final Account payee;

    private Deposit(double amount, LocalDate date, Account payee) {
        this.amount = amount;
        this.date = date;
        this.payee = payee;
    }

    public static Deposit of(double amount, LocalDate date, Account payee) {
        return new Deposit(amount, date, payee);
    }

    public static Deposit of(double amount, Account payee) {
        return new Deposit(amount, LocalDate.now(), payee);
    }

    public double amount() {
        return amount;
    }

    public LocalDate date() {
        return date;
    }

    public Account payee() {
        return payee;
    }

    public Deposit roll() {
        // Log audit event for rolling the date
        return new Deposit(amount, date.plusDays(1), payee);
    }

    public Deposit amend(double newAmount) {
        // Log audit event for amending the amount
        return new Deposit(newAmount, date, payee);
    }

    public static class DepositBuilder implements Builder<Deposit> {
        private double amount;
        private LocalDate date;
        private Account payee;

        public DepositBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public DepositBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public DepositBuilder payee(Account payee) {
            this.payee = payee;
            return this;
        }

        @Override
        public Deposit build() {
            return new Deposit(amount, date, payee);
        }
    }
}
