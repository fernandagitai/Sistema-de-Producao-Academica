package projeto;

import java.util.Scanner;
import java.util.Vector;

import colaboradores.*;
import publicacoes.*;

public class Projeto implements Comparable<Projeto> {
    //colaboradores alocados e uma lista contendo toda a produção acadêmica do projeto
    Vector<Colaboradores> listaColaboradores = new Vector<Colaboradores>();
    Vector<Publicacoes> listaPublicacoes = new Vector<Publicacoes>();
    
    String statusProjeto = "Em elaboração";

    String titulo, objetivo, descricao;
    int dataInicio, dataTermino;
    String agencia;
    Float valorFinanciado;

    private void limparConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    public int getTermino(){
        return this.dataTermino;
    }


    private void setStatus(String status) {
        if(status.equals("Concluído") || status.equals("Em andamento")){
            this.statusProjeto = status;
        }
    }

    public String getStatus() {
        return this.statusProjeto;
    }
    
    public String getTitulo() {
        return this.titulo;
    }

    public int getPublicacoes() {
        return listaPublicacoes.size();
    }

    public void adicionarColaborador(Scanner reader, Colaboradores novoColaborador){
        listaColaboradores.add(novoColaborador);
        if(novoColaborador instanceof Professor){
            //adicionando professor
            if(statusProjeto.equals("Em elaboração")){
                limparConsole();
                System.out.println("Professor adicionado!");
                System.out.println("O projeto agora encontra-se 'Em andamento'.");
                this.statusProjeto = "Em andamento";
            } else {
                System.out.println("Você está tentando adicionar um professor à um projeto o qual já possui orientador.");
            }
        }
        System.out.print("Pessione enter para continuar...");
        reader.nextLine();
    }
    
    public Projeto(String titulo, String objetivo, String descricao, int dataInicio, int dataTermino, String agencia,Float valorFinanciado) {

        this.titulo = titulo;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.agencia = agencia;
        this.valorFinanciado = valorFinanciado;

    } //configurando o projeto
    
    public void concluir() {
        setStatus("Concluído");
	}

    @Override
    public int compareTo(Projeto o) { 

        if (this.dataTermino > o.getTermino()) { 
          return -1; 
        } 

        if (this.dataTermino < o.getTermino()) { 
          return 1; 
        } 

          return 0; 
    }
	

}
