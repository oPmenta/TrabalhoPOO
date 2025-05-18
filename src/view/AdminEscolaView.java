package view;

import controller.*;
import util.ConsoleUtil;

public class AdminEscolaView {

    private AlunoController alunoController;
    private TurmaController turmaController;
    private UsuarioController usuarioController;
    private EscolaController escolaController;
    private CursoController cursoController;
    private AlunoTurmaController alunoTurmaController;

    public AdminEscolaView(AlunoController alunoController, TurmaController turmaController,
            UsuarioController usuarioController, EscolaController escolaController, CursoController cursoController, AlunoTurmaController alunoTurmaController) {
        this.alunoController = alunoController;
        this.turmaController = turmaController;
        this.usuarioController = usuarioController;
        this.escolaController = escolaController;
        this.cursoController = cursoController;
        this.alunoTurmaController = alunoTurmaController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU ADMIN ESCOLA ===");
            System.out.println("1 - Menu de Aluno");
            System.out.println("2 - Menu de Usuarios da Escola");
            System.out.println("3 - Menu de Curso");
            System.out.println("4 - Menu de Turma");
            System.out.println("5 - Vincular Alunos as Turmas");
            System.out.println("6 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 6);

            switch (opcao) {
                case 1:
                    new AlunoView(alunoController).exibirMenu();
                    break;
                case 2:
                    new UsuarioEscolaView(usuarioController, escolaController).exibirMenu(escolaId);
                    break;
                case 3:
                    new CursoView(cursoController).exibirMenu();
                    break;
                case 4:
                    new TurmaView(turmaController).exibirMenuAdminEscola(escolaId);
                    break;
                case 5:
                    new AlunoTurmaView(alunoTurmaController).exibirMenu(escolaId);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 6);
    }
}
