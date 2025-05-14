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
        if (!tipo.matches("SUPERIOR|INTEGRADO|CONCOMITANTE")) {
            System.out.println("Erro: Tipo inválido!");
            return;
        }
        Curso curso = new Curso(0, nome, sigla, tipo);
        cursoDAO.criar(curso);
        System.out.println("Curso criado com ID: " + curso.getId());
    }

    // Implementar atualizar, listar, deletar...
    public void atualizarCurso() {
        int id = ConsoleUtil.lerInt("ID do Curso: ", 1, Integer.MAX_VALUE);
        Curso curso = cursoDAO.buscarPorId(id);
        if (curso != null) {
            // lê nome e sigla normalmente
            curso.setNome(ConsoleUtil.lerString("Novo Nome: "));
            curso.setSigla(ConsoleUtil.lerString("Nova Sigla: "));

            // lê e valida tipo
            String novoTipo = ConsoleUtil
                    .lerString("Novo Tipo (SUPERIOR/INTEGRADO/CONCOMITANTE): ")
                    .toUpperCase();
            if (!novoTipo.matches("SUPERIOR|INTEGRADO|CONCOMITANTE")) {
                System.out.println("Erro: Tipo inválido!");
                return;
            }
            curso.setTipo(novoTipo);

            // persiste alteração
            cursoDAO.atualizar(curso);
            System.out.println("Curso atualizado!");
        } else {
            System.out.println("Curso nao encontrado!");
        }
    }

    public void listarCurso() {
        Curso[] curso = cursoDAO.listarTodos();
        for (Curso p : curso) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Sigla: " + p.getSigla() + " | Tipo: " + p.getTipo());
        }
    }

    public void deletarCurso() {
        int id = ConsoleUtil.lerInt("ID do Curso: ", 1, Integer.MAX_VALUE);
        Curso curso = cursoDAO.buscarPorId(id);
        if (curso != null) {
            cursoDAO.deletar(id);
            System.out.println("Curso deletado!");
        } else {
            System.out.println("Curso nao encontrado!");
        }
    }
}
