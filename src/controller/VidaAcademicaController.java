package controller;

import model.DAO.VidaAcademicaDAO;
import model.DAO.AlunoDAO;
import model.DAO.AlunoTurmaDAO;
import model.Aluno;
import model.DAO.RegistroProfessorDAO;
import model.DAO.RegistroProfessorDescricaoDAO;
import model.DAO.TurmaDAO;
import model.RegistroProfessor;
import model.RegistroProfessorDescricao;
import model.Turma;
import model.VidaAcademica;
import util.ConsoleUtil;

public class VidaAcademicaController {

    private final VidaAcademicaDAO vidaAcademicaDAO;
    private final AlunoDAO alunoDAO;
    private final AlunoTurmaDAO alunoTurmaDAO;
    private final TurmaDAO turmaDAO;
    private final RegistroProfessorDAO registroProfessorDAO;
    private final RegistroProfessorDescricaoDAO registroProfessorDescricaoDAO;

    public VidaAcademicaController(VidaAcademicaDAO vidaAcademicaDAO, AlunoDAO alunoDAO, AlunoTurmaDAO alunoTurmaDAO, TurmaDAO turmaDAO, RegistroProfessorDAO registroProfessorDAO, RegistroProfessorDescricaoDAO registroProfessorDescricaoDAO) {
        this.vidaAcademicaDAO = vidaAcademicaDAO;
        this.alunoDAO = alunoDAO;
        this.alunoTurmaDAO = alunoTurmaDAO;
        this.turmaDAO = turmaDAO;
        this.registroProfessorDAO = registroProfessorDAO;
        this.registroProfessorDescricaoDAO = registroProfessorDescricaoDAO;
    }

    public void criarVidaAcademica(int escolaId) {
        int alunoId = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(alunoId);

        if (aluno == null) {
            System.out.println("Erro: Aluno n�o encontrado!");
            return;
        }

        if (!alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId)) {
            System.out.println("Erro: Aluno n�o est� vinculado a nenhuma turma desta escola!");
            return;
        }

        String tipo = ConsoleUtil.lerString("Tipo (OBSERVACAO, INCIDENTE, ADVERTENCIA, MERITO): ").toUpperCase();
        if (!tipo.matches("OBSERVACAO|INCIDENTE|ADVERTENCIA|MERITO")) {
            System.out.println("Erro: Tipo inv�lido!");
            return;
        }

        String descricao = ConsoleUtil.lerString("Descri��o: ");
        if (descricao.trim().isEmpty()) {
            System.out.println("Erro: Descri��o n�o pode ser vazia!");
            return;
        }

