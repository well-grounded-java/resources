package ch04;

import java.time.LocalDate;
import java.util.Comparator;

public final class Deposit implements Comparable<Deposit> {
    private final double amount;
    private final LocalDate date;
    private final Account payee;

    private Deposit(double amount, LocalDate date, Account payee) {
        this.amount = amount;
        this.date = date;
        this.payee = payee;
    }

//    @Override
//    public int compareTo(Deposit other) {
//        return Comparator.nullsFirst(LocalDate::compareTo).compare(this.date, other.date);
//    }

    @Override
    public int compareTo(Deposit other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare to null");
        }
        if (this.date == null || other.date == null) {
            throw new IllegalArgumentException("Cannot compare to null date: "+ this +" ; "+ other);
        }

        return this.date.compareTo(other.date);
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
