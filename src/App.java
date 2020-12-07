import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import projeto.*;
import publicacoes.*;
import colaboradores.*;
// import publicacoes.*;

public class App {

    static void clearconsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    static Colaboradores getColaborador(String nomeColaborador, Vector<Colaboradores> listaColaboradores){
        Colaboradores colaboradorProcurado = null;
        for(int i = 0; i < listaColaboradores.size(); i++){
            if(nomeColaborador.equals(listaColaboradores.get(i).getNome())){
                colaboradorProcurado = listaColaboradores.get(i);
            }
        }
        return colaboradorProcurado;
    }
    static Projeto getProjeto(String nomeProjeto, Vector<Projeto> listaProjetos){
        Projeto projetoProcurado = null;
        for(int i = 0; i < listaProjetos.size(); i++){
            if(nomeProjeto.equals(listaProjetos.get(i).getTitulo())){
                projetoProcurado = listaProjetos.get(i);
            }
        }
        return projetoProcurado;
    }

    static void consultaColaborador(Scanner reader, String nomeColaborador, Vector<Colaboradores> listaColaboradores){
        Colaboradores colaborador = getColaborador(nomeColaborador, listaColaboradores);
        colaborador.consulta(reader);
    }

    static void associarColaboradorProjeto(Scanner reader, String nomeColaborador, String nomeProjeto, Vector<Colaboradores> listaColaboradores, Vector<Projeto> listaProjetos){
        Colaboradores colaboradorAssociado = getColaborador(nomeColaborador, listaColaboradores);
        Projeto projetoAssociado = getProjeto(nomeProjeto, listaProjetos);

        if(!colaboradorAssociado.equals(null) && !projetoAssociado.equals(null)){

            if(colaboradorAssociado.getFuncao().equals("G") && colaboradorAssociado.getProjetosAtivos() > 0){
                System.out.println("Um aluno de graduação só pode estar associado a um projeto por vez.");
            } else {
                colaboradorAssociado.associarProjetoColaborador(projetoAssociado);
                projetoAssociado.adicionarColaborador(reader, colaboradorAssociado);
                System.out.println("Autor associado!");
            }

        } else {
            System.out.println("Este colaborador não foi encontrado.");
        }
    }

    static void concluirProjeto(String nomeProjeto, Vector<Projeto> listaProjetos){
        Projeto projetoMencionado = getProjeto(nomeProjeto, listaProjetos);
        
        if(projetoMencionado.equals(null)) {
            System.out.println("Projeto não encontrado.");
            
        } else {
            if(projetoMencionado.getStatus().equals("Em andamento")){
                if(projetoMencionado.publicacaoExistente()){
                    projetoMencionado.concluir();
                    System.out.println("Projeto concluído.");

                } else {
                    System.out.println("O projeto precisa ter ao menos uma publicação para ser concluído.");
                }
                
            } else {
                System.out.println("O projeto não está em andamento para ser concluído.");
            }
        }

    }

    static void adicionarColaborador(Vector<Colaboradores> listaColaboradores, Scanner reader){
        clearconsole();
        System.out.print("Digite o nome do Colaborador: ");
        String nome = reader.nextLine();

        clearconsole();
        System.out.print("Digite o email do Colaborador: ");
        String email = reader.nextLine();

        clearconsole();
        System.out.println("Escolha uma das opções abaixo para definir a função do Colaborador:");
        System.out.println("(P) para professor...");
        System.out.println("(G) para graduando...");
        System.out.println("(O) para outros (pesquisador, mestrando, doutorando)...");
        String funcao = reader.nextLine();
        funcao = funcao.toUpperCase();

        Colaboradores novoColaborador = new Colaboradores(nome, email, funcao);

        listaColaboradores.add(novoColaborador);
        // sugestão: ordenar por função aqui
    }
    
