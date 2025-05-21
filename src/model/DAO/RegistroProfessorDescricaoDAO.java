package model.DAO;

import model.RegistroProfessorDescricao;
import util.DataUtil;

public class RegistroProfessorDescricaoDAO {

    private RegistroProfessorDescricao[] registros = new RegistroProfessorDescricao[50];
    private int ultimoId = 0;

    public void criar(RegistroProfessorDescricao registro) {
        // Encontra o maior ID existente
        int maiorId = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() > maiorId) {
                maiorId = registros[i].getId();
            }
        }

        // Define o novo ID como maiorId + 1
        registro.setId(maiorId + 1);

        registro.setDataCriacao(DataUtil.getDataAtual());
        registros[ultimoId] = registro;
        ultimoId++;
    }

    public RegistroProfessorDescricao buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == id) {
                return registros[i];
            }
        }
        return null;
    }

    public RegistroProfessorDescricao[] listarTodos() {
        RegistroProfessorDescricao[] lista = new RegistroProfessorDescricao[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = registros[i];
        }
        return lista;
    }

    public void atualizar(RegistroProfessorDescricao registro) {
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