        VidaAcademica registro = new VidaAcademica(0, tipo, descricao, aluno);
        vidaAcademicaDAO.criar(registro);
        System.out.println("Registro criado com ID: " + registro.getId());
    }

    // Implementar m�todos restantes (atualizar, listar, deletar)...
    public void atualizarVidaAcademica(int escolaId) {
        int registroId = ConsoleUtil.lerInt("ID do registro a atualizar: ", 1, Integer.MAX_VALUE);
        VidaAcademica registro = vidaAcademicaDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro n�o encontrado!");
            return;
        }

        int alunoId = registro.getAluno().getId();
        if (!alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId)) {
            System.out.println("Erro: Registro n�o pertence � escola do professor!");
            return;
        }

        String novoTipo = ConsoleUtil.lerString("Novo Tipo (OBSERVACAO, INCIDENTE, ADVERTENCIA, MERITO - deixe em branco para manter): ").toUpperCase();
        String novaDescricao = ConsoleUtil.lerString("Nova Descri��o (deixe em branco para manter): ");

        if (!novoTipo.isBlank()) {
            if (novoTipo.matches("OBSERVACAO|INCIDENTE|ADVERTENCIA|MERITO")) {
                registro.setTipo(novoTipo);
            } else {
                System.out.println("Erro: Tipo inv�lido! Altera��o ignorada.");
            }
        }

        if (!novaDescricao.isBlank()) {
            registro.setDescricao(novaDescricao);
        }

        vidaAcademicaDAO.atualizar(registro);
        System.out.println("Registro atualizado com sucesso!");
    }

    public void listarRegistrosAcademicos(int escolaId) {
        System.out.println("\n=== REGISTROS ACAD�MICOS ===");
        VidaAcademica[] registros = vidaAcademicaDAO.listarTodos();

        if (registros.length == 0) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        boolean encontrouRegistros = false;
        for (VidaAcademica registro : registros) {
            if (registro == null) {
                continue;
            }

            int alunoId = registro.getAluno().getId();
            if (alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId)) {
                System.out.printf(
                        "ID: %d | Tipo: %s | Descri��o: %s | Data: %s | Aluno ID: %d\n",
                        registro.getId(),
                        registro.getTipo(),
                        registro.getDescricao(),
                        registro.getDataCriacao(),
                        alunoId
                );
                encontrouRegistros = true;
            }
        }

        if (!encontrouRegistros) {
            System.out.println("Nenhum registro vinculado a esta escola.");
        }
    }

    public void deletarVidaAcademica(int escolaId) {
        int registroId = ConsoleUtil.lerInt("ID do registro a deletar: ", 1, Integer.MAX_VALUE);
        VidaAcademica registro = vidaAcademicaDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro n�o encontrado!");
            return;
        }

        int alunoId = registro.getAluno().getId();
        if (!alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId)) {
            System.out.println("Erro: Registro n�o pertence � escola do professor!");
            return;
        }

        vidaAcademicaDAO.deletar(registroId);
        System.out.println("Registro deletado com sucesso!");
    }

    public void exibirTimelineAluno(int escolaId) {
        int alunoId = ConsoleUtil.lerInt("ID do Aluno para hist�rico: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(alunoId);

        if (aluno == null) {
            System.out.println("Erro: Aluno n�o encontrado!");
            return;
        }

        if (!alunoTurmaDAO.existeVinculoAlunoEscola(alunoId, escolaId)) {
            System.out.println("Erro: Voc� n�o tem permiss�o para ver o hist�rico deste aluno!");
            return;
        }

        VidaAcademica[] registros = vidaAcademicaDAO.listarTodos();
        System.out.println("\n=== Hist�rico de " + aluno.getNome() + " ===");

        boolean encontrouRegistros = false;
        for (VidaAcademica registro : registros) {
            if (registro != null && registro.getAluno().getId() == alunoId) {
                String dataFormatada = registro.getDataCriacao().split(" ")[0]; // Formata��o simplificada
                System.out.printf(
                        "%s | %s\n%s\n\n",
                        dataFormatada,
                        registro.getTipo(),
                        registro.getDescricao()
                );
                encontrouRegistros = true;
            }
        }

        if (!encontrouRegistros) {
            System.out.println("Nenhum registro acad�mico encontrado para este aluno.");
        }
    }

    public void gerarRelatorioConselhoTurma(int escolaId) {
        System.out.print("ID da Turma para relat�rio: ");
        int idTurma = ConsoleUtil.lerInt("", 1, Integer.MAX_VALUE);

        // Busca a turma verificando se pertence � escola do professor
        Turma turma = turmaDAO.buscarPorIdEEscola(idTurma, escolaId);

        if (turma == null) {
            System.out.println("Erro: Turma n�o encontrada ou n�o pertence � sua escola!");
            return;
        }

        System.out.println("\n=== Relat�rio de Todos os Registros: Turma " + turma.getNome() + " ===");

        // 1) Coletar IDs �nicos de alunos com registros na turma (usando array e loop manual)
        RegistroProfessorDescricao[] todosRegistrosDesc = registroProfessorDescricaoDAO.listarTodos();
        int[] alunosVistos = new int[todosRegistrosDesc.length];
        int qtAlunos = 0;

        for (RegistroProfessorDescricao descricao : todosRegistrosDesc) {
            if (descricao == null) {
                continue;
            }

            RegistroProfessor registro = registroProfessorDAO.buscarPorId(descricao.getRegistro().getId());
            if (registro != null && registro.getTurma().getId() == idTurma) {
                int idAluno = descricao.getAluno().getId();

                // Verifica se o aluno j� foi adicionado ao array
                boolean jaAdicionado = false;
                for (int i = 0; i < qtAlunos; i++) {
                    if (alunosVistos[i] == idAluno) {
                        jaAdicionado = true;
                        break;
                    }
                }

                if (!jaAdicionado) {
                    alunosVistos[qtAlunos++] = idAluno;
                }
            }
        }

        if (qtAlunos == 0) {
            System.out.println("Nenhum registro encontrado para esta turma.");
            return;
        }

        // 2) Exibir registros por aluno
        for (int i = 0; i < qtAlunos; i++) {
            int idAluno = alunosVistos[i];
            Aluno aluno = alunoDAO.buscarPorId(idAluno);
            System.out.println("\nAluno: " + (aluno != null ? aluno.getNome() : "ID " + idAluno));

            for (RegistroProfessorDescricao descricao : todosRegistrosDesc) {
                if (descricao == null || descricao.getAluno().getId() != idAluno) {
                    continue;
                }

                RegistroProfessor registro = registroProfessorDAO.buscarPorId(descricao.getRegistro().getId());
                if (registro == null || registro.getTurma().getId() != idTurma) {
                    continue;
                }

                System.out.printf("  [%s] Prof. %s (%s): %s%n",
                        descricao.getDataCriacao(),
                        registro.getProfessor().getPessoa().getNome(),
                        registro.getDisciplina(),
                        descricao.getObservacao()
                );
            }
        }
    }

}
