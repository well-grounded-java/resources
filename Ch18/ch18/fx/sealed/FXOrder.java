package ch18.fx.sealed;

import ch17.fx.CurrencyPair;
import ch17.fx.Side;

import java.time.LocalDateTime;

public sealed interface FXOrder permits MarketOrder, LimitOrder {
    int units();
    CurrencyPair pair();
    Side side();
    LocalDateTime sentAt();
}
