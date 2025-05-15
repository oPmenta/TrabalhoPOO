package view;

import util.*;
import controller.*;

public class FuncionarioView {

    private VidaAcademicaController vidaAcademicaController;
    private AlunoTurmaController alunoTurmaController;

    public FuncionarioView(VidaAcademicaController vidaAcademicaController, AlunoTurmaController alunoTurmaController) {
        this.vidaAcademicaController = vidaAcademicaController;
        this.alunoTurmaController = alunoTurmaController;
    }

    public void exibirMenu(int idEscola) {
        int opcao;
        do {
            System.out.println("\n=== MENU PROFESSOR ===");
            System.out.println("1 - Menu eventos da vida acadêmica");
            System.out.println("2 - Listar Alunos de uma Turma");
            System.out.println("3 - Histórico de um aluno (Timeline)");
            System.out.println("4 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 4);

            switch (opcao) {
                case 1:
                    // 
                    break;
                case 2:
                    //  
                    break;
                case 3:
                    // 
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 4);
    }
}
