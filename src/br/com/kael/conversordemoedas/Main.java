package br.com.kael.conversordemoedas;

import br.com.kael.conversordemoedas.models.Conversion;
import br.com.kael.conversordemoedas.services.ConnectApi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnectApi conversionApi = new ConnectApi();
        String[] currencySymbols = new String[2];
        int options = 0;

        while (options != 7) {
            System.out.println("""
                    ************************************************
                            Bem vindo ao Conversor de Moedas
                            
                        Escolha uma opção de conversão
                        
                    1 - Real para Dólar estadunidense
                    2 - Dólar estadunidense para Real
                    3 - Real para Euro
                    4 - Euro para Real
                    5 - Dólar para Euro
                    6 - Euro para Dólar
                    7 - Sair
                    """);
            options = scanner.nextInt();

            if (options == 7) {
                System.out.println("Finalizando...");
                return; // Encerrar o programa se o número for inválido
            }

            switch (options) {
                case 1:
                    currencySymbols[0] = "BRL";
                    currencySymbols[1] = "USD";
                    break;
                case 2:
                    currencySymbols[0] = "USD";
                    currencySymbols[1] = "BRL";
                    break;
                case 3:
                    currencySymbols[0] = "BRL";
                    currencySymbols[1] = "EUR";
                    break;
                case 4:
                    currencySymbols[0] = "EUR";
                    currencySymbols[1] = "BRL";
                    break;
                case 5:
                    currencySymbols[0] = "USD";
                    currencySymbols[1] = "EUR";
                    break;
                case 6:
                    currencySymbols[0] = "EUR";
                    currencySymbols[1] = "USD";
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, insira um número de 0 a 7.");
                    break;
            }
            try {
                System.out.println("Digite o valor");
                var valor = scanner.nextDouble();
                Conversion conversion = conversionApi.getConversion(currencySymbols, valor);

                System.out.println("Resultado: %.2f %s é igual a %.2f %s".formatted(valor, conversion.base_code(), conversion.conversion_result(), conversion.target_code()));
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
