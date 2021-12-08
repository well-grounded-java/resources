package ch18.fx.sealed;

import java.time.LocalDateTime;

public record FXCancelled(LocalDateTime timestamp, long orderId, long unitsCancelled)
        implements FXOrderResponse {

    public FXCancelled {
        if (orderId < 0) {
            throw new IllegalArgumentException("FXCancelled orderId must be positive");
        }
    }

    public static FXCancelled of(long orderId, long unitsCancelled) {
        return new FXCancelled(LocalDateTime.now(), orderId, unitsCancelled);
    }
}
