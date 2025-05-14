package controller;

import model.DAO.PessoaDAO;
import model.Pessoa;
import util.ConsoleUtil;

public class PessoaController {

    private final PessoaDAO pessoaDAO;

    public PessoaController(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public void criarPessoa() {
        String nome = ConsoleUtil.lerString("Nome: ");
        String login = ConsoleUtil.lerString("Login: ");
        String senha = ConsoleUtil.lerString("Senha: ");
        Pessoa pessoa = new Pessoa(0, nome, login, senha);
        pessoaDAO.criar(pessoa);
        System.out.println("Pessoa criada com ID: " + pessoa.getId());
    }

    public void criarPessoa(Pessoa p) {
        pessoaDAO.criar(p);
        System.out.println("Pessoa padrao criada com ID: " + p.getId());
    }

    public void atualizarPessoa() {
        int id = ConsoleUtil.lerInt("ID da Pessoa: ", 1, Integer.MAX_VALUE);
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        if (pessoa != null) {
            pessoa.setNome(ConsoleUtil.lerString("Novo Nome: "));
            pessoa.setLogin(ConsoleUtil.lerString("Novo Login: "));
            pessoa.setSenha(ConsoleUtil.lerString("Nova Senha: "));
            pessoaDAO.atualizar(pessoa);
            System.out.println("Pessoa atualizada!");
        } else {
            System.out.println("Pessoa nao encontrada!");
        }
    }

    public void listarPessoas() {
        Pessoa[] pessoas = pessoaDAO.listarTodos();
        for (Pessoa p : pessoas) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Login: " + p.getLogin());
        }
    }

    public void deletarPessoa() {
        int id = ConsoleUtil.lerInt("ID da Pessoa: ", 1, Integer.MAX_VALUE);
        pessoaDAO.deletar(id);
        System.out.println("Pessoa deletada!");
    }

    public Pessoa buscarPorLogin(String login) {
        return pessoaDAO.buscarPorLogin(login);
    }
}
