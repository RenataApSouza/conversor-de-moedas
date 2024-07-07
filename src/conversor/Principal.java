package conversor;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem vindo(a) ao Conversor de Moedas!");
        System.out.println("***************************************");

        System.out.println("Informe o valor a ser convertido: ");
        double valor = scanner.nextDouble();

        int moedaOrigemIndex = selecionarMoeda(scanner, "Selecione a primeira opção de moeda para conversão: ");
        if (moedaOrigemIndex == 0) {
            System.out.println("Operação encerrada.");
            return;
        }

        int moedaDestinoIndex = selecionarMoeda(scanner, "Selecione a segunda opção de moeda para conversão: ");
        if (moedaDestinoIndex == 0) {
            System.out.println("Operação encerrada.");
            return;
        }

        String[] moedas = {"", "BRL", "USD", "EUR", "CNY", "AUD", "CAD", "INR", "JPY", "GBP", "CHF"};
        String moedaOrigem = moedas[moedaOrigemIndex];
        String moedaDestino = moedas[moedaDestinoIndex];

        try {
            double valorConvertido = API.converterMoeda(valor, moedaOrigem, moedaDestino);
            System.out.printf("Valor convertido: %.2f %s\n", valorConvertido, moedaDestino);
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao fazer a conversão: " + e.getMessage());
        }

        scanner.close();
    }

    private static int selecionarMoeda(Scanner scanner, String mensagem) {
        System.out.println(mensagem);
        System.out.println("""
                1 - BRL (Real Brasileiro); 
                2 - USD (Dólar Americano); 
                3 - EUR (Euro); 
                4 - CNY (Yuan China);  
                5 - AUD (Dólar Australiano);  
                6 - CAD (Dólar Canadense);  
                7 - INR (Rúpia Indiana); 
                8 - JPY (Yen Japonês);  
                9 - GBP (Libra Esterlina do Reino Unido);  
                10 - CHF (Franco Suiço);  
                0 - Sair. 
                """);

        int opcao = scanner.nextInt();
        if (opcao < 0 || opcao > 10) {
            System.out.println("Opção inválida. Tente novamente.");
            return selecionarMoeda(scanner, men
        }
        return opcao;
    }

}
