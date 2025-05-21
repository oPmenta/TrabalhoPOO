package model.DAO;

import model.Usuario;
import util.DataUtil;

public class UsuarioDAO {

    private Usuario[] usuarios = new Usuario[50];
    private int ultimoId = 0;

    public void criar(Usuario usuario) {
        // Encontra o maior ID existente
        int maiorId = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (usuarios[i].getId() > maiorId) {
                maiorId = usuarios[i].getId();
            }
        }

        // Define o novo ID como maiorId + 1
        usuario.setId(maiorId + 1);

        usuario.setDataCriacao(DataUtil.getDataAtual());
        usuarios[ultimoId] = usuario;
        ultimoId++;
    }

    public Usuario buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (usuarios[i].getId() == id) {
                return usuarios[i];
            }
        }
        return null;
    }

    public Usuario[] listarTodos() {
        Usuario[] lista = new Usuario[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = usuarios[i];
        }
        return lista;
    }

    public void atualizar(Usuario usuario) {
        usuario.setDataModificacao(usuario.getDataModificacao());
        for (int i = 0; i < ultimoId; i++) {
            if (usuarios[i].getId() == usuario.getId()) {
                usuarios[i] = usuario;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (usuarios[i].getId() == id) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                usuarios[i] = usuarios[i + 1];
            }
            usuarios[ultimoId - 1] = null;
            ultimoId--;
        }
    }
}
