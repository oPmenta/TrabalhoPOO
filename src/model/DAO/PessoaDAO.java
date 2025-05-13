package model.DAO;

import model.Pessoa;
import util.DataUtil;

public class PessoaDAO {
    private Pessoa[] pessoas = new Pessoa[10];
    private int ultimoId = 0;
    private int capacidade = 10;

    public void criar(Pessoa pessoa) {
        if (ultimoId >= capacidade) aumentarCapacidade();
        pessoa.setId(++ultimoId);
        pessoa.setDataCriacao(DataUtil.getDataAtual());
        pessoas[ultimoId - 1] = pessoa;
    }

    public Pessoa buscarPorId(int id) {
        for (int i = 0; i < ultimoId; i++) {
            if (pessoas[i].getId() == id) return pessoas[i];
        }
        return null;
    }

    public Pessoa buscarPorLogin(String login) {
        for (int i = 0; i < ultimoId; i++) {
            if (pessoas[i].getLogin().equals(login)) return pessoas[i];
        }
        return null;
    }

    public Pessoa[] listarTodos() {
        Pessoa[] lista = new Pessoa[ultimoId];
        for (int i = 0; i < ultimoId; i++) {
            lista[i] = pessoas[i];
        }
        return lista;
    }

    public void atualizar(Pessoa pessoa) {
        pessoa.setDataModificacao(DataUtil.getDataAtual());
        for (int i = 0; i < ultimoId; i++) {
            if (pessoas[i].getId() == pessoa.getId()) {
                pessoas[i] = pessoa;
                break;
            }
        }
    }

    public void deletar(int id) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (pessoas[i].getId() == id) {
                posicao = i;
                break;
            }
        }
        
        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                pessoas[i] = pessoas[i + 1];
            }
            pessoas[ultimoId - 1] = null;
            ultimoId--;
        }
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        Pessoa[] novoArray = new Pessoa[capacidade];
        for (int i = 0; i < pessoas.length; i++) {
            novoArray[i] = pessoas[i];
        }
        pessoas = novoArray;
    }
}