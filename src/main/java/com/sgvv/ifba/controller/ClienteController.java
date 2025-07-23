package com.sgvv.ifba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sgvv.ifba.service.ClienteService;
import com.sgvv.ifba.entity.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listarClientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "listarClientes";
    }
    @GetMapping("/buscarClientePorCpf")
    public String buscarClientePorCpf(@RequestParam String cpf, Model model) {
        model.addAttribute("clientes", clienteService.buscarClientePorCpf(cpf));
        return "listarClientes";
    }
    @GetMapping("/cadastrarCliente")
    public String cadastrarCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastrarCliente";
    }
    @GetMapping("/atualizarCliente")
    public String atualizarCliente(@RequestParam Long id, Model model) {
        Cliente cliente = clienteService.buscarClientePorCpf(id.toString()).stream().findFirst().
        orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + id));
        model.addAttribute("cliente", cliente);
        return "atualizarCliente";
    }
    @GetMapping("/deletarCliente")
    public String deletarCliente(@RequestParam Long id) {
        clienteService.deletarCliente(id);
        return "redirect:/listarClientes";
    }
    @PostMapping("/SalvarCliente")
    public String salvarCliente(@RequestBody Cliente cliente) {
        clienteService.cadastrarCliente(cliente);
        return "redirect:/listarClientes";
    }
    

}