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
            System.out.println("\n=== MENU PROFESSOR ===");
            System.out.println("1 - Menu registros da turma");
            System.out.println("2 - Menu observações do aluno");
            System.out.println("3 - Menu eventos da vida acadêmica");
            System.out.println("4 - Listar Alunos de uma Turma");
            System.out.println("5 - Histórico de um aluno (Timeline)");
            System.out.println("6 - Relatório de Conselho de Classe");
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
                    new VidaAcademicaView(vidaAcademicaController).exibirMenu();
                    break;
                case 4:
                    // metodo de listar alunos de uma turma
                    break;
                case 5:
                    // metodo de mostrar historico do aluno
                    break;
                case 6:
                    // metodo de relatorio do conselho de classe
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
            }
        } while (opcao != 7);
    }
}
