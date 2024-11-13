package gestaopauta;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


public class LerFicheiroTexto {
	
	public void escreveTexto(List<Estudante> estudantes, String nomeDoFicheiro) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDoFicheiro))) {
            for (Estudante estudante : estudantes) {
                writer.write(estudante.getCodigo() + "," + estudante.getNome() + "," + estudante.getIdade() + "," + estudante.getNota1() + "," + estudante.getNota2());
                writer.newLine();
            }
        }
    }
	
	// Lê dados do ficheiro de texto para uma lista
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
    }
}
