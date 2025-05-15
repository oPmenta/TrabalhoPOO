package view;

import util.*;
import controller.*;

public class RegistroProfessorDescricaoView {

    private RegistroProfessorDescricaoController registroProfessorDescricaoController;

    public RegistroProfessorDescricaoView(RegistroProfessorDescricaoController registroProfessorDescricaoController) {
        this.registroProfessorDescricaoController = registroProfessorDescricaoController;

    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU OBSERVAÇÕES DO ALUNO ===");
            System.out.println("1 - Registrar observações por aluno");
            System.out.println("2 - Atualizar observações individuais");
            System.out.println("3 - Listar observações individuais");
            System.out.println("4 - Deletar observação");
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
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 5);
    }
}
