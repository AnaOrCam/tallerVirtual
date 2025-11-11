package com.example.taller.controller;

import com.example.taller.model.Cliente;
import com.example.taller.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service){
        this.service=service;
    }

    @GetMapping()
    public String listarCliente(Model model){
        model.addAttribute("clientes",service.listarClientes());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String registrarCliente(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes",service.listarClientes());
        return "clientes-reg";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        service.registrarCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/{id}")
    public String buscar(@PathVariable Long id, Model model) {
        Cliente cliente = service.buscarClientePorId(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
        }
        return "cliente-buscado";
    }

    @GetMapping("/borrar/{id}")
    public String borrarCliente(@PathVariable Long id, RedirectAttributes redirectAttrs){
        Cliente cliente=service.buscarClientePorId(id);
        service.borrarClientes(id);
        redirectAttrs.addFlashAttribute("mensaje", "Cliente borrado: " + cliente.getNombre());
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model){
        Cliente cliente=service.buscarClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes-editar";
    }

    @PostMapping("/guardarcambios")
    public String guardarCambios(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttrs){
        service.editarCliente(cliente.getId(), cliente);
        redirectAttrs.addFlashAttribute("mensaje", "Cliente modificado: " + cliente.getNombre());
        return "redirect:/clientes";
    }
}
