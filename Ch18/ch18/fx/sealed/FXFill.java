package ch18.fx.sealed;

import java.time.LocalDateTime;

public record FXFill(LocalDateTime timestamp, long orderId, double price, long units)
        implements FXOrderResponse {

    public FXFill {
        if (units < 1) {
            throw new IllegalArgumentException("FXFill units must be positive");
        }
        if (orderId < 0) {
            throw new IllegalArgumentException("FXFill orderId must be positive");
        }
        if (price <= 0.0) {
            throw new IllegalArgumentException("FXFill price must be positive");
        }
    }

    public static FXFill of(long orderId, double price, long units) {
        return new FXFill(LocalDateTime.now(), orderId, price, units);
    }

}
