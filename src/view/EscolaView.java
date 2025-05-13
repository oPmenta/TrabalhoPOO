package view;

import controller.EscolaController;
import util.ConsoleUtil;

public class EscolaView {

    private final EscolaController escolaController;

    public EscolaView(EscolaController escolaController) {
        this.escolaController = escolaController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU ESCOLA ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Listar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1 ->
                    escolaController.criarEscola();
                case 2 ->
                    escolaController.atualizarEscola();
                case 3 ->
                    escolaController.listarEscolas();
                case 4 ->
                    escolaController.deletarEscola();
            }
        } while (opcao != 5);
    }
}
