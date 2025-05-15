package controller;

import model.DAO.RegistroProfessorDAO;
import model.DAO.UsuarioDAO;
import model.DAO.TurmaDAO; // Adicionado
import model.RegistroProfessor;
import model.Usuario;
import model.Turma; // Adicionado
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

    public void criarRegistro(int escolaId) {
        // Solicitar ID do professor
        int professorId = ConsoleUtil.lerInt("ID do Professor: ", 1, Integer.MAX_VALUE);
        Usuario professor = usuarioDAO.buscarPorId(professorId);

        // Verificar se o professor é válido
        if (professor == null || !"PROFESSOR".equals(professor.getTipo())) {
            System.out.println("Erro: Professor inválido ou não encontrado!");
            return;
        }

        // Coletar disciplina
        String disciplina = ConsoleUtil.lerString("Disciplina: ");
        if (disciplina.isBlank()) {
            System.out.println("Erro: Disciplina não pode ser vazia!");
            return;
        }

        // Coletar período
        String periodo = ConsoleUtil.lerString("Período (ex: 1º SEMESTRE): ");
        if (periodo.isBlank()) {
            System.out.println("Erro: Período não pode ser vazio!");
            return;
        }

        // Coletar ID da turma e validar pertencimento à escola
        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorIdEEscola(turmaId, escolaId);
        if (turma == null) {
            System.out.println("Erro: Turma não encontrada ou não pertence à escola!");
            return;
        }

        // Revisão geral (opcional)
        String revisaoGeral = ConsoleUtil.lerString("Revisão geral da turma (opcional): ");

        // Criar e salvar o registro
        RegistroProfessor registro = new RegistroProfessor(0, professor, disciplina, periodo, turma);
        registro.setRevisaoGeral(revisaoGeral);
        registroProfessorDAO.criar(registro);
        System.out.println("Registro criado com sucesso!");
    }

    // Implementar outros métodos...
    public void atualizarRegistro(int escolaId) {
        int registroId = ConsoleUtil.lerInt("Digite o ID do registro que deseja atualizar: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        // Validação básica
        if (registro == null) {
            System.out.println("Erro: Registro não encontrado!");
            return;
        }
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Registro não pertence à sua escola!");
            return;
        }

        // Atualizar disciplina
        String disciplina = ConsoleUtil.lerString("Nova disciplina (deixe em branco para manter atual): ");
        if (!disciplina.isBlank()) {
            registro.setDisciplina(disciplina);
        }

        // Atualizar período
        String periodo = ConsoleUtil.lerString("Novo período (ex: 1º SEMESTRE) (deixe em branco para manter atual): ");
        if (!periodo.isBlank()) {
            registro.setPeriodo(periodo);
        }

        // Atualizar turma (com validação de escola)
        String inputTurma = ConsoleUtil.lerString("ID da nova Turma (deixe em branco para manter atual): ");
        if (!inputTurma.isBlank()) {
            try {
                int novaTurmaId = Integer.parseInt(inputTurma);
                Turma novaTurma = turmaDAO.buscarPorIdEEscola(novaTurmaId, escolaId);
                if (novaTurma != null) {
                    registro.setTurma(novaTurma);
                } else {
                    System.out.println("Aviso: Turma inválida ou não pertence à escola. Turma mantida.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Aviso: ID de turma inválido. Turma mantida.");
            }
        }

        // Atualizar revisão geral
        String revisao = ConsoleUtil.lerString("Nova revisão geral (deixe em branco para manter atual): ");
        if (!revisao.isBlank()) {
            registro.setRevisaoGeral(revisao);
        }

        // Persistir alterações
        registroProfessorDAO.atualizar(registro);
        System.out.println("Registro atualizado com sucesso!");
    }

    public void listarRegistrosPorEscola(int escolaId) {
        RegistroProfessor[] registros = registroProfessorDAO.listarTodos();
        System.out.println("=== REGISTROS DE PROFESSORES ===");

        for (RegistroProfessor reg : registros) {
            if (reg != null) {
                Turma turma = reg.getTurma();

                if (turma.getEscola().getId() == escolaId) {
                    System.out.println(
                            "ID: " + reg.getId()
                            + " | Professor: " + reg.getProfessor().getPessoa().getNome()
                            + " | Disciplina: " + reg.getDisciplina()
                            + " | Período: " + reg.getPeriodo()
                            + " | Turma: " + turma.getNome()
                            + " | Revisão: " + reg.getRevisaoGeral()
                            + " | Criado: " + reg.getDataCriacao()
                            + " | Atualizado: " + reg.getDataModificacao()
                    );
                }
            }
        }
    }

    public void deletarRegistro(int escolaId) {
        int registroId = ConsoleUtil.lerInt("Digite o ID do registro que deseja deletar: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        if (registro == null) {
            System.out.println("Erro: Registro não encontrado!");
            return;
        }

        // Verifica se a turma do registro pertence à escola do professor
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Não autorizado a deletar registro de outra escola!");
            return;
        }

        registroProfessorDAO.deletar(registroId);
        System.out.println("Registro deletado com sucesso!");
    }
}
