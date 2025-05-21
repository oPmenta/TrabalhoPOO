package model.DAO;

import model.VidaAcademica;
import util.DataUtil;

public class VidaAcademicaDAO {

    private VidaAcademica[] registros = new VidaAcademica[50];
    private int ultimoId = 0;

    public void criar(VidaAcademica vidaAcademica) {
        // Encontra o maior ID existente
        int maiorId = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (registros[i].getId() > maiorId) {
                maiorId = registros[i].getId();
            }
        }

        // Define o novo ID como maiorId + 1
        vidaAcademica.setId(maiorId + 1);

        vidaAcademica.setDataCriacao(DataUtil.getDataAtual());
        registros[ultimoId] = vidaAcademica;
        ultimoId++;
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
}
