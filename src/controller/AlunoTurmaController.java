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

    public void vincularAlunoTurma(int escolaId) {
        int alunoId = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(alunoId);

        if (aluno == null) {
            System.out.println("Erro: Aluno nao encontrado!");
            return;
        }

        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorIdEEscola(turmaId, escolaId);

        if (turma == null) {
            System.out.println("Erro: Turma nao encontrada ou nao pertence a escola!");
            return;
        }

        if (alunoTurmaDAO.buscarPorAlunoETurma(alunoId, turmaId) != null) {
            System.out.println("Erro: Aluno ja esta vinculado a esta turma!");
            return;
        }

        AlunoTurma relacao = new AlunoTurma(0, aluno, turma);
        alunoTurmaDAO.criar(relacao);
        System.out.println("Aluno vinculado a turma com sucesso!");
    }

    // Implementar outros m√©todos...
    public void moverAlunoTurma(int escolaId) {
        // Obter IDs via View
        int turmaOrigemId = ConsoleUtil.lerInt("ID da Turma de Origem: ", 1, Integer.MAX_VALUE);
        int turmaDestinoId = ConsoleUtil.lerInt("ID da Turma de Destino: ", 1, Integer.MAX_VALUE);

        // Validar turmas
        Turma turmaOrigem = turmaDAO.buscarPorIdEEscola(turmaOrigemId, escolaId);
        Turma turmaDestino = turmaDAO.buscarPorIdEEscola(turmaDestinoId, escolaId);

        if (turmaOrigem == null || turmaDestino == null) {
            System.out.println("Erro: Uma ou ambas as turmas sao invalidas ou nao pertencem a escola!");
            return;
        }

        // Atualizar v√≠nculos
        alunoTurmaDAO.atualizarTurmaAlunos(turmaOrigemId, turmaDestino);
        System.out.println("Alunos movidos com sucesso!");
    }

    public void listarAlunosTurma(int escolaId) {
        int turmaId = ConsoleUtil.lerInt("ID da Turma para listar alunos: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorIdEEscola(turmaId, escolaId);

        if (turma == null) {
            System.out.println("Erro: Turma n„o encontrada ou n„o pertence ‡ escola!");
            return;
        }

        AlunoTurma[] vinculos = alunoTurmaDAO.listarPorTurma(turmaId);
        System.out.println("\n=== ALUNOS DA TURMA " + turmaId + " ===");

        if (vinculos.length == 0) {
            System.out.println("Nenhum aluno vinculado a esta turma.");
            return;
        }

        for (AlunoTurma vinculo : vinculos) {
            Aluno aluno = vinculo.getAluno();
            System.out.printf(
                    "ID: %d | Nome: %s | CPF: %s | Email: %s\n",
                    aluno.getId(),
                    aluno.getNome(),
                    aluno.getCpf(),
                    aluno.getEmail()
            );
        }
    }
    
    
    

}
