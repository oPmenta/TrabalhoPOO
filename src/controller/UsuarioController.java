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
        String tipo = ConsoleUtil.lerString("Tipo (ADMIN_GERAL/ADMIN_ESCOLA/PROFESSOR/FUNCIONARIO): ").toUpperCase();

        Pessoa pessoa = pessoaDAO.buscarPorId(pessoaId);
        Escola escola = escolaDAO.buscarPorId(escolaId);

        if (!tipo.matches("^(ADMIN_GERAL|ADMIN_ESCOLA|PROFESSOR|FUNCIONARIO)$")) {
            System.out.println("Erro: Tipo invalido!");
            return;
        } else {
            if (pessoa != null && escola != null) {
                Usuario usuario = new Usuario(0, pessoa, escola, tipo);
                usuarioDAO.criar(usuario);
                System.out.println("Usuario criado com ID: " + usuario.getId());
            } else {
                System.out.println("Pessoa ou Escola nao encontrada!");
            }
        }
    }

    public void criarUsuario(Usuario u) {
        usuarioDAO.criar(u);
        // debug do pré-cadastro
        // System.out.println("Usuario padrao criado com ID: " + u.getId());
    }

    public Usuario autenticar(String login, String senha) {
        Pessoa pessoa = pessoaDAO.buscarPorLogin(login);
        if (pessoa != null && pessoa.getSenha().equals(senha)) {
            return usuarioDAO.buscarPorId(pessoa.getId());
        }
        return null;
    }

    public void atualizarUsuario() {
        int id = ConsoleUtil.lerInt("ID do Usuario: ", 1, Integer.MAX_VALUE);
        Usuario usuario = usuarioDAO.buscarPorId(id);
        if (usuario != null) {
            int novoPessoaId = ConsoleUtil.lerInt("Novo ID da Pessoa: ", 1, Integer.MAX_VALUE);
            int novoEscolaId = ConsoleUtil.lerInt("Novo ID da Escola: ", 1, Integer.MAX_VALUE);
            String novoTipo = ConsoleUtil.lerString("Novo Tipo(ADMIN_GERAL/ADMIN_ESCOLA/PROFESSOR/FUNCIONARIO): ").toUpperCase();

            Pessoa novaPessoa = pessoaDAO.buscarPorId(novoPessoaId);
            Escola novaEscola = escolaDAO.buscarPorId(novoEscolaId);

            if (!novoTipo.matches("ADMIN_GERAL|ADMIN_ESCOLA|PROFESSOR|FUNCIONARIO")) {
                System.out.println("Erro: Tipo invalido!");
                return;
            } else {
                if (novaPessoa != null && novaEscola != null) {
                    usuario.setPessoa(novaPessoa);
                    usuario.setEscola(novaEscola);
                    usuario.setTipo(novoTipo);
                    usuarioDAO.atualizar(usuario);
                    System.out.println("Usuario atualizado!");
                } else {
                    System.out.println("Pessoa ou Escola nao encontrada!");
                }
            }
        } else {
            System.out.println("Usuario nao encontrado!");
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

    public void vincularUsuarioEscola(int escolaId) {
        int pessoaId = ConsoleUtil.lerInt("ID da Pessoa: ", 1, Integer.MAX_VALUE);

        // Buscar Pessoa e Escola
        Pessoa pessoa = pessoaDAO.buscarPorId(pessoaId);
        Escola escola = escolaDAO.buscarPorId(escolaId);

        if (pessoa == null || escola == null) {
            System.out.println("Pessoa ou Escola nao encontrada!");
            return;
        }

        // Validar tipo
        String tipo;
        do {
            tipo = ConsoleUtil.lerString("Tipo (ADMIN_ESCOLA/PROFESSOR/FUNCIONARIO): ").toUpperCase();
        } while (!tipo.matches("ADMIN_ESCOLA|PROFESSOR|FUNCIONARIO"));

        // Verificar se ja existe vinculo
        Usuario usuarioExistente = null;
        for (Usuario u : usuarioDAO.listarTodos()) {
            if (u.getPessoa().getId() == pessoaId) {
                usuarioExistente = u;
                break;
            }
        }

        if (usuarioExistente != null) {
            // Atualizar vi­nculo existente
            usuarioExistente.setEscola(escola);
            usuarioExistente.setTipo(tipo);
            usuarioDAO.atualizar(usuarioExistente);
            System.out.println("Vi­nculo atualizado!");
        } else {
            // Criar novo vinculo
            int novoId = gerarNovoId();
            Usuario novoUsuario = new Usuario(novoId, pessoa, escola, tipo);
            usuarioDAO.criar(novoUsuario);
            System.out.println("Usuario vinculado! ID: " + novoId);
        }
    }

    public void listarUsuariosDaEscola(int escolaId) {
        System.out.println("\n=== USUARIOS VINCULADOS DA ESCOLA ===");
        Usuario[] usuarios = usuarioDAO.listarTodos();
        for (Usuario usuario : usuarios) {
            if (usuario.getEscola().getId() == escolaId) {
                System.out.println(
                        "ID: " + usuario.getId()
                        + " | Nome: " + usuario.getPessoa().getNome()
                        + " | Escola: " + usuario.getEscola().getNome()
                        + " | Tipo: " + usuario.getTipo()
                );
            }
        }
    }

    public void deletarUsuarioEscola(int escolaId) {
        int id = ConsoleUtil.lerInt("ID do Usuario para remover vi­nculo: ", 1, Integer.MAX_VALUE);
        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (usuario != null && usuario.getEscola().getId() == escolaId) {
            usuarioDAO.deletar(id);
            System.out.println("Vi­nculo removido com sucesso!");
        } else {
            System.out.println("Usuario nao encontrado ou nao vinculado a esta escola!");
        }
    }

    private int gerarNovoId() {
        int maxId = 0;
        for (Usuario u : usuarioDAO.listarTodos()) {
            if (u.getId() > maxId) {
                maxId = u.getId();
            }
        }
        return maxId + 1;
    }
}
