package model.DAO;

import model.Turma;
import util.DataUtil;

public class TurmaDAO {

    private Turma[] turmas = new Turma[10];
    private int ultimoId = 0;
    private int capacidade = 10;
    private EscolaDAO escolaDAO;
    private CursoDAO cursoDAO;

    public TurmaDAO(EscolaDAO escolaDAO, CursoDAO cursoDAO) {
        this.escolaDAO = escolaDAO;
        this.cursoDAO = cursoDAO;
    }

    public void criar(Turma turma) {
        if (ultimoId >= capacidade) {
            aumentarCapacidade();
        }
        turma.setId(++ultimoId);
        turma.setDataCriacao(DataUtil.getDataAtual());
        turmas[ultimoId - 1] = turma;
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

    private void aumentarCapacidade() {
        capacidade *= 2;
        Turma[] novoArray = new Turma[capacidade];
        for (int i = 0; i < turmas.length; i++) {
            novoArray[i] = turmas[i];
        }
        turmas = novoArray;
    }

    public Turma buscarPorIdEEscola(int turmaId, int escolaId) {
        for (Turma turma : turmas) { // Supondo que 'turmas' é o array de Turma
            if (turma != null && turma.getId() == turmaId
                    && turma.getEscola().getId() == escolaId) {
                return turma;
            }
        }
        return null;
    }
}
