package controller;

import model.DAO.AlunoTurmaDAO;
import model.DAO.AlunoDAO;
import model.DAO.TurmaDAO;
import model.AlunoTurma;
import model.Aluno;
import model.Turma;
import util.ConsoleUtil;

public class AlunoTurmaController {

    private final AlunoTurmaDAO alunoTurmaDAO;
    private final AlunoDAO alunoDAO;
    private final TurmaDAO turmaDAO;

    public AlunoTurmaController(AlunoTurmaDAO alunoTurmaDAO, AlunoDAO alunoDAO, TurmaDAO turmaDAO) {
        this.alunoTurmaDAO = alunoTurmaDAO;
        this.alunoDAO = alunoDAO;
        this.turmaDAO = turmaDAO;
    }

    public void vincularAlunoTurma() {
        int alunoId = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(alunoId);
        Turma turma = turmaDAO.buscarPorId(turmaId);

        if (aluno != null && turma != null) {
            AlunoTurma relacao = new AlunoTurma(0, aluno, turma);
            alunoTurmaDAO.criar(relacao);
            System.out.println("Aluno vinculado à turma!");
        } else {
            System.out.println("Aluno ou Turma não encontrado!");
        }
    }

    // Implementar outros métodos...
}
