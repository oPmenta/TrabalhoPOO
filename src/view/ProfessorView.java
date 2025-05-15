package view;

import util.*;
import controller.*;

public class ProfessorView {

    private RegistroProfessorController registroProfessorController;
    private RegistroProfessorDescricaoController registroProfessorDescricaoController;
    private VidaAcademicaController vidaAcademicaController;

    public ProfessorView(RegistroProfessorController registroProfessorController, RegistroProfessorDescricaoController registroProfessorDescricaoController, VidaAcademicaController vidaAcademicaController) {
        this.registroProfessorController = registroProfessorController;
        this.registroProfessorDescricaoController = registroProfessorDescricaoController;
        this.vidaAcademicaController = vidaAcademicaController;
    }

    public void exibirMenu(int idEscola) {
        int opcao;
        do {
            System.out.println("\n=== MENU PROFESSOR ===");
            System.out.println("1 - Menu registros da turma");
            System.out.println("2 - Menu observa��es do aluno");
            System.out.println("3 - Menu eventos da vida acad�mica");
            System.out.println("4 - Listar Alunos de uma Turma");
            System.out.println("5 - Hist�rico de um aluno (Timeline)");
            System.out.println("6 - Relat�rio de Conselho de Classe");
            System.out.println("7 - Logout");
            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 7);

            switch (opcao) {
                case 1:
                    new RegistroProfessorView(registroProfessorController).exibirMenu();
                    break;
                case 2:
                    new RegistroProfessorDescricaoView(registroProfessorDescricaoController).exibirMenu();
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
