package model.DAO;

import model.Turma;
import util.DataUtil;

public class TurmaDAO {

    private Turma[] turmas = new Turma[50];
    private int ultimoId = 0;

    public void criar(Turma turma) {
        // Encontra o maior ID existente
        int maiorId = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (turmas[i].getId() > maiorId) {
                maiorId = turmas[i].getId();
            }
        }

        // Define o novo ID como maiorId + 1
        turma.setId(maiorId + 1);

        turma.setDataCriacao(DataUtil.getDataAtual());
        turmas[ultimoId] = turma;
        ultimoId++;
    }

    public Turma buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (turmas[i].getId() == id) {
                return turmas[i];
            }
        }
        return null;
    }

    public Turma[] listarTodos() {
        Turma[] lista = new Turma[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = turmas[i];
        }
        return lista;
    }

    public void atualizar(Turma turma) {
        turma.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (turmas[i].getId() == turma.getId()) {
                turmas[i] = turma;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (turmas[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                turmas[i] = turmas[i + 1];
            }
            turmas[ultimoId - 1] = null;
            ultimoId--;
        }
    }

    public Turma buscarPorIdEEscola(int turmaId, int escolaId) {
        for (Turma turma : turmas) {
            if (turma != null && turma.getId() == turmaId
                    && turma.getEscola().getId() == escolaId) {
                return turma;
            }
        }
        return null;
    }
}