    static int adicionarProjeto(Vector<Projeto> projetos, Scanner teclado) {
        int notfinished = 0;

        clearconsole();
        System.out.print("Digite o titulo do projeto: ");
        String tituloEntrada = teclado.nextLine();

        clearconsole();
        System.out.print("Digite o objetivo do projeto: ");
        String objetivoEntrada = teclado.nextLine();

        clearconsole();
        System.out.print("Digite o descricao do projeto: ");
        String descricaoEntrada = teclado.nextLine();

        clearconsole();
        System.out.print("Digite o agencia do projeto: ");
        String agenciaEntrada = teclado.nextLine();

        clearconsole();
        System.out.print("Digite o ano de inicio do projeto: ");
        int dataInicioEntrada = Integer.parseInt(teclado.nextLine());

        clearconsole();
        System.out.print("Digite o ano de termino do projeto: ");
        int dataTerminoEntrada = Integer.parseInt(teclado.nextLine());

        clearconsole();
        System.out.print("Digite o valor financiado do projeto: ");
        float valorFinanciadoEntrada = Float.parseFloat(teclado.nextLine());


        Projeto novoProjeto = new Projeto(tituloEntrada, objetivoEntrada, descricaoEntrada, dataInicioEntrada,dataTerminoEntrada, agenciaEntrada, valorFinanciadoEntrada);
        projetos.add(novoProjeto);

        // System.out.println(projetos.get(0).getStatus());

        if (novoProjeto.getDadosInformados() != 7) {
            // System.out.println("");
            notfinished = 1;
        }

        return notfinished;
    }

