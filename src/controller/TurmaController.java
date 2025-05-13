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
    public void atualizarTurmaAdminGeral() {
        // 1) Lê o ID da turma e busca no banco
        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorId(turmaId);

        if (turma == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        // 2) Lê os novos valores
        String novoNome = ConsoleUtil.lerString("Novo Nome da Turma [" + turma.getNome() + "]: ");
        int novoCursoId = ConsoleUtil.lerInt("Novo ID do Curso [" + turma.getCurso().getId() + "]: ", 1, Integer.MAX_VALUE);
        int novaEscolaId = ConsoleUtil.lerInt("Novo ID da Escola [" + turma.getEscola().getId() + "]: ", 1, Integer.MAX_VALUE);

        // 3) Busca as entidades relacionadas
        Curso novoCurso = cursoDAO.buscarPorId(novoCursoId);
        Escola novaEscola = escolaDAO.buscarPorId(novaEscolaId);

        if (novoCurso == null || novaEscola == null) {
            System.out.println("Curso ou Escola não encontrada!");
            return;
        }

        // 4) Atualiza os campos da turma somente se o usuário digitou algo (pode querer manter o existente)
        if (!novoNome.trim().isEmpty()) {
            turma.setNome(novoNome);
        }
        turma.setCurso(novoCurso);
        turma.setEscola(novaEscola);

        // 5) Persiste e notifica
        turmaDAO.atualizar(turma);
        System.out.println("Turma atualizada com sucesso!");
    }

    public void listarTurmaAdminGeral() {
        Turma[] turma = turmaDAO.listarTodos();
        for (Turma p : turma) {
            System.out.println("ID da Turma: " + p.getId() + " | Nome da Turma: " + p.getNome() + " | Nome do Curso: " + p.getCurso().getSigla() + " (" + p.getCurso().getNome() + ")");
        }
    }

    public void deletarTurmaAdminGeral() {
        int id = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        turmaDAO.deletar(id);
        System.out.println("Turma deletada!");
    }
}
