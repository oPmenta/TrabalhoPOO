package main;

import model.DAO.*;
import model.*;
import controller.*;
import view.*;
import java.util.Scanner;

public class Main {

    private static Usuario usuarioLogado;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicialização dos DAOs
        PessoaDAO pessoaDAO = new PessoaDAO();
        EscolaDAO escolaDAO = new EscolaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CursoDAO cursoDAO = new CursoDAO();
        TurmaDAO turmaDAO = new TurmaDAO(escolaDAO, cursoDAO);
        AlunoDAO alunoDAO = new AlunoDAO();
        AlunoTurmaDAO alunoTurmaDAO = new AlunoTurmaDAO(alunoDAO, turmaDAO);
        RegistroProfessorDAO registroProfessorDAO = new RegistroProfessorDAO ();
        RegistroProfessorDescricaoDAO registroProfessorDescricaoDAO = new RegistroProfessorDescricaoDAO();
        
        // Inicialização dos controladores
        PessoaController pessoaController = new PessoaController(pessoaDAO);
        EscolaController escolaController = new EscolaController(escolaDAO);
        CursoController cursoController = new CursoController(cursoDAO);
        TurmaController turmaController = new TurmaController(turmaDAO, escolaDAO, cursoDAO, alunoTurmaDAO);
        UsuarioController usuarioController = new UsuarioController(pessoaDAO, escolaDAO, usuarioDAO);
        AlunoController alunoController = new AlunoController(alunoDAO);
        AlunoTurmaController alunoTurmaController = new AlunoTurmaController(alunoTurmaDAO, alunoDAO, turmaDAO);
        RegistroProfessorController registroProfessorController = new RegistroProfessorController(registroProfessorDAO, usuarioDAO);
        RegistroProfessorDescricaoController registroProfessorDescricaoController = new RegistroProfessorDescricaoController();
        VidaAcademicaController vidaAcademicaController = new VidaAcademicaController ();

        // Cria o admin pré-cadastrado se necessário
        criarAdminSeNecessario(pessoaController, escolaController, usuarioController);

        // Loop principal
        while (true) {
            if (usuarioLogado == null) {
                exibirTelaBoasVindas();
                fazerLogin(usuarioController);
            } else {
                exibirMenuPrincipal(
                        pessoaController,
                        escolaController,
                        usuarioController,
                        cursoController,
                        turmaController,
                        alunoController,
                        alunoTurmaController,
                        registroProfessorController,
                        registroProfessorDescricaoController,
                        vidaAcademicaController
                );
            }
        }
    }

    private static void criarAdminSeNecessario(PessoaController pessoaController, EscolaController escolaController, UsuarioController usuarioController) {
        // Verifica se já existem os admins
        Pessoa adminGeral = pessoaController.buscarPorLogin("admin");
        Pessoa adminEscola = pessoaController.buscarPorLogin("adminE");

        if (adminGeral == null && adminEscola == null) {
            // Cria Pessoa admin_geral
            Pessoa admin1 = new Pessoa(1, "Admin", "admin", "admin123");
            pessoaController.criarPessoa(admin1);

            // Cria Pessoa admin_escola
            Pessoa admin2 = new Pessoa(2, "AdminE", "adminE", "admin123");
            pessoaController.criarPessoa(admin2);

            // Cria Escola padrão
            Escola escolaPadrao = new Escola(1, "IFTM", "Uberaba", "(34)3326-1100");
            escolaController.criarEscola(escolaPadrao);

            // Cria Usuário admin_geral
            Usuario adminUsuario = new Usuario(1, admin1, escolaPadrao, "ADMIN_GERAL");
            usuarioController.criarUsuario(adminUsuario);

            // Cria Usuário admin_escola
            Usuario adminEUsuario = new Usuario(2, admin2, escolaPadrao, "ADMIN_ESCOLA");
            usuarioController.criarUsuario(adminEUsuario);
        }
    }

    private static void exibirTelaBoasVindas() {
        System.out.println("\n=== BEM-VINDO AO SISTEMA ACADEMICO ===");
    }

    private static void fazerLogin(UsuarioController usuarioController) {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = usuarioController.autenticar(login, senha);

        if (usuarioLogado != null) {
            System.out.println("Login realizado como: " + usuarioLogado.getTipo());
        } else {
            System.out.println("Login ou senha inválidos!");
        }
    }

    private static void exibirMenuPrincipal(
            PessoaController pessoaController,
            EscolaController escolaController,
            UsuarioController usuarioController,
            CursoController cursoController,
            TurmaController turmaController,
            AlunoController alunoController,
            AlunoTurmaController alunoTurmaController,
            RegistroProfessorController registroProfessorController,
            RegistroProfessorDescricaoController registroProfessorDescricaoController,
            VidaAcademicaController vidaAcademicaController
    ) {
        if (usuarioLogado.getTipo().equals("ADMIN_GERAL")) {
            AdminGeralView adminGeralView = new AdminGeralView(
                    pessoaController,
                    escolaController,
                    usuarioController,
                    cursoController,
                    turmaController
            );
            adminGeralView.exibirMenu();
            usuarioLogado = null; // Logout após sair do menu
        } else if (usuarioLogado.getTipo().equals("ADMIN_ESCOLA")) {
            AdminEscolaView adminEscolaView = new AdminEscolaView(
                    alunoController,
                    turmaController,
                    usuarioController,
                    escolaController,
                    cursoController,
                    alunoTurmaController
            );
            adminEscolaView.exibirMenu(usuarioLogado.getEscola().getId());
            usuarioLogado = null; // Logout após sair do menu
        } else if (usuarioLogado.getTipo().equals("PROFESSOR")) {
            ProfessorView professorView = new ProfessorView(
                    registroProfessorController,
                    registroProfessorDescricaoController,
                    vidaAcademicaController
            );
            professorView.exibirMenu(usuarioLogado.getEscola().getId());
            usuarioLogado = null; // Logout após sair do menu
        }

    }
}
