package controller;

import model.Aluno;
import model.AlunoTurma;
import model.DAO.TurmaDAO;
import model.DAO.EscolaDAO;
import model.DAO.CursoDAO;
import model.Turma;
import model.Escola;
import model.Curso;
import model.DAO.AlunoTurmaDAO;
import util.ConsoleUtil;

public class TurmaController {

    private final TurmaDAO turmaDAO;
    private final EscolaDAO escolaDAO;
    private final CursoDAO cursoDAO;
    private final AlunoTurmaDAO alunoTurmaDAO;

    public TurmaController(TurmaDAO turmaDAO, EscolaDAO escolaDAO, CursoDAO cursoDAO, AlunoTurmaDAO alunoTurmaDAO) {
        this.turmaDAO = turmaDAO;
        this.escolaDAO = escolaDAO;
        this.cursoDAO = cursoDAO;
        this.alunoTurmaDAO = alunoTurmaDAO;
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
            System.out.println("Escola ou Curso nao encontrado!");
        }
    }

    public void criarTurma1(Turma turma) {
        turmaDAO.criar(turma);
    }

    public void atualizarTurmaAdminGeral() {
        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorId(turmaId);

        if (turma == null) {
            System.out.println("Turma nao encontrada!");
            return;
        }

        String novoNome = ConsoleUtil.lerString("Novo Nome da Turma [" + turma.getNome() + "]: ");
        int novoCursoId = ConsoleUtil.lerInt("Novo ID do Curso [" + turma.getCurso().getId() + "]: ", 1, Integer.MAX_VALUE);
        int novaEscolaId = ConsoleUtil.lerInt("Novo ID da Escola [" + turma.getEscola().getId() + "]: ", 1, Integer.MAX_VALUE);

        Curso novoCurso = cursoDAO.buscarPorId(novoCursoId);
        Escola novaEscola = escolaDAO.buscarPorId(novaEscolaId);

        if (novoCurso == null || novaEscola == null) {
            System.out.println("Curso ou Escola nao encontrada!");
            return;
        }

        // Atualiza o nome SEM VALIDACAO
        turma.setNome(novoNome);
        turma.setCurso(novoCurso);
        turma.setEscola(novaEscola);
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

    public void atualizarTurmaEscola(int escolaId) {
        int idTurma = ConsoleUtil.lerInt("ID da Turma para atualizar: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorId(idTurma);

        // Verifica se a turma existe e pertence a escola
        if (turma == null || turma.getEscola().getId() != escolaId) {
            System.out.println("Turma nao encontrada ou nao pertence a sua escola!");
            return;
        }

        // Solicita novo nome e atualiza
        String novoNome = ConsoleUtil.lerString("Novo nome da Turma: ");
        turma.setNome(novoNome);
        turmaDAO.atualizar(turma);
        System.out.println("Turma atualizada com sucesso!");
    }

    public void listarTurmasDaEscola(int escolaId) {
        System.out.println("\n=== TURMAS DA ESCOLA ===");
        Turma[] turmas = turmaDAO.listarTodos();
        for (Turma turma : turmas) {
            if (turma != null && turma.getEscola().getId() == escolaId) {
                System.out.println(
                        "ID: " + turma.getId()
                        + " | Nome: " + turma.getNome()
                        + " | Curso: " + turma.getCurso().getSigla()
                        + " (" + turma.getCurso().getNome() + ")"
                );
            }
        }
    }

    public void listarAlunosDaTurma(int escolaId) {
        int idTurma = ConsoleUtil.lerInt("ID da Turma para listar alunos: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorId(idTurma);

        if (turma == null || turma.getEscola().getId() != escolaId) {
            System.out.println("Erro: Turma nao encontrada ou nao pertence a sua escola!");
            return;
        }

        System.out.println("\n=== ALUNOS DA TURMA " + idTurma + " ===");
        AlunoTurma[] vinculos = alunoTurmaDAO.listarPorTurma(idTurma);
        boolean flag = false;

        if (vinculos != null) {
            for (AlunoTurma rel : vinculos) {
                if (rel != null) {
                    Aluno a = rel.getAluno();
                    System.out.println(
                            "ID: " + a.getId()
                            + " | Nome: " + a.getNome()
                            + " | CPF: " + a.getCpf()
                            + " | Email: " + a.getEmail()
                    );
                    flag = true;
                }
            }
        }

        if (!flag) {
            System.out.println("Nenhum aluno vinculado a esta turma.");
        }
    }

    public void deletarTurmaEscola(int escolaId) {
        int idTurma = ConsoleUtil.lerInt("ID da Turma para deletar: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorId(idTurma);

        if (turma == null || turma.getEscola().getId() != escolaId) {
            System.out.println("Turma nao encontrada ou nao pertence a sua escola!");
            return;
        }

        turmaDAO.deletar(idTurma);
        System.out.println("Turma deletada com sucesso!");
    }

}
