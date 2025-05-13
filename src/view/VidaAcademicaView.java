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
            System.out.println("\n=== MENU VIDA ACADÊMICA ===");
            System.out.println("1 - Adicionar Registro");
            System.out.println("2 - Listar Registros");
            System.out.println("3 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 3);

            switch (opcao) {
                case 1:
                    //vidaAcademicaController.criarRegistro();
                    break;
                case 2:
                    //vidaAcademicaController.listarRegistros();
                    break;
            }
        } while (opcao != 3);
    }
}
