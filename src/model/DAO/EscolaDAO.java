package model.DAO;

import model.Escola;
import util.DataUtil;

public class EscolaDAO {

    private Escola[] escolas = new Escola[50];
    private int ultimoId = 0;

    public void criar(Escola escola) {
        escola.setId(++ultimoId);
        escola.setDataCriacao(DataUtil.getDataAtual());
        escolas[ultimoId - 1] = escola;
    }

    public Escola buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (escolas[i].getId() == id) {
                return escolas[i];
            }
        }
        return null;
    }

    public Escola[] listarTodos() {
        Escola[] lista = new Escola[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = escolas[i];
        }
        return lista;
    }

    public void atualizar(Escola escola) {
        escola.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (escolas[i].getId() == escola.getId()) {
                escolas[i] = escola;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (escolas[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                escolas[i] = escolas[i + 1];
            }
            escolas[ultimoId - 1] = null;
            ultimoId--;
        }
    }
}
