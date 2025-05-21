/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import controller.EscolaController;
import controller.PessoaController;
import controller.UsuarioController;
import controller.CursoController;
import controller.TurmaController;
import controller.AlunoController;
import model.Aluno;
import model.Curso;
import model.Escola;
import model.Pessoa;
import model.Turma;
import model.Usuario;

/**
 *
 * @author zaneg
 */
public class InicializaDados {

    public static void PreCadastro(PessoaController pessoaController, EscolaController escolaController, UsuarioController usuarioController, CursoController cursoController, TurmaController turmaController, AlunoController alunoController) {
        // Verifica se já existem os admins e o professor
        Pessoa adminGeral = pessoaController.buscarPorLogin("admin");
        Pessoa adminEscola = pessoaController.buscarPorLogin("adminE");
        Pessoa professor = pessoaController.buscarPorLogin("professor");
        Pessoa funcionario = pessoaController.buscarPorLogin("funcionario");

        // Cria Escola padrão
        Escola escolaPadrao = new Escola(1, "IFTM", "Uberaba", "(34)3326-1100");
        escolaController.criarEscola(escolaPadrao);

        Escola escolaPadrao2 = new Escola(2, "Gabarito", "Uberaba", "(34)3336-6363");
        escolaController.criarEscola(escolaPadrao2);

        // Cria cursos
        Curso curso1 = new Curso(0, "Análise e Desenvolvimento de Sistemas", "ADS", "SUPERIOR");
        cursoController.criarCurso1(curso1);

        Curso curso2 = new Curso(0, "Engenharia de Software", "ENG", "SUPERIOR");
        cursoController.criarCurso1(curso2);

        // Cria Turmas
        Turma turma1 = new Turma(0, "Turma A", curso1, escolaPadrao, "2024-1");
        turmaController.criarTurma1(turma1);

        Turma turma2 = new Turma(0, "Turma B", curso2, escolaPadrao, "2024-1");
        turmaController.criarTurma1(turma2);

        Turma turma3 = new Turma(0, "Turma C", curso1, escolaPadrao2, "2024-1");
        turmaController.criarTurma1(turma3);

        Turma turma4 = new Turma(0, "Turma D", curso2, escolaPadrao2, "2024-1");
        turmaController.criarTurma1(turma4);

        // Cria Alunos
        Aluno aluno1 = new Aluno(0, "Thiago Pimenta", "123.456.789-00", "thiago@gmail.com");
        alunoController.criarAluno1(aluno1);

        Aluno aluno2 = new Aluno(0, "Yuri Fernandes", "987.654.321-00", "yuri@gmail.com");
        alunoController.criarAluno1(aluno2);

        if (adminGeral == null) {
            // Cria Pessoa admin_geral
            Pessoa admin1 = new Pessoa(1, "Admin", "admin", "admin123");
            pessoaController.criarPessoa(admin1);

            // Cria Usuário admin_geral
            Usuario adminUsuario = new Usuario(1, admin1, escolaPadrao, "ADMIN_GERAL");
            usuarioController.criarUsuario(adminUsuario);
        }

        if (adminEscola == null && professor == null && funcionario == null) {
            // Cria Pessoa admin_escola 1
            Pessoa admin2 = new Pessoa(2, "AdminE", "adminE", "admin123");
            pessoaController.criarPessoa(admin2);

            // Cria Pessoa admin_escola 2
            Pessoa admin3 = new Pessoa(3, "AdminE_2", "adminE2", "admin123");
            pessoaController.criarPessoa(admin3);

            // Cria Pessoa professor 1
            Pessoa professorE = new Pessoa(4, "Professor", "professor", "123");
            pessoaController.criarPessoa(professorE);

            // Cria Pessoa professor 2
            Pessoa professorE2 = new Pessoa(5, "Professor_2", "professor2", "123");
            pessoaController.criarPessoa(professorE2);

            // Cria Pessoa funcionario 1
            Pessoa funcionarioE = new Pessoa(6, "Funcionario", "funcionario", "123");
            pessoaController.criarPessoa(funcionarioE);

            // Cria Pessoa funcionario 2
            Pessoa funcionarioE2 = new Pessoa(6, "Funcionario_2", "funcionario2", "123");
            pessoaController.criarPessoa(funcionarioE2);

            // Cria Usuário admin_escola 1
            Usuario adminEUsuario = new Usuario(2, admin2, escolaPadrao, "ADMIN_ESCOLA");
            usuarioController.criarUsuario(adminEUsuario);

            // Cria Usuário admin_escola 2
            Usuario adminEUsuario2 = new Usuario(3, admin3, escolaPadrao2, "ADMIN_ESCOLA");
            usuarioController.criarUsuario(adminEUsuario2);

            // Cria Usuário professor 1
            Usuario professorUsuario = new Usuario(4, professorE, escolaPadrao, "PROFESSOR");
            usuarioController.criarUsuario(professorUsuario);

            // Cria Usuário professor 2
            Usuario professorUsuario2 = new Usuario(5, professorE2, escolaPadrao2, "PROFESSOR");
            usuarioController.criarUsuario(professorUsuario2);

            // Cria Usuário funcionario 1
            Usuario funcionarioUsuario = new Usuario(6, funcionarioE, escolaPadrao, "FUNCIONARIO");
            usuarioController.criarUsuario(funcionarioUsuario);

            // Cria Usuário funcionario 2
            Usuario funcionarioUsuario2 = new Usuario(7, funcionarioE2, escolaPadrao2, "FUNCIONARIO");
            usuarioController.criarUsuario(funcionarioUsuario2);
        }

    }
}
