package model;

public class Usuario extends EntidadeBase {
    private Pessoa pessoa;
    private Escola escola;
    private String tipo; // ADMIN_GERAL, ADMIN_ESCOLA, PROFESSOR, etc.

    public Usuario(int id, Pessoa pessoa, Escola escola, String tipo) {
        setId(id);
        this.pessoa = pessoa;
        this.escola = escola;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}