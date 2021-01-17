package colaboradores;

import java.util.Scanner;

public class Aluno extends Colaboradores {

    public Aluno(String nome, String email, String funcao) {
        super(nome, email, funcao);
    }
    
    public void consulta(Scanner reader){
        limparConsole();
        System.out.println("####################################");
        System.out.println("");
        System.out.println("   Consulta por colaborador:");
        System.out.println("   Nome: " + getNome());
        System.out.println("   Email: " + this.email);
        System.out.println("");
        System.out.println("####################################");
        System.out.println("");
        
        // lista de projetos
        if(listaProjetos.size() > 0){

            System.out.println("Projetos como colaborador:");
            
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
