package controller;

import model.DAO.AlunoDAO;
import model.Aluno;
import util.ConsoleUtil;

public class AlunoController {

    private final AlunoDAO alunoDAO;

    public AlunoController(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public void criarAluno() {
        String nome = ConsoleUtil.lerString("Nome: ");
        String cpf = ConsoleUtil.lerString("CPF: ");
        String email = ConsoleUtil.lerString("Email: ");
        Aluno aluno = new Aluno(0, nome, cpf, email);
        alunoDAO.criar(aluno);
        System.out.println("Aluno criado com ID: " + aluno.getId());
    }

    // Implementar atualizar, listar, deletar...
    public void atualizarAluno() {
        int id = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        Aluno aluno = alunoDAO.buscarPorId(id);
        if (aluno != null) {
            aluno.setNome(ConsoleUtil.lerString("Novo Nome: "));
            aluno.setCpf(ConsoleUtil.lerString("Novo CPF: "));
            aluno.setEmail(ConsoleUtil.lerString("Nova Email: "));
            alunoDAO.atualizar(aluno);
            System.out.println("Aluno atualizado!");
        } else {
            System.out.println("Aluno nao encontrado!");
        }
    }

    public void listarAluno() {
        Aluno[] aluno = alunoDAO.listarTodos();
        for (Aluno p : aluno) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | CPF: " + p.getCpf() + " | Email: " + p.getEmail());
        }
    }

    public void deletarAluno() {
        int id = ConsoleUtil.lerInt("ID do Aluno: ", 1, Integer.MAX_VALUE);
        alunoDAO.deletar(id);
        System.out.println("Aluno deletado!");
    }
}
