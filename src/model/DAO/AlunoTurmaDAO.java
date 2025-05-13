package model.DAO;

import model.AlunoTurma;
import model.DAO.*;

public class AlunoTurmaDAO {

    private AlunoTurma[] vinculos = new AlunoTurma[10];
    private int ultimoId = 0;
    private int capacidade = 10;
    private AlunoDAO alunoDAO;
    private TurmaDAO turmaDAO;

    public AlunoTurmaDAO(AlunoDAO alunoDAO, TurmaDAO turmaDAO) {
        this.alunoDAO = alunoDAO;
        this.turmaDAO = turmaDAO;
    }

    public void criar(AlunoTurma vinculo) {
        if (ultimoId >= capacidade) {
            aumentarCapacidade();
        }
        vinculo.setId(++ultimoId);
        vinculos[ultimoId - 1] = vinculo;
    }

    public AlunoTurma[] listarPorTurma(int turmaId) {
        int contador = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (vinculos[i].getTurma().getId() == turmaId) {
                contador++;
            }
        }

        AlunoTurma[] resultado = new AlunoTurma[contador];
        int index = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (vinculos[i].getTurma().getId() == turmaId) {
                resultado[index++] = vinculos[i];
            }
        }
        return resultado;
    }

    public void removerVinculo(int idVinculo) {
        int posicao = -1;
        for (int i = 0; i < ultimoId; i++) {
            if (vinculos[i].getId() == idVinculo) {
                posicao = i;
                break;
            }
        }

        if (posicao != -1) {
            for (int i = posicao; i < ultimoId - 1; i++) {
                vinculos[i] = vinculos[i + 1];
            }
            vinculos[ultimoId - 1] = null;
            ultimoId--;
        }
    }

    private void aumentarCapacidade() {
        capacidade *= 2;
        AlunoTurma[] novoArray = new AlunoTurma[capacidade];
        for (int i = 0; i < vinculos.length; i++) {
            novoArray[i] = vinculos[i];
        }
        vinculos = novoArray;
    }
}
