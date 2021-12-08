package ch18.fx.sealed;

import java.time.LocalDateTime;

public record FXReject(LocalDateTime timestamp, long orderId, String reason)
        implements FXOrderResponse {

    public FXReject {
        if (orderId < 0) {
            throw new IllegalArgumentException("FXCancelled orderId must be positive");
        }
    }

    public static FXReject of(long orderId, String reason) {
        return new FXReject(LocalDateTime.now(), orderId, reason);
    }


}
