package view;

import controller.AlunoTurmaController;
import controller.TurmaController;
import util.ConsoleUtil;

public class AlunoTurmaView {

    private final AlunoTurmaController alunoTurmaController;
    private final TurmaController turmaController;

    public AlunoTurmaView(AlunoTurmaController alunoTurmaController, TurmaController turmaController) {
        this.alunoTurmaController = alunoTurmaController;
        this.turmaController = turmaController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU VINCULAR ALUNOS ===");
            System.out.println("1 - Vincular Aluno");
            System.out.println("2 - Mover entre Turmas");
            System.out.println("3 - Listar Alunos de uma Turma");
            System.out.println("4 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 4);

            switch (opcao) {
                case 1:
                    alunoTurmaController.vincularAlunoTurma(escolaId);
                    break;
                case 2:
                    alunoTurmaController.moverAlunoTurma(escolaId);
                    break;
                case 3:
                    turmaController.listarAlunosDaTurma(escolaId);
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 4);
    }
}
