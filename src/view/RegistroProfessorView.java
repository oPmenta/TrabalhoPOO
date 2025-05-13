package view;

import controller.RegistroProfessorController;
import util.ConsoleUtil;

public class RegistroProfessorView {

    private final RegistroProfessorController registroController;

    public RegistroProfessorView(RegistroProfessorController registroController) {
        this.registroController = registroController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU REGISTROS PROFESSOR ===");
            System.out.println("1 - Criar Registro");
            System.out.println("2 - Listar Registros");
            System.out.println("3 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 3);

            switch (opcao) {
                case 1:
                    //registroController.criarRegistro();
                    break;
                case 2:
                    //registroController.listarRegistros();
                    break;
            }
        } while (opcao != 3);
    }
}
