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

        // Verificar se o professor � v�lido
        if (professor == null || !"PROFESSOR".equals(professor.getTipo())) {
            System.out.println("Erro: Professor inv�lido ou n�o encontrado!");
            return;
        }

        // Coletar disciplina
        String disciplina = ConsoleUtil.lerString("Disciplina: ");
        if (disciplina.isBlank()) {
            System.out.println("Erro: Disciplina n�o pode ser vazia!");
            return;
        }

        // Coletar per�odo
        String periodo = ConsoleUtil.lerString("Per�odo (ex: 1� SEMESTRE): ");
        if (periodo.isBlank()) {
            System.out.println("Erro: Per�odo n�o pode ser vazio!");
            return;
        }

        // Coletar ID da turma e validar pertencimento � escola
        int turmaId = ConsoleUtil.lerInt("ID da Turma: ", 1, Integer.MAX_VALUE);
        Turma turma = turmaDAO.buscarPorIdEEscola(turmaId, escolaId);
        if (turma == null) {
            System.out.println("Erro: Turma n�o encontrada ou n�o pertence � escola!");
            return;
        }

        // Revis�o geral (opcional)
        String revisaoGeral = ConsoleUtil.lerString("Revis�o geral da turma (opcional): ");

        // Criar e salvar o registro
        RegistroProfessor registro = new RegistroProfessor(0, professor, disciplina, periodo, turma);
        registro.setRevisaoGeral(revisaoGeral);
        registroProfessorDAO.criar(registro);
        System.out.println("Registro criado com sucesso!");
    }

    // Implementar outros m�todos...
    public void atualizarRegistro(int escolaId) {
        int registroId = ConsoleUtil.lerInt("Digite o ID do registro que deseja atualizar: ", 1, Integer.MAX_VALUE);
        RegistroProfessor registro = registroProfessorDAO.buscarPorId(registroId);

        // Valida��o b�sica
        if (registro == null) {
            System.out.println("Erro: Registro n�o encontrado!");
            return;
        }
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: Registro n�o pertence � sua escola!");
            return;
        }

        // Atualizar disciplina
        String disciplina = ConsoleUtil.lerString("Nova disciplina (deixe em branco para manter atual): ");
        if (!disciplina.isBlank()) {
            registro.setDisciplina(disciplina);
        }

        // Atualizar per�odo
        String periodo = ConsoleUtil.lerString("Novo per�odo (ex: 1� SEMESTRE) (deixe em branco para manter atual): ");
        if (!periodo.isBlank()) {
            registro.setPeriodo(periodo);
        }

        // Atualizar turma (com valida��o de escola)
        String inputTurma = ConsoleUtil.lerString("ID da nova Turma (deixe em branco para manter atual): ");
        if (!inputTurma.isBlank()) {
            try {
                int novaTurmaId = Integer.parseInt(inputTurma);
                Turma novaTurma = turmaDAO.buscarPorIdEEscola(novaTurmaId, escolaId);
                if (novaTurma != null) {
                    registro.setTurma(novaTurma);
                } else {
                    System.out.println("Aviso: Turma inv�lida ou n�o pertence � escola. Turma mantida.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Aviso: ID de turma inv�lido. Turma mantida.");
            }
        }

        // Atualizar revis�o geral
        String revisao = ConsoleUtil.lerString("Nova revis�o geral (deixe em branco para manter atual): ");
        if (!revisao.isBlank()) {
            registro.setRevisaoGeral(revisao);
        }

        // Persistir altera��es
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
                            + " | Per�odo: " + reg.getPeriodo()
                            + " | Turma: " + turma.getNome()
                            + " | Revis�o: " + reg.getRevisaoGeral()
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
            System.out.println("Erro: Registro n�o encontrado!");
            return;
        }

        // Verifica se a turma do registro pertence � escola do professor
        if (registro.getTurma().getEscola().getId() != escolaId) {
            System.out.println("Erro: N�o autorizado a deletar registro de outra escola!");
            return;
        }

        registroProfessorDAO.deletar(registroId);
        System.out.println("Registro deletado com sucesso!");
    }
}
