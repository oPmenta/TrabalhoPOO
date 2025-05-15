package view;

import controller.RegistroProfessorController;
import util.ConsoleUtil;

public class RegistroProfessorView {

    private final RegistroProfessorController registroProfessorController;

    public RegistroProfessorView(RegistroProfessorController registroProfessorController) {
        this.registroProfessorController = registroProfessorController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU REGISTROS DA TURMA ===");
            System.out.println("1 - Registrar dados gerais da turma");
            System.out.println("2 - Atualizar registros de turma");
            System.out.println("3 - Listar registros de turma");
            System.out.println("4 - Deletar registro da turma");
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
                    System.out.println("Voltar...");
                    break;
            }
        } while (opcao != 5);
    }
}