    static void adicionarPublicacao(Vector<Publicacoes> listaPublicacoes, Vector<Projeto> listaProjetos, Vector<Colaboradores> listaColaboradores, Scanner reader){
        clearconsole();
        System.out.print("Digite o nome da publicação: ");
        String nomePublicacao = reader.nextLine();


        clearconsole();
        System.out.print("Digite o nome da conferência onde foi publicada: ");
        String nomeConferencia = reader.nextLine();

        clearconsole();
        System.out.print("Digite o ano de publicação: ");
        Integer anoPublicacao = Integer.parseInt(reader.nextLine());

        Publicacoes novaPublicacao = new Publicacoes(nomePublicacao, nomeConferencia, anoPublicacao);
        listaPublicacoes.add(novaPublicacao);
        Collections.sort(listaPublicacoes);

        clearconsole();
        System.out.print("Digite o projeto associado, caso não exista digite '0': ");
        String nomeProjeto = reader.nextLine();

        Projeto projetoMencionado = getProjeto(nomeProjeto, listaProjetos);

        if(projetoMencionado.equals(null)){
            System.out.println("Este projeto de pesquisa não existe neste laboratório. Nessa publicação será considerada sem projeto de pesquisa associado.");
        }
        novaPublicacao.associarProjetoPublicacao(projetoMencionado);
        // sugestão: passar a classe e não o nome


        clearconsole();
        Integer noAutores = 0;
        System.out.println("Digite o nome dos autores da publicação (Digite '0' para encerrar a adição de colaboradores)");
        System.out.print("Digite o nome de um autor: ");
        String nomeAutor = reader.nextLine();

        while(!nomeAutor.equals("0")){
            noAutores++;
            Colaboradores autor = getColaborador(nomeAutor, listaColaboradores);
            
            if(!autor.equals(null)){
                novaPublicacao.associarAutorPublicacao(autor);
                autor.associarPublicacaoColaborador(novaPublicacao);
            } else {
                System.out.println("Colaborador não encontrado");
                noAutores--;
            }

            if(noAutores == 0){
                System.out.println("A publicação deve ter pelo menos um autor.");
            }

            clearconsole();
            System.out.print("Digite o nome de outro autor (Digite '0' para encerrar a adição de colaboradores): ");
            nomeAutor = reader.nextLine();
        }

    }
    static void relatorioLaboratorio(Vector<Colaboradores> listaColaboradores, Vector<Projeto> listaProjetos, Vector<Publicacoes> listaPublicacoes, Scanner reader) {
        System.out.println("Relatório de produção do laboratório:");
        System.out.println("Número de colaboradores:" + listaColaboradores.size());

        Integer elaboracao = 0;
        Integer andamento = 0;
        Integer concluido = 0;

        for(int i = 0; i < listaProjetos.size(); i++){
            if(listaProjetos.get(i).getStatus().equals("Em elaboração")){
                elaboracao++;
            }
            else if(listaProjetos.get(i).getStatus().equals("Em andamento")){
                andamento++;
            }
            else if(listaProjetos.get(i).getStatus().equals("Concluído")){
                concluido++;
            }
        }
        System.out.println("Número de projetos 'Em elaboração': " + elaboracao);
        System.out.println("Número de projetos 'Em andamento': " + andamento);
        System.out.println("Número de projetos concluídos: " + concluido);
        System.out.println("Número de projetos:" + listaColaboradores.size());
        System.out.println("Número de publicações:" + listaPublicacoes.size());

        Integer orientacoes = 0;

        for(int j = 0; j < listaColaboradores.size(); j++){
            if(listaColaboradores.get(j).getFuncao().equals("P")){
                orientacoes += listaColaboradores.get(j).getNumeroProjetos();
            }
        }
        System.out.println("Número de orientações:" + orientacoes);

        System.out.println("");
        System.out.println("Digite enter para continuar...");
        reader.nextLine();

    }

    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in);

        boolean using = true;
        int finished = 0;
        int opt;

        Vector<Projeto> listaProjetos = new Vector<Projeto>();
        Vector<Colaboradores> listaColaboradores = new Vector<Colaboradores>();
        Vector<Publicacoes> listaPublicacoes = new Vector<Publicacoes>();

        while (using) {

            clearconsole();
            System.out.println("Escolha uma das opções abaixo:");

            if (finished > 0) {
                System.out.println("Digite (0) para completar os dados de um projeto");
            }
            System.out.println("Digite (1) para adicionar um projeto");
            System.out.println("Digite (2) para adicionar um colaborador");
            System.out.println("Digite (3) para adicionar uma publicação"); 
            System.out.println("Digite (4) para concluir um projeto");
            System.out.println("Digite (5) para alocar os colaboradores em um projeto");
            System.out.println("Digite (6) para obter a consulta de um colaborador");
            System.out.println("Digite (7) para obter a consulta do laboratório");
            // sugestão System.out.println("Digite (y) para acessar um projeto");
            System.out.println("Digite (8) para sair");
            System.out.print("Digite a opção aqui: ");

            opt = Integer.parseInt(reader.nextLine());
            System.out.println(opt);

            switch (opt) {
                case 0:
                    System.out.print("Digite o nome do projeto: ");
                    String nomeProjeto = reader.nextLine();

                    Projeto aux = getProjeto(nomeProjeto, listaProjetos);

                    if(!aux.equals(null)){
                        aux.completandoDados(reader);

                        if(aux.getStatus().equals("Em andamento")){
                            finished--;
                        }
                    } else {
                        System.out.println("Este projeto não foi encontrado.");
                        System.out.print("Digite enter para continuar...");
                        reader.nextLine();
                    }
                    

                    break;

                case 1:
                    finished += adicionarProjeto(listaProjetos, reader);
                    break;
                
                case 2:
                    
                    // sugestão: mudar outros para pesquisador, mestrando, etc......
                    
                    adicionarColaborador(listaColaboradores, reader);
                    break;
                case 3:
                    clearconsole();
                    adicionarPublicacao(listaPublicacoes, listaProjetos, listaColaboradores, reader);
                    break;
                case 4:
                    clearconsole();
                    System.out.print("Digite o nome do projeto.");
                    String projetoConcluido = reader.nextLine();
                    concluirProjeto(projetoConcluido, listaProjetos);
                    break;
                case 5:
                    clearconsole();
                    System.out.print("Digite o nome do colaborador: ");
                    String autorAssociado = reader.nextLine();

                    clearconsole();
                    System.out.print("Digite o nome do projeto: ");
                    String projetoAssociado = reader.nextLine();

                    associarColaboradorProjeto(reader, autorAssociado, projetoAssociado, listaColaboradores, listaProjetos);
                    break;
                case 6:
                    clearconsole();
                    System.out.print("Digite o nome do colaborador: ");
                    String colaboradorConsultado = reader.nextLine();

                    consultaColaborador(reader, colaboradorConsultado, listaColaboradores);
                    break;
                case 7:
                    clearconsole();
                    relatorioLaboratorio(listaColaboradores, listaProjetos, listaPublicacoes, reader);
                    break;
                case 8:
                    clearconsole();
                    using = false;
                    break;
                default: 
                    
            }
        }

        reader.close();
    }

    
}
