package controller;

import model.DAO.EscolaDAO;
import model.Escola;
import util.ConsoleUtil;

public class EscolaController {

    private final EscolaDAO escolaDAO;

    public EscolaController(EscolaDAO escolaDAO) {
        this.escolaDAO = escolaDAO;
    }

    public void criarEscola() {
        String nome = ConsoleUtil.lerString("Nome: ");
        String cidade = ConsoleUtil.lerString("Cidade: ");
        String telefone = ConsoleUtil.lerString("Telefone: ");
        Escola escola = new Escola(0, nome, cidade, telefone);
        escolaDAO.criar(escola);
        System.out.println("Escola criada com ID: " + escola.getId());
    }

    public void criarEscola(Escola escola) {
        escolaDAO.criar(escola);
        System.out.println("Escola criada com ID: " + escola.getId());
    }

    public void atualizarEscola() {
        int id = ConsoleUtil.lerInt("ID da Escola: ", 1, Integer.MAX_VALUE);
        Escola escola = escolaDAO.buscarPorId(id);
        if (escola != null) {
            escola.setNome(ConsoleUtil.lerString("Novo Nome: "));
            escola.setCidade(ConsoleUtil.lerString("Nova Cidade: "));
            escola.setTelefone(ConsoleUtil.lerString("Novo Telefone: "));
            escolaDAO.atualizar(escola);
            System.out.println("Escola atualizada!");
        } else {
            System.out.println("Escola não encontrada!");
        }
    }

    public void listarEscolas() {
        Escola[] escolas = escolaDAO.listarTodos();
        for (Escola e : escolas) {
            System.out.println("ID: " + e.getId() + " | Nome: " + e.getNome() + " | Cidade: " + e.getCidade());
        }
    }

    public void deletarEscola() {
        int id = ConsoleUtil.lerInt("ID da Escola: ", 1, Integer.MAX_VALUE);
        escolaDAO.deletar(id);
        System.out.println("Escola deletada!");
    }
}
