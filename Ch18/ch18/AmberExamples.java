package ch18;

import static ch17.fx.CurrencyPair.*;
import static ch17.fx.Side.*;
import ch18.fx.sealed.FXOrder;
import ch18.fx.sealed.LimitOrder;

import java.time.LocalDateTime;

public class AmberExamples {

    public static void main(String[] args) {

    }

    void run2(Object o) {
        if (o instanceof String[] { String s1, String s2, ... }){
            System.out.println(s1 + s2);
        }
    }

    void run1() {
        FXOrder order = new LimitOrder(1, GBPUSD, BUY, LocalDateTime.now(), 1.25,1000);

        var isMarket = switch (order) {
            case MarketOrder(int units, CurrencyPair pair, Side side, LocalDateTime sent, boolean allOrNothing) -> true;
            case LimitOrder(int units, CurrencyPair pair, Side side, LocalDateTime sent double price, int ttl) -> false;
        };
        System.out.println(isMarket);
    }
}
