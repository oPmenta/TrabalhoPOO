package view;

import controller.AlunoTurmaController;
import util.ConsoleUtil;

public class AlunoTurmaView {

    private final AlunoTurmaController alunoTurmaController;

    public AlunoTurmaView(AlunoTurmaController alunoTurmaController) {
        this.alunoTurmaController = alunoTurmaController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU VINCULAR ALUNOS ===");
            System.out.println("1 - Vincular Aluno");
            System.out.println("2 - Mover entre Turmas");
            System.out.println("3 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 3);

            switch (opcao) {
                case 1:
                    alunoTurmaController.vincularAlunoTurma();
                case 2:
                    //alunoTurmaController.moverAlunoTurma();
                    break;
            }
        } while (opcao != 3);
    }
}
