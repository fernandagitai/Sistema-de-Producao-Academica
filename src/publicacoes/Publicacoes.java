package publicacoes;

import java.util.Vector;

import projeto.*;
import colaboradores.*;

public class Publicacoes implements Comparable<Publicacoes> {
    // Uma publicação deve ter título, nome da conferência onde foi publicada, ano de publicação e projeto de pesquisa associado (se houver)
    String titulo, conferencia;
    int anoPublicacao;
    Vector<Projeto> projetoAssociado = new Vector<Projeto>();
    Vector<Colaboradores> autores = new Vector<Colaboradores>();

    public String getTituloPublicacao(){
        return this.titulo;
    }
    public Integer getAno(){
        return this.anoPublicacao;
    }
    public Publicacoes(String titulo, String conferencia, Integer anoPublicacao){
        this.titulo = titulo;
        this.conferencia = conferencia;
        this.anoPublicacao = anoPublicacao;
    }

    public void associarAutorPublicacao(Colaboradores autor){
        autores.add(autor);
    }

    public void associarProjetoPublicacao(Projeto projeto){
        projetoAssociado.add(projeto);
    }

    @Override
    public int compareTo(Publicacoes o) { 

        if (this.anoPublicacao > o.getAno()) { 
          return -1; 
        } 

        if (this.anoPublicacao < o.getAno()) { 
          return 1; 
        } 
        
          return 0; 
    }
}
