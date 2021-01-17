// import java.util.Collections;
import java.util.Scanner;


public class App {

    static void limparConsole() {
        for (int i = 0; i < 100; i++) {
            System.out.println("");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        boolean using = true;
        int opt;

        limparConsole();
        System.out.print("Digite seu nome:");
        String nomeAdm = teclado.nextLine();
        Laboratorio lab = new Laboratorio(nomeAdm, teclado);

        limparConsole();
        System.out.println("Bem-vindo(a), " + nomeAdm + "!");
        System.out.print("Digite enter para continuar...");
        teclado.nextLine();

        while (using) {

            limparConsole();
            System.out.println("Escolha uma das opções abaixo:");

            System.out.println("Digite (1) para adicionar um projeto");
            System.out.println("Digite (2) para adicionar um colaborador");
            System.out.println("Digite (3) para adicionar uma publicação"); 
            System.out.println("Digite (4) para concluir um projeto");
            System.out.println("Digite (5) para alocar os colaboradores em um projeto");
            System.out.println("Digite (6) para obter a consulta de um colaborador");
            System.out.println("Digite (7) para obter a consulta do laboratório");
            System.out.println("Digite (8) para sair");
            System.out.print("Digite a opção aqui: ");

            opt = Integer.parseInt(teclado.nextLine());
            System.out.println(opt);

            switch (opt) {

                case 1:
                    lab.adicionarProjeto();
                    break;
                
                case 2:
                    lab.adicionarColaborador();
                    break;

                case 3:
                    lab.adicionarPublicacao();
                    break;

                case 4:
                    lab.concluirProjeto();
                    break;

                case 5:
                    lab.associarColaboradorProjeto();
                    break;

                case 6:  
                    lab.consultaColaborador();
                    break;

                case 7:
                    lab.relatorioLaboratorio();
                    break;

                case 8:
                    limparConsole();
                    using = false;
                    break;
                    
                default: 
                    
            }
        }

        teclado.close();
    }

    
}
