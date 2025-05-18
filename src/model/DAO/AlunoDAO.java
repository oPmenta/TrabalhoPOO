package model.DAO;

import model.Aluno;
import util.DataUtil;

public class AlunoDAO {

    private Aluno[] alunos = new Aluno[50];
    private int ultimoId = 0;

    public void criar(Aluno aluno) {
        aluno.setId(++ultimoId);
        aluno.setDataCriacao(DataUtil.getDataAtual());
        alunos[ultimoId - 1] = aluno;
    }

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

    public void atualizar(Aluno aluno) {
        aluno.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (alunos[i].getId() == aluno.getId()) {
                alunos[i] = aluno;
                break;
            }
        }
    }

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
}
