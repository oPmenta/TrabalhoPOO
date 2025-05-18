package view;

import util.*;
import controller.*;

public class RegistroProfessorDescricaoView {

    private RegistroProfessorDescricaoController registroProfessorDescricaoController;

    public RegistroProfessorDescricaoView(RegistroProfessorDescricaoController registroProfessorDescricaoController) {
        this.registroProfessorDescricaoController = registroProfessorDescricaoController;

    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU OBSERVA��ES DO ALUNO ===");
            System.out.println("1 - Registrar observacoes por aluno");
            System.out.println("2 - Atualizar observacoes individuais");
            System.out.println("3 - Listar observacoes individuais");
            System.out.println("4 - Deletar observacao");
            System.out.println("5 - Voltar");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    registroProfessorDescricaoController.criarRegistroDesc(escolaId);
                    break;
                case 2:
                    registroProfessorDescricaoController.atualizarRegistroDesc(escolaId);
                    break;
                case 3:
                    registroProfessorDescricaoController.listarRegistroDesc(escolaId);
                    break;
                case 4:
                    registroProfessorDescricaoController.deletarRegistroDesc(escolaId);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 5);
    }
}
