package model;

public class Curso extends EntidadeBase {
    private String nome;
    private String sigla;
    private String tipo; // SUPERIOR, INTEGRADO, CONCOMITANTE

    public Curso(int id, String nome, String sigla, String tipo) {
        setId(id);
        this.nome = nome;
        this.sigla = sigla;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}