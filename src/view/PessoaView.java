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
                case 1 ->
                    pessoaController.criarPessoa();
                case 2 ->
                    pessoaController.atualizarPessoa();
                case 3 ->
                    pessoaController.listarPessoas();
                case 4 ->
                    pessoaController.deletarPessoa();
            }
        } while (opcao != 5);
    }
}
