package model.DAO;

import model.VidaAcademica;
import util.DataUtil;

public class VidaAcademicaDAO {

    private VidaAcademica[] registros = new VidaAcademica[10];
    private int ultimoId = 0;
    private int capacidade = 10;

    public void criar(VidaAcademica vidaAcademica) {
        if (ultimoId >= capacidade) {
            aumentarCapacidade();
        }
        vidaAcademica.setId(++ultimoId);
        vidaAcademica.setDataCriacao(DataUtil.getDataAtual());
        registros[ultimoId - 1] = vidaAcademica;
    }

    public VidaAcademica buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == id) {
                return registros[i];
            }
        }
        return null;
    }

    public VidaAcademica[] listarTodos() {
        VidaAcademica[] lista = new VidaAcademica[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = registros[i];
        }
        return lista;
    }

    public void atualizar(VidaAcademica vidaAcademica) {
        vidaAcademica.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() == vidaAcademica.getId()) {
                registros[i] = vidaAcademica;
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
        VidaAcademica[] novoArray = new VidaAcademica[capacidade];
        for (int i = 0; i < registros.length; i++) {
            novoArray[i] = registros[i];
        }
        registros = novoArray;
    }
}
