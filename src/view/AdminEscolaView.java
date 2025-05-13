package view;

import controller.*;
import util.ConsoleUtil;

public class AdminEscolaView {

    private AlunoController alunoController;
    private TurmaController turmaController;
    private UsuarioController usuarioController;
    private EscolaController escolaController;

    public AdminEscolaView(AlunoController alunoController, TurmaController turmaController,
            UsuarioController usuarioController, EscolaController escolaController) {
        this.alunoController = alunoController;
        this.turmaController = turmaController;
        this.usuarioController = usuarioController;
        this.escolaController = escolaController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n=== MENU ADMIN ESCOLA ===");
            System.out.println("1 - Menu de Aluno");
            System.out.println("2 - Menu de Usuários da Escola");
            System.out.println("3 - Menu de Turma");
            System.out.println("4 - Vincular Alunos às Turmas");
            System.out.println("5 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);

            switch (opcao) {
                case 1:
                    new AlunoView(alunoController).exibirMenu();
                    break;
                case 2:
                    new UsuarioEscolaView(usuarioController, escolaController).exibirMenu(escolaId);
                    break;
                case 3:
                    new TurmaView(turmaController).exibirMenuEscola(escolaId);
                    break;
                case 4:
                    //turmaController.vincularAlunoTurma();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 5);
    }
}
