package view;

import controller.CursoController;
import util.ConsoleUtil;

public class CursoView {

    private final CursoController cursoController;

    public CursoView(CursoController cursoController) {
        this.cursoController = cursoController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU CURSO ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    cursoController.criarCurso();
                    break;
                case 2:
                    cursoController.atualizarCurso();
                    break;
                case 3:
                    cursoController.listarCurso();
                    break;
                case 4:
                    cursoController.deletarCurso();
                    break;
            }
        } while (opcao != 5);
    }
}
