package ch18.fx.sealed;

public class OrderPatterns {

    public static void main(String[] args) {

    }

    void run() {
        FXOrderResponse resp = FXAccepted.of(1137);
        var msg = switch (resp) {
            case FXAccepted a  -> a.orderId() + " Accepted";
            case FXFill f && f.units() < 100 -> f.orderId() + " Small Fill";
            case FXFill f      -> f.orderId() + " Filled "+ f.units();
            case FXReject r    -> r.orderId() + " Rejected: "+ r.reason();
            case FXCancelled c -> c.orderId() + " Cancelled";
            case null -> "Order is null";
        };
        System.out.println(msg);
    }
}
