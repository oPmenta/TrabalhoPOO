package model.DAO;

import model.Aluno;
import util.DataUtil;

public class AlunoDAO {

    private Aluno[] alunos = new Aluno[10];
    private int ultimoId = 0;
    private int capacidade = 10;

    // CREATE
    public void criar(Aluno aluno) {
        if (ultimoId >= capacidade) {
            aumentarCapacidade();
        }
        aluno.setId(++ultimoId);
        aluno.setDataCriacao(DataUtil.getDataAtual());
        alunos[ultimoId - 1] = aluno;
    }

    // READ
    public Aluno buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (alunos[i].getId() == id) {
                return alunos[i];
            }
        }
        return null;
    }

    public Aluno[] listarTodos() {
        Aluno[] lista = new Aluno[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = alunos[i];
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Aluno aluno) {
        aluno.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (alunos[i].getId() == aluno.getId()) {
                alunos[i] = aluno;
                break;
            }
        }
    }

    // DELETE
    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (alunos[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                alunos[i] = alunos[i + 1];
            }
            alunos[ultimoId - 1] = null;
            ultimoId--;
        }
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        Aluno[] novoArray = new Aluno[capacidade];
        for (int i = 0; i < alunos.length; i++) {
            novoArray[i] = alunos[i];
        }
        alunos = novoArray;
    }
}
