package view;

import controller.RegistroProfessorController;
import util.ConsoleUtil;

public class RegistroProfessorView {

    private final RegistroProfessorController registroProfessorController;

    public RegistroProfessorView(RegistroProfessorController registroProfessorController) {
        this.registroProfessorController = registroProfessorController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU REGISTROS DA TURMA ===");
            System.out.println("1 - Registrar dados gerais da turma");
            System.out.println("2 - Atualizar registros de turma");
            System.out.println("3 - Listar registros de turma");
            System.out.println("4 - Deletar registro da turma");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    registroProfessorController.criarRegistro(escolaId);
                    break;
                case 2:
                    registroProfessorController.atualizarRegistro(escolaId);
                    break;
                case 3:
                    registroProfessorController.listarRegistrosPorEscola(escolaId);
                    break;
                case 4:
                    registroProfessorController.deletarRegistro(escolaId);
                    break;
                case 5:
                    System.out.println("Voltar...");
                    break;
            }
        } while (opcao != 5);
    }
}
