package model.DAO;

import model.AlunoTurma;
import model.DAO.*;
import model.Turma;
import util.DataUtil;

public class AlunoTurmaDAO {

    private AlunoTurma[] vinculos = new AlunoTurma[50];
    private int ultimoId = 0;

    public void criar(AlunoTurma vinculo) {
        // Encontra o maior ID existente
        int maiorId = 0;
        for (int i = 0; i < ultimoId; i++) {
            if (vinculos[i].getId() > maiorId) {
                maiorId = vinculos[i].getId();
            }
        }

        // Define o novo ID como maiorId + 1
        vinculo.setId(maiorId + 1);

        vinculo.setDataCriacao(DataUtil.getDataAtual());
        vinculos[ultimoId] = vinculo;
        ultimoId++;
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

    public AlunoTurma buscarPorAlunoETurma(int alunoId, int turmaId) {
        for (int i = 0; i < ultimoId; i++) {
            AlunoTurma vinculo = vinculos[i];
            if (vinculo.getAluno().getId() == alunoId
                    && vinculo.getTurma().getId() == turmaId) {
                return vinculo;
            }
        }
        return null;
    }

    public void atualizarTurmaAlunos(int turmaOrigemId, Turma turmaDestino) {
        for (int i = 0; i < ultimoId; i++) {
            if (vinculos[i].getTurma().getId() == turmaOrigemId) {
                vinculos[i].setTurma(turmaDestino);
            }
        }
    }

    public boolean existeVinculoAlunoEscola(int alunoId, int escolaId) {
        for (int i = 0; i < ultimoId; i++) {
            AlunoTurma vinculo = vinculos[i];
            if (vinculo.getAluno().getId() == alunoId
                    && vinculo.getTurma().getEscola().getId() == escolaId) {
                return true;
            }
        }
        return false;
    }
}
