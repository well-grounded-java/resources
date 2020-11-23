import java.util.*;
import java.util.function.*;
import var.Var;
import static var.Var.var;

public class Ch01 {

    public static void main(String[] args) {
        Map<Integer, Map<String, String>> usersLists = new HashMap<>();

        Function<String, Integer> lengthFn = s -> s.length();

        var var = var();
        if (var == null) {
            var(new Var());
        }
        System.out.println(var());
    }

}
