import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import projeto.*;
import publicacoes.*;
import colaboradores.*;

public class Laboratorio { // padrão Facade

    static void limparConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    String nomeAdm;
    static Scanner teclado;
    static Vector<Projeto> listaProjetos;
    static Vector<Colaboradores> listaColaboradores;
    static Vector<Publicacoes> listaPublicacoes;

    public Laboratorio(String nomeAdm, Scanner tecladoEntrada) {
        teclado = tecladoEntrada;
        this.nomeAdm = nomeAdm;
        listaProjetos = new Vector<Projeto>();
        listaColaboradores = new Vector<Colaboradores>();
        listaPublicacoes = new Vector<Publicacoes>();
    }

    static Colaboradores getColaborador() { // padrão fabric

        Colaboradores colaboradorProcurado = null;
        Boolean achado = false;
        String nomeColaborador;

        while(!achado){
            limparConsole();
            System.out.print("Digite o nome do colaborador: ");
            nomeColaborador = teclado.nextLine();

            for (int i = 0; i < listaColaboradores.size(); i++) {
                if (nomeColaborador.equals(listaColaboradores.get(i).getNome())) {
                    colaboradorProcurado = listaColaboradores.get(i);
                    achado = true;
                }
            }
            if(!achado){
                System.out.println("Colaborador não encontrado.");
                System.out.print("Desejas digitar outro nome? (S/N): ");

                String opcao = teclado.nextLine();
                opcao = opcao.toUpperCase();

                if(opcao.equals("N")){
                    achado = true;
                }
            }
        }
        
        return colaboradorProcurado;
    }

    static Projeto getProjeto() { // padrão fabric
        Projeto projetoProcurado = null;
        Boolean achado = false;
        String nomeProjeto;

        while(!achado){
            limparConsole();
            System.out.print("Digite o nome do projeto: ");
            nomeProjeto = teclado.nextLine();

            for (int i = 0; i < listaProjetos.size(); i++) {
                if (nomeProjeto.equals(listaProjetos.get(i).getTitulo())) {
                    projetoProcurado = listaProjetos.get(i);
                    achado = true;
                }
            }
            if(!achado){
                System.out.println("Projeto não encontrado.");
                System.out.print("Desejas digitar outro título? (S/N): ");

                String opcao = teclado.nextLine();
                opcao = opcao.toUpperCase();
                
                if(opcao.equals("N")){
                    achado = true;
                }
            }
        }

        return projetoProcurado;
    }

    void consultaColaborador() {

        limparConsole();
        Colaboradores colaborador = null;

        try {
            colaborador = getColaborador();
            colaborador.consulta(teclado);
        } catch (Exception e) {
            limparConsole();
            System.out.println("Não foi dado um colaborador.");
            System.out.print("Digite enter para continuar...");
            teclado.nextLine();
        }
    }

    void associarColaboradorProjeto() {

        Boolean achado = true;
        Colaboradores colaboradorAssociado = getColaborador();
        Projeto projetoAssociado = getProjeto();

        try { // para o colaborador
            if(!colaboradorAssociado.equals(null)){
                //condicional generica para testar se o colaborador é nulo
            }
        } catch (Exception e) {
            limparConsole();
            System.out.println("Não foi dado um colaborador.");
            achado = false;
        }

        try { // para o projeto
            if(!projetoAssociado.equals(null)){
                //condicional generica para testar se o projeto é nulo
            }
        } catch (Exception e) {
            limparConsole();
            System.out.println("Não foi dado um projeto.");
            achado = false;
        }

        if(achado){

            if ((colaboradorAssociado instanceof Aluno) && colaboradorAssociado.getNumeroProjetos() > 0) {
                limparConsole();
                System.out.println("Um aluno de graduação só pode estar associado a um projeto por vez.");
            } else {
                colaboradorAssociado.associarProjetoColaborador(projetoAssociado);
                projetoAssociado.adicionarColaborador(teclado, colaboradorAssociado);
                limparConsole();
                System.out.println("Autor associado!");
            }
        }

        System.out.print("Digite enter para continuar...");
        teclado.nextLine();
    }

    void concluirProjeto() {

        limparConsole();

        Projeto projetoMencionado = getProjeto();

        try {
            if(!projetoMencionado.equals(null)){
                if (projetoMencionado.getStatus().equals("Em andamento")) {
                    if (projetoMencionado.getPublicacoes() > 0) {
                        projetoMencionado.concluir();
                        System.out.println("Projeto concluído.");
    
                    } else {
                        System.out.println("O projeto precisa ter ao menos uma publicação para ser concluído.");
                    }
    
                } else {
                    System.out.println("O projeto não está em andamento para ser concluído.");
                }
            }
        } catch (Exception e) {
            System.out.println("Não foi dado um projeto.");
        }

        System.out.print("Digite enter para continuar...");
        teclado.nextLine();

    }

