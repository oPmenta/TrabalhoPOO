package controller;

import model.DAO.PessoaDAO;
import model.DAO.EscolaDAO;
import model.DAO.UsuarioDAO;
import model.Usuario;
import model.Pessoa;
import model.Escola;
import util.ConsoleUtil;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final PessoaDAO pessoaDAO;
    private final EscolaDAO escolaDAO;

    public UsuarioController(PessoaDAO pessoaDAO, EscolaDAO escolaDAO, UsuarioDAO usuarioDAO) {
        this.pessoaDAO = pessoaDAO;
        this.escolaDAO = escolaDAO;
        this.usuarioDAO = usuarioDAO;
    }

    public void criarUsuario() {
        int pessoaId = ConsoleUtil.lerInt("ID da Pessoa: ", 1, Integer.MAX_VALUE);
        int escolaId = ConsoleUtil.lerInt("ID da Escola: ", 1, Integer.MAX_VALUE);
        String tipo = ConsoleUtil.lerString("Tipo (ADMIN_GERAL/ADMIN_ESCOLA/PROFESSOR): ").toUpperCase();

        Pessoa pessoa = pessoaDAO.buscarPorId(pessoaId);
        Escola escola = escolaDAO.buscarPorId(escolaId);

        if (pessoa != null && escola != null) {
            Usuario usuario = new Usuario(0, pessoa, escola, tipo);
            usuarioDAO.criar(usuario);
            System.out.println("Usuário criado com ID: " + usuario.getId());
        } else {
            System.out.println("Pessoa ou Escola não encontrada!");
        }
    }

    public void criarUsuario(Usuario u) {
        usuarioDAO.criar(u);
        System.out.println("Usuário padrão criado com ID: " + u.getId());
    }

    public Usuario autenticar(String login, String senha) {
        Pessoa pessoa = pessoaDAO.buscarPorLogin(login);
        if (pessoa != null && pessoa.getSenha().equals(senha)) {
            return usuarioDAO.buscarPorId(pessoa.getId());
        }
        return null;
    }

    // Outros métodos (atualizar, listar, deletar) seguem o mesmo padrão...
    public void atualizarUsuario() {
        int id = ConsoleUtil.lerInt("ID do Usuario: ", 1, Integer.MAX_VALUE);
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario != null) {
            int novoPessoaId = ConsoleUtil.lerInt("Novo ID da Pessoa: ", 1, Integer.MAX_VALUE);
            int novoEscolaId = ConsoleUtil.lerInt("Novo ID da Escola: ", 1, Integer.MAX_VALUE);

            Pessoa novaPessoa = pessoaDAO.buscarPorId(novoPessoaId);
            Escola novaEscola = escolaDAO.buscarPorId(novoEscolaId);

            if (novaPessoa != null && novaEscola != null) {
                usuario.setPessoa(novaPessoa);
                usuario.setEscola(novaEscola);
                usuario.setTipo(ConsoleUtil.lerString("Novo Tipo(ADMIN_GERAL/ADMIN_ESCOLA/PROFESSOR): ").toUpperCase());
                usuarioDAO.atualizar(usuario);
                System.out.println("Usuário atualizado!");
            } else {
                System.out.println("Pessoa ou Escola não encontrada!");
            }
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    public void listarUsuario() {
        Usuario[] usuario = usuarioDAO.listarTodos();
        for (Usuario p : usuario) {
            System.out.println("ID: " + p.getId() + " | ID Pessoa: " + p.getPessoa().getId() + " | ID Escola: " + p.getEscola().getId() + " | Tipo: " + p.getTipo());
        }
    }

    public void deletarUsuario() {
        int id = ConsoleUtil.lerInt("ID do Usuario: ", 1, Integer.MAX_VALUE);
        usuarioDAO.deletar(id);
        System.out.println("Usuario deletado!");
    }
}
