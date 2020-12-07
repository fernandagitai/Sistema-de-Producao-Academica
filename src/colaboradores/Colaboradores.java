package colaboradores;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import publicacoes.*;
import projeto.*;


public class Colaboradores {
    // alunos de graduação, alunos de mestrado, alunos de doutorado, professores e pesquisadores. 
    // nome, e-mail, um histórico contendo a lista de projetos nos quais este colaborador participou

    String nome, email, funcao;
    Integer projetosAtivos;

    Vector<Projeto> listaProjetos = new Vector<Projeto>();
    Vector<Publicacoes> listaPublicacoes = new Vector<Publicacoes>();

    public Colaboradores(String nome, String email, String funcao){
        this.nome = nome;
        this.email = email;
        this.funcao = funcao;
        this.projetosAtivos = 0;
    }
    private void clearconsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    public void encerrandoProjeto(){
        this.projetosAtivos--;
    }
    
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getFuncao(){
        return this.funcao;
    }
    
    public int getProjetosAtivos(){
        return this.projetosAtivos;
    }

    public void associarProjetoColaborador(Projeto projetoAdicionado){
        this.listaProjetos.add(projetoAdicionado);
        this.projetosAtivos++;
       
        Collections.sort(this.listaProjetos);
    }

    public void associarPublicacaoColaborador(Publicacoes publicacaoAdicionada){
        this.listaPublicacoes.add(publicacaoAdicionada);
        // o tratamento foi feito na chamada do método

        Collections.sort(this.listaPublicacoes);
    }

    public int getNumeroProjetos(){
        return listaProjetos.size();
    }

    public void consulta(Scanner reader){
        clearconsole();
        System.out.println("####################################");
        System.out.println("");
        System.out.println("   Consulta por colaborador:");
        System.out.println("   Nome: " + getNome());
        System.out.println("   Email: " + getEmail());
        System.out.println("");
        System.out.println("####################################");
        System.out.println("");
        
        // lista de projetos
        if(listaProjetos.size() > 0){
            if(funcao.equals("P")){
                System.out.println("Orientações:");
            } else {
                System.out.println("Projetos como colaborador:");
            }
            
            for(int i = 0; i < listaProjetos.size(); i++){
                System.out.println("    " + listaProjetos.get(i).getTitulo() + " - ANO DE TERMINO: " + listaProjetos.get(i).getTermino());
            }
        } else {
            System.out.println(this.nome + " não possui projetos associados.");
        }

        System.out.println("");

        
        //lista de publicações
        if(this.listaPublicacoes.size() > 0){
            System.out.println("Lista de publicações:");

            for(int j = 0; j < listaPublicacoes.size(); j++){
                System.out.println("    " + listaPublicacoes.get(j).getTituloPublicacao() + "\n      ANO DA PUBLICAÇÃO: " + listaPublicacoes.get(j).getAno());
            }
        } else {
            System.out.println(this.nome + " não possui publicações.");
        }

        System.out.print("Digite enter para continuar...");
        reader.nextLine();

    }
    
    

}
