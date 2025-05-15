package view;

import controller.TurmaController;
import util.ConsoleUtil;

public class TurmaView {

    private final TurmaController turmaController;

    public TurmaView(TurmaController turmaController) {
        this.turmaController = turmaController;
    }

    public void exibirMenuAdminGeral() {
        int opcao;
        do {
            System.out.println("\n=== MENU TURMA (ADMIN GERAL) ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar Todas");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    turmaController.criarTurmaAdminGeral();
                    break;
                case 2:
                    turmaController.atualizarTurmaAdminGeral();
                    break;
                case 3:
                    turmaController.listarTurmaAdminGeral();
                    break;
                case 4:
                    turmaController.deletarTurmaAdminGeral();
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 5);
    }

    public void exibirMenuAdminEscola(int escolaId) {
        int opcao;
        do {
            System.out.println("\n=== MENU TURMA (ADMIN ESCOLA) ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar Turmas");
            System.out.println("4 - Listar Alunos de uma Turma");
            System.out.println("5 - Deletar");
            System.out.println("6 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 6);

            switch (opcao) {
                case 1:
                    turmaController.criarTurmaEscola(escolaId);
                    break;
                case 2:
                    turmaController.atualizarTurmaEscola(escolaId);
                    break;
                case 3:
                    turmaController.listarTurmasDaEscola(escolaId);
                    break;
                case 4:
                    turmaController.listarAlunosDaTurma(escolaId);
                    break;
                case 5:
                    turmaController.deletarTurmaEscola(escolaId);
                    break;
                case 6:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 6);
    }
}
