package gestaopauta;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LerFicheiroTexto {

    // Método para escrever dados em um ficheiro de texto
	public void escreveTexto(List<Estudante> estudantes, String nomeDoFicheiro) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoFicheiro))) {
	        for (Estudante estudante : estudantes) {
	            writer.write(estudante.getCodigo() + "," + estudante.getNome() + "," + estudante.getIdade() + "," +
	                         estudante.getNota1() + "," + estudante.getNota2() + "," + estudante.getMedia());
	            writer.newLine();
	        }
	    }
	}

    // Método para ler dados do ficheiro de texto e retornar uma lista de estudantes
	public List<Estudante> leTexto(String nomeDoFicheiro) throws IOException {
	    List<Estudante> estudantes = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoFicheiro))) {
	        String linha;
	        int linhaAtual = 1; // Para rastrear a linha processada
	        while ((linha = reader.readLine()) != null) {
	            String[] dados = linha.split(",");
	            if (dados.length < 5) {
	                System.out.println("Linha " + linhaAtual + " mal formatada e foi ignorada: " + linha);
	                linhaAtual++;
	                continue;
	            }
	            try {
	                int codigo = Integer.parseInt(dados[0]);
	                String nome = dados[1];
	                int idade = Integer.parseInt(dados[2]);
	                double nota1 = Double.parseDouble(dados[3]);
	                double nota2 = Double.parseDouble(dados[4]);
	                double media = (nota1 + nota2) / 2; // Calcula a média automaticamente

	                Estudante estudante = new Estudante(codigo, nome, idade);
	                estudante.setNota1(nota1);
	                estudante.setNota2(nota2);
	                estudante.setMedia(media);

	                estudantes.add(estudante);
	            } catch (NumberFormatException e) {
	                System.out.println("Erro ao processar dados na linha " + linhaAtual + ": " + linha);
	            }
	            linhaAtual++;
	        }
	    }
	    return estudantes;
	}

    // Método para ler dados do ficheiro de texto e retornar uma fila (FIFO)
	public Queue<Estudante> leTextoParaFila(String nomeDoFicheiro) throws IOException {
	    Queue<Estudante> fila = new LinkedList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(nomeDoFicheiro))) {
	        String linha;
	        int linhaAtual = 1;
	        while ((linha = reader.readLine()) != null) {
	            String[] dados = linha.split(",");
	            if (dados.length < 5) {
	                System.out.println("Linha " + linhaAtual + " mal formatada e foi ignorada: " + linha);
	                linhaAtual++;
	                continue;
	            }
	            try {
	                int codigo = Integer.parseInt(dados[0]);
	                String nome = dados[1];
	                int idade = Integer.parseInt(dados[2]);
	                double nota1 = Double.parseDouble(dados[3]);
	                double nota2 = Double.parseDouble(dados[4]);
	                double media = (nota1 + nota2) / 2; 

	                Estudante estudante = new Estudante(codigo, nome, idade);
	                estudante.setNota1(nota1);
	                estudante.setNota2(nota2);
	                estudante.setMedia(media);

	                fila.add(estudante);
	            } catch (NumberFormatException e) {
	                System.out.println("Erro ao processar dados na linha " + linhaAtual + ": " + linha);
	            }
	            linhaAtual++;
	        }
	    }
	    return fila;
	}
}
