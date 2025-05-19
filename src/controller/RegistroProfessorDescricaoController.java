package controller;

import model.DAO.RegistroProfessorDescricaoDAO;
import model.DAO.RegistroProfessorDAO;
import model.DAO.AlunoDAO;
import model.DAO.AlunoTurmaDAO;
import model.RegistroProfessorDescricao;
import model.RegistroProfessor;
import model.Aluno;
import model.Turma;
import util.ConsoleUtil;

public class RegistroProfessorDescricaoController {

    private final RegistroProfessorDescricaoDAO registroDescricaoDAO;
    private final RegistroProfessorDAO registroProfessorDAO;
    private final AlunoDAO alunoDAO;
    private final AlunoTurmaDAO alunoTurmaDAO;

    public RegistroProfessorDescricaoController(
            RegistroProfessorDescricaoDAO registroDescricaoDAO,
            RegistroProfessorDAO registroProfessorDAO,
            AlunoDAO alunoDAO,
            AlunoTurmaDAO alunoTurmaDAO) {
        this.registroDescricaoDAO = registroDescricaoDAO;
        this.registroProfessorDAO = registroProfessorDAO;
        this.alunoDAO = alunoDAO;
        this.alunoTurmaDAO = alunoTurmaDAO;
    }

    int flag = 0;

    public void criarRegistroDesc(int escolaId) {
        int registroId = ConsoleUtil.lerInt("ID do Registro Professor: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro de professor nao encontrado!");
            return;
        }

        // Verifica se a turma do registro pertence a escola
        Turma turma = registro.getTurma();
        if (turma.getEscola().getId() != escolaId) {
            System.out.println("Erro: Registro de turma de outra escola!");
            return;
        }

        int alunoId = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(alunoId);

        if (aluno == null) {
            System.out.println("Erro: Aluno nao encontrado!");
            return;
        }

        // Verifica se o aluno esta vinculado a uma turma da escola
        boolean alunoVinculado = alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId);
        if (!alunoVinculado) {
            System.out.println("Erro: Aluno nao pertence a nenhuma turma da escola!");
            return;
        }

        String observacao = ConsoleUtil.lerString("Observacao: ");
        if (observacao.isBlank()) {
            System.out.println("Erro: Observacao nao pode ser vazia!");
            return;
        }

        RegistroProfessorDescricao descricao = new RegistroProfessorDescricao(
                0, registro, aluno, observacao
        );
        registroDescricaoDAO.criar(descricao);
        System.out.println("Descricao criada com sucesso!");
    }

    public void atualizarRegistroDesc(int escolaId) {
        int descricaoId = ConsoleUtil.lerInt("ID da Descricao: ", 1, Integer.MAX_VALUE);
        RegistroProfessorDescricao descricao = registroDescricaoDAO.buscarPorId(descricaoId);

        if (descricao == null) {
            System.out.println("Erro: Descricao nao encontrada!");
            return;
        }

        // Valida se o registro pertence a escola correta
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(descricao.getRegistro().getId());
        if (registro == null || registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Descricao nao pertence a esta escola!");
            return;
        }

        String novaObservacao = ConsoleUtil.lerString("Nova Observacao: ");
        if (novaObservacao.isBlank()) {
            System.out.println("Erro: Observacao nao pode ser vazia!");
            return;
        }

        descricao.setObservacao(novaObservacao);
        registroDescricaoDAO.atualizar(descricao);
        System.out.println("Observacao atualizada com sucesso!");
        flag = 1;
    }

    public void listarRegistroDesc(int escolaId) {
        System.out.println("\n\n=== DESCRICOES DE REGISTROS DE PROFESSORES ===");
        RegistroProfessorDescricao[] registros = registroDescricaoDAO.listarTodos();
        for (RegistroProfessorDescricao d : registros) {
            if (d == null) {
                continue;
            }
            RegistroProfessor reg = registroProfessorDAO.buscarPorId(d.getRegistro().getId());
            if (reg == null || reg.getTurma().getEscola().getId() != escolaId) {
                continue;
            }
            System.out.print(
                    "ID: " + d.getId()
                    + " | Registro ID: " + d.getRegistro().getId()
                    + " | Aluno: " + d.getAluno().getNome()
                    + " | Observação: " + d.getObservacao()
                    + " | Criado: " + d.getDataCriacao());
            if (flag == 0) {
                System.out.print(" | Atualizado: " + d.getDataCriacao());
            } else {
                System.out.print(" | Atualizado: " + d.getDataModificacao());
            }
        }
    }

    public void deletarRegistroDesc(int escolaId) {
        int descricaoId = ConsoleUtil.lerInt("Digite o ID da descricao que deseja deletar: ", 1, Integer.MAX_VALUE);
        RegistroProfessorDescricao descricao = registroDescricaoDAO.buscarPorId(descricaoId);

        if (descricao == null) {
            System.out.println("Erro: Descricao nao encontrada!");
            return;
        }

        RegistroProfessor registro = registroProfessorDAO.buscarPorId(descricao.getRegistro().getId());
        if (registro == null || registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Nao autorizado a deletar descricao de outra escola!");
            return;
        }

        registroDescricaoDAO.deletar(descricaoId);
        System.out.println("Descricao deletada com sucesso!");
    }

}
