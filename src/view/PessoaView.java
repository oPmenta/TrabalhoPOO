package view;

import controller.PessoaController;
import util.ConsoleUtil;

public class PessoaView {

    private final PessoaController pessoaController;

    public PessoaView(PessoaController pessoaController) {
        this.pessoaController = pessoaController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU PESSOA ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    pessoaController.criarPessoa();
                    break;
                case 2:
                    pessoaController.atualizarPessoa();
                    break;
                case 3:
                    pessoaController.listarPessoas();
                    break;
                case 4:
                    pessoaController.deletarPessoa();
                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 5);
    }
}
