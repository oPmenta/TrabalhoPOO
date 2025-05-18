package model.DAO;

import model.Curso;
import util.DataUtil;

public class CursoDAO {

    private Curso[] cursos = new Curso[50];
    private int ultimoId = 0;

    public void criar(Curso curso) {
        curso.setId(++ultimoId);
        curso.setDataCriacao(DataUtil.getDataAtual());
        cursos[ultimoId - 1] = curso;
    }

    public Curso buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (cursos[i].getId() == id) {
                return cursos[i];
            }
        }
        return null;
    }

    public Curso[] listarTodos() {
        Curso[] lista = new Curso[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = cursos[i];
        }
        return lista;
    }

    public void atualizar(Curso curso) {
        curso.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (cursos[i].getId() == curso.getId()) {
                cursos[i] = curso;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (cursos[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                cursos[i] = cursos[i + 1];
            }
            cursos[ultimoId - 1] = null;
            ultimoId--;
        }
    }
}
