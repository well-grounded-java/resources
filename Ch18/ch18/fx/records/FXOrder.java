package ch18.fx.records;

import ch18.fx.CurrencyPair;
import ch18.fx.Side;

import java.time.LocalDateTime;

public record FXOrder(int units,
                      CurrencyPair pair,
                      Side side,
                      double price,
                      LocalDateTime sentAt,
                      int ttl) {

    public FXOrder {
        if (units < 1) {
            throw new IllegalArgumentException("FXOrder units must be positive");
        }
        if (ttl < 0) {
            throw new IllegalArgumentException("FXOrder TTL must be positive, or 0 for market orders");
        }
        if (price <= 0.0) {
            throw new IllegalArgumentException("FXOrder price must be positive");
        }
    }

    public static FXOrder of(CurrencyPair pair, Side side, double price) {
        return new FXOrder(1, pair, side, price, LocalDateTime.now(), 1000);
    }

}
