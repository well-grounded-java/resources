package ch18.fx.sealed;

import java.time.LocalDateTime;

public record FXAccepted(LocalDateTime timestamp, long orderId)
        implements FXOrderResponse {
    public static FXAccepted of(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be > 0");
        }
        return new FXAccepted(LocalDateTime.now(), orderId);
    }
}
