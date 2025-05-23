package view;

import util.*;
import controller.*;
import model.Usuario;

public class FuncionarioView {

    private VidaAcademicaController vidaAcademicaController;
    private AlunoTurmaController alunoTurmaController;

    public FuncionarioView(VidaAcademicaController vidaAcademicaController, AlunoTurmaController alunoTurmaController) {
        this.vidaAcademicaController = vidaAcademicaController;
        this.alunoTurmaController = alunoTurmaController;
    }

    public void exibirMenu(int escolaId, Usuario funcionario) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU FUNCIONARIO ===");
            System.out.println("1 - Menu eventos da vida academica");
            System.out.println("2 - Listar Alunos de uma Turma");
            System.out.println("3 - Historico de um aluno (Timeline)");
            System.out.println("4 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 4);

            switch (opcao) {
                case 1:
                    new VidaAcademicaView(vidaAcademicaController).exibirMenu(escolaId);
                    break;
                case 2:
                    alunoTurmaController.listarAlunosTurma(escolaId);
                    break;
                case 3:
                    vidaAcademicaController.exibirTimelineAluno(escolaId);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 4);
    }
}
