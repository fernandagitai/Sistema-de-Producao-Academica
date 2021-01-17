import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import projeto.*;
import publicacoes.*;
import colaboradores.*;

public class Laboratorio {

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

    static Colaboradores getColaborador(String nomeColaborador, Vector<Colaboradores> listaColaboradores) {
        Colaboradores colaboradorProcurado = null;
        for (int i = 0; i < listaColaboradores.size(); i++) {
            if (nomeColaborador.equals(listaColaboradores.get(i).getNome())) {
                colaboradorProcurado = listaColaboradores.get(i);
            }
        }
        return colaboradorProcurado;
    }

    static Projeto getProjeto(String nomeProjeto, Vector<Projeto> listaProjetos) {
        Projeto projetoProcurado = null;
        for (int i = 0; i < listaProjetos.size(); i++) {
            if (nomeProjeto.equals(listaProjetos.get(i).getTitulo())) {
                projetoProcurado = listaProjetos.get(i);
            }
        }
        return projetoProcurado;
    }

    void consultaColaborador() {

        limparConsole();

        System.out.print("Digite o nome do colaborador: ");
        String colaboradorConsultado = teclado.nextLine();

        Colaboradores colaborador = getColaborador(colaboradorConsultado, listaColaboradores);
        colaborador.consulta(teclado);
    }

    void associarColaboradorProjeto() {

        limparConsole();
        System.out.print("Digite o nome do colaborador: ");
        String nomeColaborador = teclado.nextLine();

        limparConsole();
        System.out.print("Digite o nome do projeto: ");
        String nomeProjeto = teclado.nextLine();

        Colaboradores colaboradorAssociado = getColaborador(nomeColaborador, listaColaboradores);
        Projeto projetoAssociado = getProjeto(nomeProjeto, listaProjetos);

        if (!colaboradorAssociado.equals(null) && !projetoAssociado.equals(null)) {

            if ((colaboradorAssociado instanceof Aluno) && colaboradorAssociado.getNumeroProjetos() > 0) {
                System.out.println("Um aluno de graduação só pode estar associado a um projeto por vez.");
            } else {
                colaboradorAssociado.associarProjetoColaborador(projetoAssociado);
                projetoAssociado.adicionarColaborador(teclado, colaboradorAssociado);
                System.out.println("Autor associado!");
            }

        } else {
            System.out.println("Este colaborador não foi encontrado.");
        }
    }

    void concluirProjeto() {

        limparConsole();

        System.out.print("Digite o nome do projeto: ");
        String nomeProjeto = teclado.nextLine();

        Projeto projetoMencionado = getProjeto(nomeProjeto, listaProjetos);

        if (projetoMencionado.equals(null)) {
            System.out.println("Projeto não encontrado.");

        } else {
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
        System.out.print("Digite o projeto associado, caso não exista digite '0': ");
        String nomeProjeto = teclado.nextLine();

        Projeto projetoMencionado = getProjeto(nomeProjeto, listaProjetos);

        if (projetoMencionado.equals(null)) {
            System.out.println(
                    "Este projeto de pesquisa não existe neste laboratório. Nessa publicação será considerada sem projeto de pesquisa associado.");
        }
        novaPublicacao.associarProjetoPublicacao(projetoMencionado);
        projetoMencionado.adicionarPublicacao(novaPublicacao);
        // sugestão: passar a classe e não o nome

        limparConsole();
        Integer noAutores = 0;
        System.out.println(
                "Digite o nome dos autores da publicação (Digite '0' para encerrar a adição de colaboradores)");
        System.out.print("Digite o nome de um autor: ");
        String nomeAutor = teclado.nextLine();

        while (!nomeAutor.equals("0")) {
            noAutores++;
            Colaboradores autor = getColaborador(nomeAutor, listaColaboradores);

            if (!autor.equals(null)) {
                novaPublicacao.associarAutorPublicacao(autor);
                autor.associarPublicacaoColaborador(novaPublicacao);
            } else {
                System.out.println("Colaborador não encontrado");
                noAutores--;
            }

            if (noAutores == 0) {
                System.out.println("A publicação deve ter pelo menos um autor.");
            }

            limparConsole();
            System.out.print("Digite o nome de outro autor (Digite '0' para encerrar a adição de colaboradores): ");
            nomeAutor = teclado.nextLine();
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