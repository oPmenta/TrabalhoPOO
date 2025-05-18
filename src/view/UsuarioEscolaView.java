/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import util.ConsoleUtil;
import controller.*;

/**
 *
 * @author zaneg
 */
public class UsuarioEscolaView {

    private final UsuarioController usuarioController;
    private final EscolaController escolaController;

    public UsuarioEscolaView(UsuarioController usuarioController, EscolaController escolaController) {
        this.usuarioController = usuarioController;
        this.escolaController = escolaController;
    }

    public void exibirMenu(int escolaId) {
        int opcao;
        do {
            System.out.println("\n\n=== MENU USUARIOS DA ESCOLA ===");
            System.out.println("1 - Vincular Usuario");
            System.out.println("2 - Listar Usuarios");
            System.out.println("3 - Remover Vinculo");
            System.out.println("4 - Voltar");

            opcao = ConsoleUtil.lerInt("Escolha: ", 1, 5);
            switch (opcao) {
                case 1:
                    usuarioController.vincularUsuarioEscola(escolaId);
                    break;
                case 2:
                    usuarioController.listarUsuariosDaEscola(escolaId);
                    break;
                case 3:
                    usuarioController.deletarUsuarioEscola(escolaId);
                    break;
                case 4:
                    System.out.println("Voltando...");
                    break;
            }
        } while (opcao != 4);
    }
}