    public void adicionarColaborador() {
        limparConsole();
        System.out.print("Digite o nome do Colaborador: ");
        String nome = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o email do Colaborador: ");
        String email = teclado.nextLine();

        limparConsole();
        System.out.println("Escolha uma das opções abaixo para definir a função do Colaborador:");
        System.out.println("(P) para professor...");
        System.out.println("(G) para graduando...");
        System.out.println("(O) para outros (pesquisador, mestrando, doutorando)...");

        String funcao = teclado.nextLine();
        funcao = funcao.toUpperCase();

        Colaboradores novoColaborador;

        if (funcao.equals("P")) {
            novoColaborador = new Professor(nome, email, funcao);
        } else if (funcao.equals("G")) {
            novoColaborador = new Aluno(nome, email, funcao);
        } else {
            novoColaborador = new Colaboradores(nome, email, funcao);
        }

        listaColaboradores.add(novoColaborador);
    }

    void adicionarProjeto() {

        limparConsole();
        System.out.print("Digite o titulo do projeto: ");
        String tituloEntrada = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o objetivo do projeto: ");
        String objetivoEntrada = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o descricao do projeto: ");
        String descricaoEntrada = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o agencia do projeto: ");
        String agenciaEntrada = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o ano de inicio do projeto: ");
        int dataInicioEntrada = Integer.parseInt(teclado.nextLine());

        limparConsole();
        System.out.print("Digite o ano de termino do projeto: ");
        int dataTerminoEntrada = Integer.parseInt(teclado.nextLine());

        limparConsole();
        System.out.print("Digite o valor financiado do projeto: ");
        float valorFinanciadoEntrada = Float.parseFloat(teclado.nextLine());

        Projeto novoProjeto = new Projeto(tituloEntrada, objetivoEntrada, descricaoEntrada, dataInicioEntrada,
                dataTerminoEntrada, agenciaEntrada, valorFinanciadoEntrada);
        listaProjetos.add(novoProjeto);

    }

    void adicionarPublicacao() {
        limparConsole();
        System.out.print("Digite o nome da publicação: ");
        String nomePublicacao = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o nome da conferência onde foi publicada: ");
        String nomeConferencia = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o ano de publicação: ");
        Integer anoPublicacao = Integer.parseInt(teclado.nextLine());

        Publicacoes novaPublicacao = new Publicacoes(nomePublicacao, nomeConferencia, anoPublicacao);
        listaPublicacoes.add(novaPublicacao);
        Collections.sort(listaPublicacoes);

        limparConsole();

        Projeto projetoMencionado = getProjeto();

        try {
            if(!projetoMencionado.equals(null)){
                novaPublicacao.associarProjetoPublicacao(projetoMencionado);
                projetoMencionado.adicionarPublicacao(novaPublicacao);
            }
        } catch (Exception e) {
            System.out.println("Não foi dado um projeto.");
            System.out.print("Digite enter para continuar...");
            teclado.nextLine();
        }

        limparConsole();
        Boolean acabou = false;
        Integer noAutores = 0;

        while(!acabou){
            Colaboradores autor = getColaborador();

            try {
                if(!autor.equals(null)){
                    noAutores++;
                    novaPublicacao.associarAutorPublicacao(autor);
                    autor.associarPublicacaoColaborador(novaPublicacao);
    
                    limparConsole();
                    System.out.println("Autor associado!");
                    System.out.print("Digite enter para continuar...");
                    teclado.nextLine();
    
                }
            } catch (Exception e) { //autor nulo
                if(noAutores == 0){
                    limparConsole();
                    System.out.println("A publicação deve ter pelo menos um autor.");
                    System.out.print("Digite enter para continuar...");
                    teclado.nextLine();
                } else {
                    acabou = true;
                }
                
            }
        }

    }

    void relatorioLaboratorio() {

        limparConsole();
        
        System.out.println("Relatório de produção do laboratório:");
        System.out.println("Número de colaboradores:" + listaColaboradores.size());

        Integer elaboracao = 0;
        Integer andamento = 0;
        Integer concluido = 0;

        for (int i = 0; i < listaProjetos.size(); i++) {
            if (listaProjetos.get(i).getStatus().equals("Em elaboração")) {
                elaboracao++;
            } else if (listaProjetos.get(i).getStatus().equals("Em andamento")) {
                andamento++;
            } else if (listaProjetos.get(i).getStatus().equals("Concluído")) {
                concluido++;
            }
        }
        System.out.println("Número de projetos 'Em elaboração': " + elaboracao);
        System.out.println("Número de projetos 'Em andamento': " + andamento);
        System.out.println("Número de projetos concluídos: " + concluido);
        System.out.println("Número de projetos:" + listaProjetos.size());
        System.out.println("Número de publicações:" + listaPublicacoes.size());

        Integer orientacoes = 0;

        for (int j = 0; j < listaColaboradores.size(); j++) {
            if (listaColaboradores.get(j) instanceof Professor) {
                orientacoes += listaColaboradores.get(j).getNumeroProjetos();
            }
        }
        System.out.println("Número de orientações:" + orientacoes);

        System.out.println("");
        System.out.print("Digite enter para continuar...");
        teclado.nextLine();

    }

}