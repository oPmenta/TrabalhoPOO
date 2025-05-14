package util;

import java.util.Scanner;

public class ConsoleUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static int lerInt(String prompt, int min, int max) {
        int opcao;
        while (true) {
            System.out.print(prompt);
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao >= min && opcao <= max) {
                    return opcao;
                } else {
                    System.out.printf("Erro: escolha entre %d e %d!\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: entrada invalida!");
            }
        }
    }

    public static String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
