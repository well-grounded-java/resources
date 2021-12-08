package ch18.fx;

import java.time.LocalDateTime;

public final class FXOrderClassic {
    private final int units;
    private final CurrencyPair pair;
    private final Side side;
    private final double price;
    private final LocalDateTime sentAt;
    private final int ttl;

    public FXOrderClassic(int units, CurrencyPair pair, Side side, double price, LocalDateTime sentAt, int ttl) {
        this.units = units;
        this.pair = pair; // CurrencyPair is a simple enum
        this.side = side; // Side is a simple enum
        this.price = price;
        this.sentAt = sentAt;
        this.ttl = ttl;
    }

    public int units() {
        return units;
    }

    public CurrencyPair pair() {
        return pair;
    }

    public Side side() {
        return side;
    }

    public double price() { return price; }

    public LocalDateTime sentAt() {
        return sentAt;
    }

    public int ttl() {
        return ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FXOrderClassic that = (FXOrderClassic) o;

        if (units != that.units) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (ttl != that.ttl) return false;
        if (pair != that.pair) return false;
        if (side != that.side) return false;
        return sentAt != null ? sentAt.equals(that.sentAt) : that.sentAt == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = units;
        result = 31 * result + (pair != null ? pair.hashCode() : 0);
        result = 31 * result + (side != null ? side.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (sentAt != null ? sentAt.hashCode() : 0);
        result = 31 * result + ttl;
        return result;
    }

    @Override
    public String toString() {
        return "FXOrderClassic{" +
                "units=" + units +
                ", pair=" + pair +
                ", side=" + side +
                ", price=" + price +
                ", sentAt=" + sentAt +
                ", ttl=" + ttl +
                '}';
    }
}