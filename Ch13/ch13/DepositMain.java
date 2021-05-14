package ch13;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class DepositMain {
    public static void main(String[] args) {
        var account = new Account(100);
        var deposit = Deposit.of(42.0, LocalDate.now(), account);
        try {
            Field f = Deposit.class.getDeclaredField("amount");
            f.setAccessible(true);
            f.setDouble(deposit, 21.0);
            System.out.println("Value: "+ deposit.amount());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
