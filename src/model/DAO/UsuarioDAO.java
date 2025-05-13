package model.DAO;

import model.Usuario;

public class UsuarioDAO {

    private Usuario[] usuarios = new Usuario[10];
    private int ultimoId = 0;
    private int capacidade = 10;

    public void criar(Usuario usuario) {
        if (ultimoId >= capacidade) {
            aumentarCapacidade();
        }
        usuario.setId(++ultimoId);
        usuarios[ultimoId - 1] = usuario;
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

    private void aumentarCapacidade() {
        capacidade *= 2;
        Usuario[] novoArray = new Usuario[capacidade];
        for (int i = 0; i < usuarios.length; i++) {
            novoArray[i] = usuarios[i];
        }
        usuarios = novoArray;
    }
}
