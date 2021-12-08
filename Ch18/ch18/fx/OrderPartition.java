package ch18.fx;

import ch18.fx.records.FXOrder;

record OrderPartition(CurrencyPair pair, Side side) {
    public OrderPartition(FXOrder order) {
        this(order.pair(), order.side());
    }

}