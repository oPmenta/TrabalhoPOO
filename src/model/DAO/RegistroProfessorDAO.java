package model.DAO;

import model.RegistroProfessor;

public class RegistroProfessorDAO {
    private RegistroProfessor[] registros = new RegistroProfessor[10];
    private int ultimoId = 0;
    private int capacidade = 10;

    public void criar(RegistroProfessor registro) {
        if (ultimoId >= capacidade) aumentarCapacidade();
        registro.setId(++ultimoId);
        registros[ultimoId - 1] = registro;
    }

    public RegistroProfessor buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == id) return registros[i];
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
        registro.setDataModificacao(registro.getDataModificacao());
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

    private void aumentarCapacidade() {
        capacidade *= 2;
        RegistroProfessor[] novoArray = new RegistroProfessor[capacidade];
        for (int i = 0; i < registros.length; i++) {
            novoArray[i] = registros[i];
        }
        registros = novoArray;
    }
}