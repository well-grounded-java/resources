package ch18.fx.sealed;

import ch17.fx.CurrencyPair;
import ch17.fx.Side;

import java.time.LocalDateTime;

public record LimitOrder(int units,
                         CurrencyPair pair,
                         Side side,
                         LocalDateTime sentAt,
                         double price,
                         int ttl) implements FXOrder {
    // constructors and factories ommitted
}
