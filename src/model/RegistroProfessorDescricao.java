package model;

public class RegistroProfessorDescricao extends EntidadeBase {
    private RegistroProfessor registro;
    private Aluno aluno;
    private String observacao;

    public RegistroProfessorDescricao(int id, RegistroProfessor registro, Aluno aluno, String observacao) {
        setId(id);
        this.registro = registro;
        this.aluno = aluno;
        this.observacao = observacao;
    }

    // Getters e Setters
    public RegistroProfessor getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroProfessor registro) {
        this.registro = registro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}