package controller;

import model.DAO.TurmaDAO;
import model.DAO.EscolaDAO;
import model.DAO.CursoDAO;
import model.Turma;
import model.Escola;
import model.Curso;
import util.ConsoleUtil;

public class TurmaController {

    private final TurmaDAO turmaDAO;
    private final EscolaDAO escolaDAO;
    private final CursoDAO cursoDAO;

    public TurmaController(TurmaDAO turmaDAO, EscolaDAO escolaDAO, CursoDAO cursoDAO) {
        this.turmaDAO = turmaDAO;
        this.escolaDAO = escolaDAO;
        this.cursoDAO = cursoDAO;
    }

    public void criarTurmaAdminGeral() {
        int escolaId = ConsoleUtil.lerInt("ID da Escola: ", 1, Integer.MAX_VALUE);
        criarTurma(escolaId);
    }

    public void criarTurmaEscola(int escolaId) {
        criarTurma(escolaId);
    }

    private void criarTurma(int escolaId) {
        String nome = ConsoleUtil.lerString("Nome da Turma: ");
        int cursoId = ConsoleUtil.lerInt("ID do Curso: ", 1, Integer.MAX_VALUE);
        Escola escola = escolaDAO.buscarPorId(escolaId);
        Curso curso = cursoDAO.buscarPorId(cursoId);

        if (escola != null && curso != null) {
            Turma turma = new Turma(0, nome, curso, escola, "2024-1");
            turmaDAO.criar(turma);
            System.out.println("Turma criada com ID: " + turma.getId());
        } else {
            System.out.println("Escola ou Curso não encontrado!");
        }
    }

    // Implementar outros métodos...
}
