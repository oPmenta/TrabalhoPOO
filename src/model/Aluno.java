package model;

public class Aluno extends Pessoa {
    private String cpf;
    private String email;

    public Aluno(int id, String nome, String cpf, String email) {
        super(id, nome, "aluno" + id, "senha" + id);
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}