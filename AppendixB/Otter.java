public class Otter {
    private boolean wild;
    private Keeper keeper;
    private int age;

    public boolean isWild() {
        return wild;
    }

    public Keeper getKeeper() {
        return keeper;
    }

    public void setKeeper(Keeper keeper) {
        this.keeper = keeper;
    }

    public void incAge() {
        this.age++;
    }

    public int getAge() {
        return age;
    }
}
