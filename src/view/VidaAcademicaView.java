package view;

import controller.VidaAcademicaController;
import util.ConsoleUtil;

public class VidaAcademicaView {

    private final VidaAcademicaController vidaAcademicaController;

    public VidaAcademicaView(VidaAcademicaController vidaAcademicaController) {
        this.vidaAcademicaController = vidaAcademicaController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU EVENTOS DA VIDA ACADÊMICA ===");
            System.out.println("1 - Registrar eventos da vida acadêmica");
            System.out.println("2 - Atualizar eventos da vida acadêmica");
            System.out.println("3 - Listar eventos da vida acadêmica");
            System.out.println("4 - Deletar eventos da vida acadêmica");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 5);
    }
}
