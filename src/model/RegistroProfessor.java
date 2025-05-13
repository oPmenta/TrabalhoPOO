package model;

public class RegistroProfessor extends EntidadeBase {
    private Usuario professor;
    private String disciplina;
    private String periodo;
    private Turma turma;
    private String revisaoGeral;

    public RegistroProfessor(int id, Usuario professor, String disciplina, String periodo, Turma turma) {
        setId(id);
        this.professor = professor;
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.turma = turma;
    }

    // Getters e Setters
    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getRevisaoGeral() {
        return revisaoGeral;
    }

    public void setRevisaoGeral(String revisaoGeral) {
        this.revisaoGeral = revisaoGeral;
    }
}