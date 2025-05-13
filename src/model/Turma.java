package model;

public class Turma extends EntidadeBase {
    private String nome;
    private Curso curso;
    private Escola escola;
    private String periodo; // Ex: 2024-1
    private boolean ativa;

    public Turma(int id, String nome, Curso curso, Escola escola, String periodo) {
        setId(id);
        this.nome = nome;
        this.curso = curso;
        this.escola = escola;
        this.periodo = periodo;
        this.ativa = true;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}