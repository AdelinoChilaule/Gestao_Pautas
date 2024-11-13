package gestaopauta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.io.IOException;
import java.util.Queue;

public class GestaoPauta {
    List<Estudante> turma = new ArrayList<>(); // ED1 - Lista
    Queue<Estudante> filaEstudantes = new LinkedList<>(); // ED2 - Fila (FIFO)
    Scanner teclado = new Scanner(System.in);
    FicheiroDeObjectos fo = new FicheiroDeObjectos();
    LerFicheiroTexto lft= new LerFicheiroTexto();

    public void lerDados() {
        System.out.println("Digite o seu código: ");
        int cod = teclado.nextInt();
        teclado.nextLine(); // Consume newline
        System.out.println("Digite o seu Nome: ");
        String nome = teclado.nextLine();
        System.out.println("Digite a sua idade: ");
        int idade = teclado.nextInt();

        Estudante estudante = new Estudante(cod, nome, idade);
        turma.add(estudante);
        System.out.println("Estudante adicionado com sucesso!");
        System.out.println("-----------------------------------------");
    }

    public void imprimirLista() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|            Lista de Estudantes         |");
        System.out.println("*========================================*");
        for (Estudante estudante : turma) {
            System.out.println("Código: " + estudante.getCodigo() + " | Nome: " + estudante.getNome() + " | Idade: " + estudante.getIdade());
        }
        System.out.println("-----------------------------------------");
    }

    public void lancarTeste1() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        for (Estudante estudante : turma) {
            System.out.println("Digite a nota 1 do estudante " + estudante.getNome() + ": ");
            double nota1 = teclado.nextDouble();
            estudante.setNota1(nota1);
        }
        System.out.println("Notas do Teste 1 lançadas com sucesso!");
    }

    public void lancarTeste2() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        for (Estudante estudante : turma) {
            System.out.println("Digite a nota 2 do estudante " + estudante.getNome() + ": ");
            double nota2 = teclado.nextDouble();
            estudante.setNota2(nota2);
        }
        System.out.println("Notas do Teste 2 lançadas com sucesso!");
    }

    public void imprimirPautaTeste1() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|            PAUTA TESTE 1               |");
        System.out.println("*========================================*");
        for (Estudante estudante : turma) {
            System.out.println("Código: " + estudante.getCodigo() + ", Nome: " + estudante.getNome() + ", Nota1: " + estudante.getNota1());
        }
        System.out.println("-----------------------------------------");
    }

    public void imprimirPautaTeste2() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|            PAUTA TESTE 2               |");
        System.out.println("*========================================*");
        for (Estudante estudante : turma) {
            System.out.println("Código: " + estudante.getCodigo() + ", Nome: " + estudante.getNome() + ", Nota1: " + estudante.getNota1() + ", Nota2: " + estudante.getNota2());
        }
        System.out.println("-----------------------------------------");
    }

    public void imprimirNegativas() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|              Negativas                 |");
        System.out.println("*========================================*");
        for (Estudante estudante : turma) {
            if (estudante.getNota1() < 10) {
                System.out.println("O Estudante: " + estudante.getNome() + " teve: " + estudante.getNota1() + " no Teste 1");
            }
        }
        System.out.println("-----------------------------------------");
    }

    public void guardarDados() throws IOException {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante para guardar.");
            return;
        }
        lft.escreveTexto(turma, "turma.txt");
        System.out.println("Dados guardados com sucesso em turma.txt!");
    }

    public void carregarDados() throws IOException {
        turma = lft.leTexto("turma.txt");
        if (!turma.isEmpty()) {
            System.out.println("Dados carregados com sucesso de turma.txt!");
        } else {
            System.out.println("Nenhum dado foi carregado.");
        }
    }

    public void transferirED1ParaFicheiro2() throws IOException {
        if (turma.isEmpty()) {
            System.out.println("Nenhum dado em ED1 para transferir.");
            return;
        }
        fo.escreveTextoFicheiro2(turma, "ficheiro2.txt");
        System.out.println("Dados de ED1 transferidos para ficheiro2.txt!");
    }

    public void carregarFicheiro2ParaED2() throws IOException {
        filaEstudantes = fo.leTextoParaFila("ficheiro2.txt");
        if (!filaEstudantes.isEmpty()) {
            System.out.println("Dados carregados de ficheiro2.txt para ED2 (Fila FIFO)!");
        } else {
            System.out.println("Nenhum dado foi carregado.");
        }
    }

    public void imprimirED2() {
        if (filaEstudantes.isEmpty()) {
            System.out.println("ED2 está vazia.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|         Conteúdo da ED2 (Fila FIFO)    |");
        System.out.println("*========================================*");
        for (Estudante estudante : filaEstudantes) {
            System.out.println("Código: " + estudante.getCodigo() + ", Nome: " + estudante.getNome() + ", Idade: " + estudante.getIdade() + ", Nota1: " + estudante.getNota1() + ", Nota2: " + estudante.getNota2());
        }
        System.out.println("-----------------------------------------");
    }

    public void lerED1ParaBD() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO estudantes (codigo, nome, idade, nota1, nota2) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Estudante estudante : turma) {
                stmt.setInt(1, estudante.getCodigo());
                stmt.setString(2, estudante.getNome());
                stmt.setInt(3, estudante.getIdade());
                stmt.setDouble(4, estudante.getNota1());
                stmt.setDouble(5, estudante.getNota2());
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Dados de ED1 foram gravados na BD com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao gravar ED1 na BD: " + e.getMessage());
        }
    }

    public void lerBDParaED1() {
        turma.clear();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM estudantes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                double nota1 = rs.getDouble("nota1");
                double nota2 = rs.getDouble("nota2");

                Estudante estudante = new Estudante(codigo, nome, idade);
                estudante.setNota1(nota1);
                estudante.setNota2(nota2);
                turma.add(estudante);
            }

            System.out.println("Dados carregados da BD para ED1 com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados da BD para ED1: " + e.getMessage());
        }
    }

    public void lerED2ParaBD() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO estudantes (codigo, nome, idade, nota1, nota2) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (Estudante estudante : filaEstudantes) {
                stmt.setInt(1, estudante.getCodigo());
                stmt.setString(2, estudante.getNome());
                stmt.setInt(3, estudante.getIdade());
                stmt.setDouble(4, estudante.getNota1());
                stmt.setDouble(5, estudante.getNota2());
                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Dados de ED2 foram gravados na BD com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao gravar ED2 na BD: " + e.getMessage());
        }
    }

    public void lerBDParaED2() {
        filaEstudantes.clear();
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM estudantes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                double nota1 = rs.getDouble("nota1");
                double nota2 = rs.getDouble("nota2");

                Estudante estudante = new Estudante(codigo, nome, idade);
                estudante.setNota1(nota1);
                estudante.setNota2(nota2);
                filaEstudantes.add(estudante);
            }

            System.out.println("Dados carregados da BD para ED2 com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados da BD para ED2: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        GestaoPauta gerenciador = new GestaoPauta();
        int op;
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
            System.out.println("[13] Ler ED1 para a BD;");
            System.out.println("[14] Ler BD para ED1;");
            System.out.println("[15] Ler ED2 para a BD;");
            System.out.println("[16] Ler BD para ED2;");
            System.out.println("[0] Sair do Programa.");
            op = gerenciador.teclado.nextInt();
            switch (op) {
                case 1:
                    gerenciador.lerDados();
                    break;
                case 2:
                    gerenciador.imprimirLista();
                    break;
                case 3:
                    gerenciador.lancarTeste1();
                    break;
                case 4:
                    gerenciador.lancarTeste2();
                    break;
                case 5:
                    gerenciador.imprimirPautaTeste1();
                    break;
                case 6:
                    gerenciador.imprimirPautaTeste2();
                    break;
                case 7:
                    gerenciador.imprimirNegativas();
                    break;
                case 8:
                    gerenciador.guardarDados();
                    break;
                case 9:
                    gerenciador.carregarDados();
                    break;
                case 10:
                    gerenciador.transferirED1ParaFicheiro2();
                    break;
                case 11:
                    gerenciador.carregarFicheiro2ParaED2();
                    break;
                case 12:
                    gerenciador.imprimirED2();
                    break;
                case 13:
                    gerenciador.lerED1ParaBD();
                    break;
                case 14:
                    gerenciador.lerBDParaED1();
                    break;
                case 15:
                    gerenciador.lerED2ParaBD();
                    break;
                case 16:
                    gerenciador.lerBDParaED2();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (op != 0);
    }
}
