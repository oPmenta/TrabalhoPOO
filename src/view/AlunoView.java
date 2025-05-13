package view;

import controller.AlunoController;
import util.ConsoleUtil;

public class AlunoView {

    private final AlunoController alunoController;

    public AlunoView(AlunoController alunoController) {
        this.alunoController = alunoController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU ALUNO ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    alunoController.criarAluno();
                    break;
                case 2:
                    alunoController.atualizarAluno();
                    break;
                case 3:
                    alunoController.listarAluno();
                    break;
                case 4:
                    alunoController.deletarAluno();
                    break;
            }
        } while (opcao != 5);
    }
}
