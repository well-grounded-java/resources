package ch18.fx.sealed;

import java.time.LocalDateTime;

public sealed interface FXOrderResponse
        permits FXAccepted, FXFill, FXReject, FXCancelled {
    LocalDateTime timestamp();
    long orderId();
}
