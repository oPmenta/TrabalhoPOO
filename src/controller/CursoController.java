package controller;

import model.DAO.CursoDAO;
import model.Curso;
import util.ConsoleUtil;

public class CursoController {

    private final CursoDAO cursoDAO;

    public CursoController(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public void criarCurso() {
        String nome = ConsoleUtil.lerString("Nome: ");
        String sigla = ConsoleUtil.lerString("Sigla: ");
        String tipo = ConsoleUtil.lerString("Tipo (SUPERIOR/INTEGRADO/CONCOMITANTE): ").toUpperCase();
        Curso curso = new Curso(0, nome, sigla, tipo);
        cursoDAO.criar(curso);
        System.out.println("Curso criado com ID: " + curso.getId());
    }

    // Implementar atualizar, listar, deletar...
}
