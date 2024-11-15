package gestaopauta;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FicheiroDeObjectos {

    //Metodo para escreve rno ficheiro de texto
    /*public void escreveTexto(List<Estudante> estudantes, String nomeDoFicheiro) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoFicheiro))) {
            for (Estudante estudante : estudantes) {
                writer.write(estudante.getCodigo() + "," + estudante.getNome() + "," + estudante.getIdade() + "," + estudante.getNota1() + "," + estudante.getNota2());
                writer.newLine();
            }
        }
    }

    // Lê dados do ficheiro 2 para uma fila
    public List<Estudante> leTexto(String nomeDoFicheiro) throws IOException {
        List<Estudante> estudantes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                double nota1 = Double.parseDouble(dados[3]);
                double nota2 = Double.parseDouble(dados[4]);
                Estudante estudante = new Estudante(codigo, nome, idade);
                estudante.setNota1(nota1);
                estudante.setNota2(nota2);
                estudantes.add(estudante);
            }
        }
        return estudantes;
    }*/

    // Metodo para escrever dados do ficheiro 1 que e lista para ficheiro 2 que e Fila
    public void escreveTextoFicheiro2(List<Estudante> estudantes, String nomeDoFicheiro) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoFicheiro))) {
            for (Estudante estudante : estudantes) {
                writer.write(estudante.getCodigo() + "," + estudante.getNome() + "," + estudante.getIdade() + "," + estudante.getNota1() + "," + estudante.getNota2());
                writer.newLine();
            }
        }
    }

    // Metodo para ler dados da fila
    public Queue<Estudante> leTextoParaFila(String nomeDoFicheiro) throws IOException {
        Queue<Estudante> estudantes = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoFicheiro))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int codigo = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int idade = Integer.parseInt(dados[2]);
                double nota1 = Double.parseDouble(dados[3]);
                double nota2 = Double.parseDouble(dados[4]);
                Estudante estudante = new Estudante(codigo, nome, idade);
                estudante.setNota1(nota1);
                estudante.setNota2(nota2);
                estudantes.add(estudante); // FIFO behavior
            }
        }
        return estudantes;
    }
}
