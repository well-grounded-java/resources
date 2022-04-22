import java.util.List;
import java.util.stream.Collectors;

public class StringStream {
    public static void main(String[] args) {
        // Example find
        List<String> myStrings = getSomeStrings();
        String search = getSearchString();

        System.out.println(myStrings.stream()
                .filter(s -> s.equals(search))
                .collect(Collectors.toList()));
    }

    private static List<String> getSomeStrings() {
        return List.of(
                "this",
                "is",
                "a",
                "list"
        );
    }

    private static String getSearchString() {
        return "list";
    }
}