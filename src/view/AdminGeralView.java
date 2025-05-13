package view;

import util.*;
import controller.*;

public class AdminGeralView {

    private PessoaController pessoaController;
    private EscolaController escolaController;
    private UsuarioController usuarioController;
    private CursoController cursoController;
    private TurmaController turmaController;

    public AdminGeralView(PessoaController pessoaController, EscolaController escolaController,
            UsuarioController usuarioController, CursoController cursoController,
            TurmaController turmaController) {
        this.pessoaController = pessoaController;
        this.escolaController = escolaController;
        this.usuarioController = usuarioController;
        this.cursoController = cursoController;
        this.turmaController = turmaController;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU ADMIN GERAL ===");
            System.out.println("1 - Menu de Pessoa");
            System.out.println("2 - Menu de Escola");
            System.out.println("3 - Menu de Usuario");
            System.out.println("4 - Menu de Curso");
            System.out.println("5 - Menu de Turma");
            System.out.println("6 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 6);

            switch (opcao) {
                case 1:
                    new PessoaView(pessoaController).exibirMenu();
                    break;
                case 2:
                    new EscolaView(escolaController).exibirMenu();
                    break;
                case 3:
                    new UsuarioView(usuarioController).exibirMenu();
                    break;
                case 4:
                    new CursoView(cursoController).exibirMenu();
                    break;
                case 5:
                    new TurmaView(turmaController).exibirMenuAdminGeral();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 6);
    }
}
