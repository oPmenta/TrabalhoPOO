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
            }
        } while (opcao != 5);
    }

    public void exibirMenuEscola(int escolaId) {
        int opcao;
        do {
            System.out.println("\n=== MENU TURMA (ESCOLA) ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    turmaController.criarTurmaEscola(escolaId);
                    break;
                case 2:
                   // turmaController.atualizarTurmaEscola(escolaId);
                    break;
                case 3:
                   // turmaController.listarTurmasEscola(escolaId);
                    break;
                case 4:
                   // turmaController.deletarTurmaEscola(escolaId);
                    break;
            }
        } while (opcao != 5);
    }
}
