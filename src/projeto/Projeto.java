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

    static boolean professorExistente = false;
    static int dadosInformados = 0;

    String titulo, objetivo, descricao;
    int dataInicio, dataTermino;
    String agencia;
    Float valorFinanciado;

    private void clearconsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }
    public int getDadosInformados(){
        return dadosInformados;
    }

    public boolean publicacaoExistente(){
        boolean existe = false;
        if(listaPublicacoes.size() > 0){
            existe = true;
        }
        return existe;
    }

    public int getTermino(){
        return this.dataTermino;
    }


    public void setStatus(String status) {
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

    void setTitulo(String titulo) {
        this.titulo = titulo;
        if (!titulo.equals("")) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
        if (!objetivo.equals("")) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setDescricao(String descricao) {
        this.descricao = descricao;
        if (!descricao.equals("")) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setDataInicio(int dataInicio) {
        this.dataInicio = dataInicio;
        if (dataInicio != 0) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setDataTermino(int dataTermino) {
        this.dataTermino = dataTermino;
        if (dataTermino != 0) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setAgencia(String agencia) {
        this.agencia = agencia;
        if (!agencia.equals("")) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    void setValorFinanciado(Float valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
        if (valorFinanciado != 0) {
            dadosInformados++;
        } else {
            dadosInformados--;
        }
    }

    public void adicionarColaborador(Scanner reader, Colaboradores novoColaborador){
        listaColaboradores.add(novoColaborador);
        if((novoColaborador.getFuncao()).equals("P")){
            //adicionando professor
            if(!professorExistente){
                professorExistente = true;
                clearconsole();
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

        setTitulo(titulo);
        setObjetivo(objetivo);
        setDescricao(descricao);
        setDataInicio(dataInicio);
        setDataTermino(dataTermino);
        setAgencia(agencia);
        setValorFinanciado(valorFinanciado);


    } //configurando o projeto
    public void completandoDados(Scanner reader){
        
        if(titulo.equals("")){
            clearconsole();

            System.out.print("Digite o titulo do projeto: ");

            String tituloEntrada= reader.nextLine();
            setTitulo(tituloEntrada);
    
        }
        if(objetivo.equals("")){
            clearconsole();

            System.out.print("Digite o objetivo do projeto: ");

            String objetivoEntrada = reader.nextLine();
            setObjetivo(objetivoEntrada);
            
        }
        if(descricao.equals("")){
            clearconsole();

            System.out.print("Digite o descricao do projeto: ");

            String descricaoEntrada= reader.nextLine();
            setDescricao(descricaoEntrada);
            
        }
        if(agencia.equals("")){
            clearconsole();

            System.out.print("Digite o agencia do projeto: ");

            String agenciaEntrada= reader.nextLine();
            setAgencia(agenciaEntrada);

        }
        if(dataInicio == 0){
            clearconsole();
            
            System.out.print("Digite o ano de inicio do projeto: ");

            int dataInicioEntrada = Integer.parseInt(reader.nextLine());
            setDataInicio(dataInicioEntrada);
            
        }
        if(dataTermino == 0){
            clearconsole();

            System.out.print("Digite o ano de termino do projeto: ");

            int dataTerminoEntrada = Integer.parseInt(reader.nextLine());
            setDataTermino(dataTerminoEntrada);
            
        }
        if(valorFinanciado == 0){
            clearconsole();

            System.out.print("Digite o valor financiado do projeto: ");

            float valorFinanciadoEntrada = Float.parseFloat(reader.nextLine());
            setValorFinanciado(valorFinanciadoEntrada);
        }

        if(dadosInformados == -7){
            statusProjeto = "Em andamento";
        }

    }
    public void concluir() {
        
        setStatus("Concluído");
        for(int i = 0; i < listaColaboradores.size(); i++){
            Colaboradores aux = listaColaboradores.get(i);
            aux.encerrandoProjeto();
        }
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
