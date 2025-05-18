package view;

import util.*;
import controller.*;

public class ProfessorView {

    private RegistroProfessorController registroProfessorController;
    private RegistroProfessorDescricaoController registroProfessorDescricaoController;
    private VidaAcademicaController vidaAcademicaController;
    private AlunoTurmaController alunoTurmaController;

    public ProfessorView(RegistroProfessorController registroProfessorController, RegistroProfessorDescricaoController registroProfessorDescricaoController, VidaAcademicaController vidaAcademicaController, AlunoTurmaController alunoTurmaController) {
        this.registroProfessorController = registroProfessorController;
        this.registroProfessorDescricaoController = registroProfessorDescricaoController;
        this.vidaAcademicaController = vidaAcademicaController;
        this.alunoTurmaController = alunoTurmaController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU PROFESSOR ===");
            System.out.println("1 - Menu registros da turma");
            System.out.println("2 - Menu observacoes do aluno");
            System.out.println("3 - Menu eventos da vida academica");
            System.out.println("4 - Listar Alunos de uma Turma");
            System.out.println("5 - Historico de um aluno (Timeline)");
            System.out.println("6 - Relatorio de Conselho de Classe");
            System.out.println("7 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 7);

            switch (opcao) {
                case 1:
                    new RegistroProfessorView(registroProfessorController).exibirMenu(escolaId);
                    break;
                case 2:
                    new RegistroProfessorDescricaoView(registroProfessorDescricaoController).exibirMenu(escolaId);
                    break;
                case 3:
                    new VidaAcademicaView(vidaAcademicaController).exibirMenu(escolaId);
                    break;
                case 4:
                    alunoTurmaController.listarAlunosTurma(escolaId);
                    break;
                case 5:
                    vidaAcademicaController.exibirTimelineAluno(escolaId);
                    break;
                case 6:
                    vidaAcademicaController.gerarRelatorioConselhoTurma(escolaId);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 7);
    }
}
