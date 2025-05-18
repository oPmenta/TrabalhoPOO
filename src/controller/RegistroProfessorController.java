package controller;

import model.DAO.RegistroProfessorDAO;
import model.DAO.UsuarioDAO;
import model.DAO.TurmaDAO;
import model.RegistroProfessor;
import model.Usuario;
import model.Turma;
import util.ConsoleUtil;

public class RegistroProfessorController {

    private final RegistroProfessorDAO registroProfessorDAO;
    private final UsuarioDAO usuarioDAO;
    private final TurmaDAO turmaDAO;

    public RegistroProfessorController(RegistroProfessorDAO registroProfessorDAO, UsuarioDAO usuarioDAO, TurmaDAO turmaDAO) {
        this.registroProfessorDAO = registroProfessorDAO;
        this.usuarioDAO = usuarioDAO;
        this.turmaDAO = turmaDAO;
    }

    int cont = 0;

    public void criarRegistro(int escolaId) {
        int professorId = ConsoleUtil.lerInt("ID do Professor: ", 1, Integer.MAX_VALUE);

        Usuario professor = usuarioDAO.buscarPorId(professorId);

        if (professor == null || !"PROFESSOR".equals(professor.getTipo())) {
            System.out.println("Erro: Professor invalido ou nao encontrado!");
            return;
        }

        String disciplina = ConsoleUtil.lerString("Disciplina: ");
        if (disciplina.isBlank()) {
            System.out.println("Erro: Disciplina nao pode ser vazia!");
            return;
        }

        String periodo = ConsoleUtil.lerString("Periodo (ex: 1º SEMESTRE): ");
        if (periodo.isBlank()) {
            System.out.println("Erro: Periodo nao pode ser vazio!");
            return;
        }

        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorIdEEscola(turmaId, escolaId);
        if (turma == null) {
            System.out.println("Erro: Turma nao encontrada ou nao pertence a escola!");
            return;
        }

        String revisaoGeral = ConsoleUtil.lerString("Revisao geral da turma (opcional): ");

        RegistroProfessor registro = new RegistroProfessor(0, professor, disciplina, periodo, turma);
        registro.setRevisaoGeral(revisaoGeral);
        registroProfessorDAO.criar(registro);
        System.out.println("Registro criado com sucesso!");
    }

    public void atualizarRegistro(int escolaId) {
        int registroId = ConsoleUtil.lerInt("Digite o ID do registro que deseja atualizar: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro nao encontrado!");
            return;
        }
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Registro nao pertence a sua escola!");
            return;
        }

        String disciplina = ConsoleUtil.lerString("Nova disciplina (deixe em branco para manter atual): ");
        if (!disciplina.isBlank()) {
            registro.setDisciplina(disciplina);
        }

        String periodo = ConsoleUtil.lerString("Novo periodo (ex: 1º SEMESTRE) (deixe em branco para manter atual): ");
        if (!periodo.isBlank()) {
            registro.setPeriodo(periodo);
        }

        String inputTurma = ConsoleUtil.lerString("ID da nova Turma (deixe em branco para manter atual): ");
        if (!inputTurma.isBlank()) {
            try {
                int novaTurmaId = Integer.parseInt(inputTurma);
                Turma novaTurma = turmaDAO.buscarPorIdEEscola(novaTurmaId, escolaId);
                if (novaTurma != null) {
                    registro.setTurma(novaTurma);
                } else {
                    System.out.println("Aviso: Turma invalida ou nao pertence a escola. Turma mantida.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Aviso: ID de turma invalido. Turma mantida.");
            }
        }

        String revisao = ConsoleUtil.lerString("Nova revisao geral (deixe em branco para manter atual): ");
        if (!revisao.isBlank()) {
            registro.setRevisaoGeral(revisao);
        }

        registroProfessorDAO.atualizar(registro);
        System.out.println("Registro atualizado com sucesso!");
        cont = 1;
    }

    public void listarRegistrosPorEscola(int escolaId) {
        RegistroProfessor[] registros = registroProfessorDAO.listarTodos();
        System.out.println("=== REGISTROS DE PROFESSORES ===");

        for (RegistroProfessor reg : registros) {
            if (reg != null) {
                Turma turma = reg.getTurma();

                if (turma.getEscola().getId() == escolaId) {
                    System.out.print(
                            "ID: " + reg.getId()
                            + " | Professor: " + reg.getProfessor().getPessoa().getNome()
                            + " | Disciplina: " + reg.getDisciplina()
                            + " | Período: " + reg.getPeriodo()
                            + " | Turma: " + turma.getNome()
                            + " | Revisão: " + reg.getRevisaoGeral()
                            + " | Criado: " + reg.getDataCriacao());
                    if (cont == 0) {
                        System.out.print(" | Atualizado: " + reg.getDataCriacao());
                    } else {
                        System.out.print(" | Atualizado: " + reg.getDataModificacao());
                    }
                }
            }
        }
    }

    public void deletarRegistro(int escolaId) {
        int registroId = ConsoleUtil.lerInt("Digite o ID do registro que deseja deletar: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro nao encontrado!");
            return;
        }

        // Verifica se a turma do registro pertence à escola do professor
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Nao autorizado a deletar registro de outra escola!");
            return;
        }

        registroProfessorDAO.deletar(registroId);
        System.out.println("Registro deletado com sucesso!");
    }
}
