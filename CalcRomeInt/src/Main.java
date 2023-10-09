import java.util.*;
import java.util.stream.IntStream;

public class Main {

    private static int IllegalArgExp(String msg) {
        throw new IllegalArgumentException("Invalid input: " + msg);
    }
    public static String calcFun(String[] inp){

        String[] rome_numbers = new String[] {"I","II","III","IV","V","VI","VII","VIII","IX","X"};

        int res = 0;
        boolean isRome = false;

        try {
            int x = 0, y = 0;
            if(Arrays.stream(rome_numbers).anyMatch(i->i.equals(inp[0])) &&
                    Arrays.stream(rome_numbers).anyMatch(i->i.equals(inp[2]))) {

                x = IntStream.range(0, rome_numbers.length).
                                filter(i -> rome_numbers[i].
                                equals(inp[0])).findFirst().
                                orElse(-1) + 1;

                y = IntStream.range(0, rome_numbers.length).
                                filter(i -> rome_numbers[i].
                                equals(inp[2])).findFirst().
                                orElse(-1) + 1;

                isRome = true;

            } else {
                x = Integer.parseInt(inp[0]);
                y = Integer.parseInt(inp[2]);
                if(x < 1 || y < 1 || x > 10 || y > 10) {
                    IllegalArgExp(Arrays.stream(inp).reduce("",(msg,i)-> msg + i + " ").trim());

                }
            }
            res = switch (inp[1]) {
                case "+" -> x + y;
                case "-" -> x - y;
                case "*" -> x * y;
                case "/" -> x / y;
                default -> IllegalArgExp(Arrays.stream(inp).reduce("",(msg,i)-> msg + i + " ").trim());

            };
        }catch (IllegalArgumentException e){
            IllegalArgExp(Arrays.stream(inp).reduce("",(msg,i)-> msg + i + " ").trim());
        }

        if(isRome) {
            if(res < 1) {
                IllegalArgExp(Arrays.stream(inp).reduce("",(msg,i)-> msg + i + " ").trim());
            }
            return getRome(res);
        }
        return String.valueOf(res);
    }
    public static String getRome(int res) {

        StringBuilder str = new StringBuilder();
        int[] numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rome = {"C","XC","L","XL","X","IX","V","IV","I"};

        for (int i = 0; i < numbers.length; i++) {
            while (res >= numbers[i]) {
                str.append(rome[i]);
                res -= numbers[i];
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] inp = sc.nextLine().split(" ");
        if(inp.length != 3) {
            IllegalArgExp(Arrays.stream(inp).reduce("",(msg,i)-> msg + i + " ").trim());
        }
        System.out.println(calcFun(inp));
    }
}