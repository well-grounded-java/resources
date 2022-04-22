import java.util.Set;
import java.util.stream.Collectors;

public class OtterApp {
    private static Keeper kate = new Keeper();
    private static Keeper bob = new Keeper();

    public static void main(String[] args) {
        // Otters
        Set<Otter> ots = getOtters();
        System.out.println(ots.stream()
                .filter(o -> !o.isWild())
                .map(o -> o.getKeeper())
                .filter(k -> k.isTrainee())
                .collect(Collectors.toList())
                .size());

        // Otter average age
        double aveAge = ((double) ots.stream()
                .map(o -> o.getAge())
                .reduce(0, (x, y) -> x + y)) / ots.size();
        System.out.println("Average age: "+ aveAge);

        // Bob the backup
        ots.stream()
                .filter(o -> !o.isWild())
                .filter(o -> o.getKeeper().equals(kate))
                .forEach(o -> o.setKeeper(bob));
    }

    private static Set<Otter> getOtters() {
        var splash = new Otter();

        splash.incAge();
        splash.setKeeper(kate);

        return Set.of(splash);
    }
}
