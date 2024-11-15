package gestaopauta;

import java.io.IOException;
import java.util.Scanner;

public class Executavel {
    public static void main(String[] args) throws IOException {
        GestaoPauta gestaoPauta = new GestaoPauta();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===========================");
            System.out.println("=           MENU          =");
            System.out.println("===========================");
            System.out.println("[1] Cadastrar Estudantes;");
            System.out.println("[2] Imprimir a lista de Estudantes;");
            System.out.println("[3] Lançar Teste 1;");
            System.out.println("[4] Lançar Teste 2;");
            System.out.println("[5] Imprimir Resultados do Teste 1;");
            System.out.println("[6] Imprimir Resultados do Teste 2;");
            System.out.println("[7] Imprimir Negativas;");
            System.out.println("[8] Guardar Dados em ficheiro1;");
            System.out.println("[9] Carregar Dados de ficheiro1;");
            System.out.println("[10] Transferir ED1 para ficheiro2;");
            System.out.println("[11] Carregar ficheiro2 para ED2;");
            System.out.println("[12] Imprimir ED2;");
            System.out.println("[13] Calcular Média dos Estudantes;");
            System.out.println("[0] Sair.");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gestaoPauta.lerDados();
                    break;
                case 2:
                    gestaoPauta.imprimirLista();
                    break;
                case 3:
                    gestaoPauta.lancarTeste1();
                    break;
                case 4:
                    gestaoPauta.lancarTeste2();
                    break;
                case 5:
                    gestaoPauta.imprimirPautaTeste1();
                    break;
                case 6:
                    gestaoPauta.imprimirPautaTeste2();
                    break;
                case 7:
                    gestaoPauta.imprimirNegativas();
                    break;
                case 8:
                    gestaoPauta.guardarDados();
                    break;
                case 9:
                    gestaoPauta.carregarDados();
                    break;
                case 10:
                    gestaoPauta.transferirED1ParaFicheiro2();
                    break;
                case 11:
                    gestaoPauta.carregarFicheiro2ParaED2();
                    break;
                case 12:
                    gestaoPauta.imprimirED2();
                    break;
                case 13:
                    gestaoPauta.calcularMedia();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
