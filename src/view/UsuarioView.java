package view;

import controller.UsuarioController;
import util.ConsoleUtil;

public class UsuarioView {

    private final UsuarioController usuarioController;

    public UsuarioView(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU USUÁRIO ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    usuarioController.criarUsuario();
                    break;
                case 2:
                    usuarioController.atualizarUsuario();
                    break;
                case 3:
                    usuarioController.listarUsuario();
                    break;
                case 4:
                    usuarioController.deletarUsuario();
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 5);
    }
}
