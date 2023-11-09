
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа не больше 10 (в т.ч. римские) и операцию, необходимую между ними выполнить\n" +
                "Пример: 1 + 2\nАрифметические операции, которые поддерживает приложение:\n" +
                "+ - сложение, - - вычитание, / - деление, * - умножение\n" +
                "Примечание: нельзя использовать и римские и арабские цифры, а также значения не целые значения");
        System.out.print("Введите пример:\n");
        String inputText = scanner.nextLine();
        int x = 0, y = 0;
        String[] words = SplitTextSpace(inputText);
        if (words.length != 3){
            System.out.print("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)\n" +
                    "Или вы забыли поставить пробел между цифрами и математической операции, пример: 2 + 2");
            System.exit(0);
        }
        String a, b, operaiton;
        a = words[0];
        b = words[2];
        operaiton = words[1];
        double res;
        boolean isRim = false;

        if (isNumeric(a)) {
            if (isNumeric(b)) {
                try {
                    x = Integer.parseInt(a);
                    y = Integer.parseInt(b);
                }catch (IllegalArgumentException e) {
                    System.out.print("Можно использовать только целые числа!");
                    System.exit(0);
                }
            } else {
                System.out.print("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(0);
            }
        } else if (isNumeric(b)) {
            System.out.print("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            System.exit(0);
        } else {
            isRim = true;
            x = RimToInt(a);
            y = RimToInt(b);
        }
        boolean valueIsGreaterTen = false;
        valueIsGreaterTen = y > 10 || x > 10;
        if (valueIsGreaterTen) {
            System.out.print("Вводимые значения не могут быть больше 10");
            System.exit(0);
        }

        switch (operaiton){
            case "*" :
                res = x * y;
                if (isRim) {
                    int ires = (int) res;
                    Rim RimRes = Rim.valueOf(ires);
                    System.out.println("Результат:" + RimRes);
                }
                else {
                    int ires = (int) res;
                    System.out.println("Результат:" + ires);
                }
                break;
            case "+" :
                res = x + y;
                if (isRim) {
                    int ires = (int) res;
                    Rim RimRes = Rim.valueOf(ires);
                    System.out.println("Результат:" + RimRes);
                }
                else {
                    int ires = (int) res;
                    System.out.println("Результат:" + ires);
                }
                break;
            case "-" :
                res = x - y;
                if (isRim) {
                    int ires = (int) res;
                    try {
                        Rim RimResm = Rim.valueOf(ires);
                        if (RimResm != null) {
                            System.out.println("Результат:" + RimResm);
                            ;
                        } else {
                            System.out.print("в римской системе нет отрицательных чисел");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.print("в римской системе нет отрицательных чисел");
                    }
                }else {
                    int ires = (int) res;
                    System.out.println("Результат:" + ires);
                }
                break;
            case "/" :
                res = x / y;
                if (isRim) {
                    int ires = (int) res;
                    Rim RimRes = Rim.valueOf(ires);
                    System.out.println("Результат c округлением вверх: " + RimRes);
                }
                else {
                    System.out.println("Результат: "+res);;
                }
                break;
            default:
                System.out.print("Неверно введённая математическая операция\n" +
                        "Приложение поддерживает : + - сложение, - - вычитание, / - деление, * - умножение");
                System.exit(0);
                break;

        }

    }



        static int RimToInt (String RimValue){
            Rim myValue;
            int value = 0;

            {
                try {
                    myValue = Rim.valueOf(RimValue);
                    if (myValue != null) {
                        value = myValue.getValue();
                    } else {
                        System.out.print("Некорректно введены римские цифры\n" +
                                "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
                        System.exit(0);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.print("Некорректно введены римские цифры\n" +
                            "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
                    System.exit(0);
                }
            }
            if (value > 10) {
                System.out.print("Вводимые значения не могут быть больше 10\n" +
                        "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
                System.exit(0);
            }
            return value;
        }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

        static String[] SplitTextSpace (String text){
            String[] words = text.split(" ");
            return words;
        }
    }
