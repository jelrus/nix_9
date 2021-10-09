package ua.com.alevel;

public class Main {
    public static void main(String[] args) {
        int result = 0;

        for (int i = 0; i < args[0].length(); i++) {
            String substring = args[0].substring(i, i + 1);
            if (substring.matches("[0-9]")) {
                result += Integer.parseInt(substring);
            }
        }
        System.out.println("Входные данные: " + args[0]);
        System.out.println("Выходные данные: " + result);
    }
}