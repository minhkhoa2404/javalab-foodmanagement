import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class ValidatePackage {
    private static Scanner sc = new Scanner(System.in);

    public static LocalDate inputDate(String msg, String errorMessage1, String errorMessage2) {
        boolean validCheck = false;
        LocalDate dateInput = LocalDate.now();
        LocalDate dateNow = LocalDate.now();
        LocalDate restrictDate = LocalDate.of(2050, 01, 01);
        while (validCheck == false) {
            try {
                System.out.print(msg);
                // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
                dateInput = LocalDate.parse(sc.nextLine().trim(),
                        DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
                validCheck = true;
                if (dateInput.isBefore(dateNow))
                    throw new Exception();
                if (dateInput.isAfter(restrictDate)) {
                    System.out.println("Too long to be stored in your fridge!");
                    validCheck = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println(errorMessage1);
                validCheck = false;
            } catch (Exception e1) {
                System.out.println(errorMessage2);
                validCheck = false;
            }
        }
        return dateInput;
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine().trim());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();
                return n;
            } catch (NumberFormatException nfe) {
                System.out.println("Your choice is invalid!");
            } catch (Exception e) {
                System.out.println("Your choice must be in [" + lowerBound + "," + upperBound + "]");
            }
        }
    }

    public static double getDouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine().trim());
                if (n < lowerBound || n > upperBound)
                    throw new Exception();
                return n;
            } catch (NumberFormatException nfe) {
                System.out.println("Your choice is invalid!");
            } catch (Exception e) {
                System.out.println("Your choice must be in [" + lowerBound + "," + upperBound + "]");
            }
        }
    }

    public static String inputString(String msg, String errorMsg) {
        while (true) {
            try {
                System.out.print(msg);
                String data = sc.nextLine().trim();
                if (data.length() == 0) {
                    throw new Exception();
                }
                return data;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String inputPattern(String msg, String pattern, String errorMessage) {
        String data;
        while (true) {
            try {
                System.out.print(msg);
                data = sc.nextLine().trim().toLowerCase();
                if (!data.matches(pattern)) {
                    throw new Exception();
                }
                return data;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static String inputID(String msg, String pattern, String errorMessage) {
        String data;
        while (true) {
            try {
                System.out.print(msg);
                data = sc.nextLine().trim().toUpperCase();
                if (!data.matches(pattern)) {
                    throw new Exception();
                }
                return data;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static String inputFoodName(String msg, String errorMsg) throws IOException {
        while (true) {
            try {
                System.out.print(msg);
                String data = sc.nextLine().trim();
                if (data.length() == 0) {
                    throw new Exception();
                }
                if (!data.contains(" ")) {
                    if (!Fridge.checkFoodExistence(data).toLowerCase().contains(data.toLowerCase())) {
                        throw new Exception();
                    }
                } else {
                    boolean flag = false;
                    String[] tempList = data.split(" ");
                    for (String item : tempList) {
                        if (Fridge.checkFoodExistence(item).toLowerCase().contains(item.toLowerCase())) {
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        throw new Exception();
                    }
                }
                return data;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputFoodName("Please input a food name:", "Food name is not valid or you have null value!");
        // getAnInteger("Enter an integer [0,50]:", "Please enter valid integer", 0,
        // 50);
        // getDouble("Enter a double [0,50]:", "Please enter valid double", 0, 50);
        // inputString("Enter a string:", "String cannot be null");

        // String a = inputID("acvd:", "^[F|f]\\d{3}$", "invalid!");
        // System.out.println(a);
    }
}
