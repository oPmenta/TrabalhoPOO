package model.DAO;

import model.RegistroProfessor;
import util.DataUtil;

public class RegistroProfessorDAO {

    private RegistroProfessor[] registros = new RegistroProfessor[50];
    private int ultimoId = 0;

    public void criar(RegistroProfessor registro) {
        registro.setId(++ultimoId);
        registro.setDataCriacao(DataUtil.getDataAtual());
        registros[ultimoId - 1] = registro;
    }

    public RegistroProfessor buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == id) {
                return registros[i];
            }
        }
        return null;
    }

    public RegistroProfessor[] listarTodos() {
        RegistroProfessor[] lista = new RegistroProfessor[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = registros[i];
        }
        return lista;
    }

    public void atualizar(RegistroProfessor registro) {
        registro.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == registro.getId()) {
                registros[i] = registro;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                registros[i] = registros[i + 1];
            }
            registros[ultimoId - 1] = null;
            ultimoId--;
        }
    }
}
