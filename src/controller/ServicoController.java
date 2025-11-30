package controller;

import model.Servico;
import service.ServicoService;
import exceptions.RegraNegocioException;

public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController() {
        this.servicoService = ServicoService.getInstancia();
    }

    public Servico buscarPorId(int id) throws RegraNegocioException {
        return servicoService.buscarPorId(id);
    }

    public void listarServicos() {
        servicoService.listarServicos();
    }

    /** Método para listar IDs e Nomes dos serviços . */
    public void listarIDsEServicos() {
        System.out.println("\n--- SERVIÇOS DISPONÍVEIS ---");
        boolean encontrado = false;
        for (Servico s : servicoService.getRepository().listar()) {

            System.out.println(s);
            encontrado = true;
        }
        if (!encontrado) {
            System.out.println("Nenhum serviço cadastrado.");
        }
        System.out.println("----------------------------------------");
    }

    public ServicoService getService() {
        return servicoService;
    }
}