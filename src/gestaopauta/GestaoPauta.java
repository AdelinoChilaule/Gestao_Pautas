package gestaopauta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.io.IOException;

public class GestaoPauta {
    ListaConcreta turma = new ListaConcreta(); // ED1 - Lista usando interface Lista
    FilaConcreta filaEstudantes = new FilaConcreta(); // ED2 - Fila usando interface Fila
    Scanner teclado = new Scanner(System.in);
    FicheiroDeObjectos fo = new FicheiroDeObjectos();
    LerFicheiroTexto lft = new LerFicheiroTexto();

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
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
            System.out.println("Código: " + estudante.getCodigo() + " | Nome: " + estudante.getNome() + " | Idade: " + estudante.getIdade());
        }
        System.out.println("-----------------------------------------");
    }

    public void lancarTeste1() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado. Por favor, adicione estudantes primeiro.");
            return;
        }
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
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
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
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
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
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
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
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
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
            if (estudante.getNota1() < 10) {
                System.out.println("O Estudante: " + estudante.getNome() + " teve: " + estudante.getNota1() + " no Teste 1");
            }
        }
        System.out.println("-----------------------------------------");
    }

    public void guardarDados() throws IOException {
        List<Estudante> listaEstudantes = turma.getAll(); // Agora retorna List<Estudante>
        if (listaEstudantes.isEmpty()) {
            System.out.println("Nenhum estudante para guardar.");
            return;
        }
        lft.escreveTexto(listaEstudantes, "turma.txt");
        System.out.println("Dados guardados com sucesso em turma.txt!");
    }

    
    public void carregarDados() throws IOException {
        List<Estudante> lista = lft.leTexto("turma.txt");
        for (Estudante estudante : lista) {
            turma.add(estudante);
        }
        System.out.println("Dados carregados com sucesso de turma.txt!");
    }

    public void transferirED1ParaFicheiro2() throws IOException {
        if (turma.isEmpty()) {
            System.out.println("Nenhum dado em ED1 para transferir.");
            return;
        }
        fo.escreveTextoFicheiro2(turma.getAll(), "ficheiro2.txt");
        System.out.println("Dados de ED1 transferidos para ficheiro2.txt!");
    }

    public void carregarFicheiro2ParaED2() throws IOException {
        List<Estudante> lista = lft.leTexto("ficheiro2.txt");
        for (Estudante estudante : lista) {
            filaEstudantes.enqueue(estudante);
        }
        System.out.println("Dados carregados de ficheiro2.txt para ED2 (Fila FIFO)!");
    }

    public void imprimirED2() {
        if (filaEstudantes.isEmpty()) {
            System.out.println("ED2 está vazia.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|         Conteúdo da ED2 (Fila FIFO)    |");
        System.out.println("*========================================*");
        try {
            while (!filaEstudantes.isEmpty()) {
                Estudante estudante = (Estudante) filaEstudantes.dequeue();
                System.out.println("Código: " + estudante.getCodigo() + ", Nome: " + estudante.getNome() + ", Idade: " + estudante.getIdade() + ", Nota1: " + estudante.getNota1() + ", Nota2: " + estudante.getNota2());
            }
        } catch (FilaException e) {
            System.out.println("Erro ao processar a fila: " + e.getMessage());
        }
    }

    public void calcularMedia() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado.");
            return;
        }
        System.out.println("*========================================*");
        System.out.println("|        Média dos Estudantes            |");
        System.out.println("*========================================*");
        for (Object obj : turma.getAll()) {
            Estudante estudante = (Estudante) obj;
            double media = (estudante.getNota1() + estudante.getNota2()) / 2;
            estudante.setMedia(media);
            System.out.println("Código: " + estudante.getCodigo() + ", Nome: " + estudante.getNome() + ", Média: " + media);
        }
    }
}
