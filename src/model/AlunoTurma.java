package model;

public class AlunoTurma extends EntidadeBase {
    private Aluno aluno;
    private Turma turma;

    public AlunoTurma(int id, Aluno aluno, Turma turma) {
        setId(id);
        this.aluno = aluno;
        this.turma = turma;
    }

    // Getters e Setters
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}