
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, ScannerException {
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
                if (isInteger(x) && isInteger(y) == false) {
                    throw new ScannerException("Можно использовать только целые числа!");
                }
                    x = Integer.parseInt(a);
                    y = Integer.parseInt(b);
            } else {
                throw new ScannerException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else if (isNumeric(b)) {
            throw new ScannerException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else {
            isRim = true;
            x = RimToInt(a);
            y = RimToInt(b);
        }
        boolean valueIsGreaterTen = false;
        valueIsGreaterTen = y > 10 || x > 10;
        if (valueIsGreaterTen) {
            throw new ScannerException("Вводимые значения не могут быть больше 10");
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
                    if (ires < 0) {
                        throw new ScannerException("в римской системе нет отрицательных чисел");
                    }
                    Rim RimResm = Rim.valueOf(ires);
                        if (RimResm != null){
                        System.out.println("Результат:" + RimResm);
                        } else {
                            throw new ScannerException("в римской системе нет отрицательных чисел");
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
                throw new ScannerException("Неверно введённая математическая операция\n" +
                        "Приложение поддерживает : + - сложение, - - вычитание, / - деление, * - умножение");

        }

    }
    private static boolean isInteger(double number) {
        return Math.floor(number) == number;
    }


        static int RimToInt (String RimValue) throws ScannerException {
            Rim myValue;
            int value = 0;

            {
                try {
                    myValue = Rim.valueOf(RimValue);
                    if (myValue != null) {
                        value = myValue.getValue();
                    } else {
                        throw new ScannerException("Некорректно введены римские цифры\n" +
                                "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
                    }
                } catch (IllegalArgumentException e) {
                    throw new ScannerException("Некорректно введены римские цифры\n" +
                            "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
                } catch (ScannerException e) {
                    throw new RuntimeException(e);
                }
            }
            if (value > 10) {
                throw new ScannerException("Вводимые значения не могут быть больше 10\n" +
                        "Пример: I, II, III, IV, V, VI, VII, VIII, IX, X (не больше 10)");
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
