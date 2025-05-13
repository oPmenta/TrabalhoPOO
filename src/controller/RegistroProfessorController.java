package controller;

import model.DAO.RegistroProfessorDAO;
import model.DAO.UsuarioDAO;
import model.RegistroProfessor;
import model.Usuario;
import util.ConsoleUtil;

public class RegistroProfessorController {

    private final RegistroProfessorDAO registroDAO;
    private final UsuarioDAO usuarioDAO;

    public RegistroProfessorController() {
        this.registroDAO = new RegistroProfessorDAO();
        this.usuarioDAO = new UsuarioDAO();
    }

    public void criarRegistro() {
        int professorId = ConsoleUtil.lerInt("ID do Professor: ", 1, Integer.MAX_VALUE);
        String disciplina = ConsoleUtil.lerString("Disciplina: ");
        String periodo = ConsoleUtil.lerString("Período: ");
        Usuario professor = usuarioDAO.buscarPorId(professorId);

        if (professor != null && professor.getTipo().equals("PROFESSOR")) {
            RegistroProfessor registro = new RegistroProfessor(0, professor, disciplina, periodo, null);
            registroDAO.criar(registro);
            System.out.println("Registro criado com ID: " + registro.getId());
        } else {
            System.out.println("Professor não encontrado ou tipo inválido!");
        }
    }

    // Implementar outros métodos...
}
