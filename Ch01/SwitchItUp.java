import java.lang.IllegalArgumentException;

// Comparing and contrasting switch statements and expressions in Java 14+
public class SwitchItUp {

  public static void main(String args[]) {
    classicSwitch(9);
    classicSwitchGrouping(9);
    classicSwitchCaptureVariables(9);
    expressionWithYield(9);
    expressionWithNewLabelling(9);
  }

  public static void classicSwitch(int month) {
    switch(month) {
      case 1:
        System.out.println("January");
        break;
      case 2:
        System.out.println("February");
        break;
      // ... and so on
      case 9:
        System.out.println("September");
        break;
      // ... and so forth
    }
  }

  public static void classicSwitchGrouping(int month) {
    switch(month) {
      case 12:
      case 1:
      case 2:
        System.out.println("Winter, brrrr");
        break;
      case 3:
      case 4:
      case 5:
        System.out.println("Spring has sprung!");
        break;
      // ... and so on
    }
  }

  public static void classicSwitchCaptureVariables(int month) {
    String message = null;
    switch(month) {
      case 12:
      case 1:
      case 2:
        message = "Winter, brrrr";
        break;
      case 3:
      case 4:
      case 5:
        message = "Spring has sprung!";
        break;
      case 6:
      case 7:
      case 8:
        message = "Summer is here!";
        break;
      case 9:
      case 10:
      case 11:
        message = "Fall has descended on us";
        break;
    }
    System.out.println(message);
  }

  public static void expressionWithYield(int month) {
    String message = switch(month) {
      case 12:
      case 1:
      case 2:
        yield "Winter, brrrr";
      case 3:
      case 4:
      case 5:
        yield "Spring has sprung!";
      case 6:
      case 7:
      case 8:
        yield "Summer is here!";
      case 9:
      case 10:
      case 11:
        yield "Fall has descended on us";
      default:
        throw new IllegalArgumentException("Oops, that's not a month");
    };
    System.out.println(message);
  }

  public static void expressionWithNewLabelling(int month) {
    String message = switch(month) {
      case 1, 2, 12  -> "Winter, brrrr";
      case 3, 4, 5   -> "Spring has sprung!";
      case 6, 7, 8   -> "Summer is here!";
      case 9, 10, 11 -> "Fall has descended on us";
      // If we exclude default, code won't compile as the switch misses branches
      default -> { throw new IllegalArgumentException("Oops, that's not a month"); }
    };
    System.out.println(message);
  }
}
