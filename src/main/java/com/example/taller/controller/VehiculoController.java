package com.example.taller.controller;

import com.example.taller.model.Cliente;
import com.example.taller.model.Vehiculo;
import com.example.taller.service.ClienteService;
import com.example.taller.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService service;
    private final ClienteService clienteService;

    public VehiculoController(VehiculoService service, ClienteService clienteService){
        this.service=service;
        this.clienteService=clienteService;
    }

    @GetMapping()
    public String listarVehiculos(Model model){
        model.addAttribute("vehiculos",service.listarVehiculos());
        return "vehiculos";
    }

    @GetMapping("/nuevo")
    public String registrarVehiculo(Model model){
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("clientes",clienteService.listarClientes());
        return "vehiculos-reg";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Vehiculo vehiculo) {
        if (vehiculo.getCliente() != null) {
            Cliente cliente = clienteService.buscarClientePorId(vehiculo.getCliente().getId());
            vehiculo.setCliente(cliente);
            service.registrarVehiculo(vehiculo);
        }
        return "redirect:/vehiculos";
    }

    @GetMapping("/{id}")
    public String buscar(@PathVariable Long id, Model model) {
        Vehiculo vehiculo = service.buscarVehiculoPorId(id);
        if (vehiculo != null) {
            model.addAttribute("vehiculo", vehiculo);
        }
        return "vehiculo-buscado";
    }

    @GetMapping("/borrar/{id}")
    public String borrarVehiculo(@PathVariable Long id, RedirectAttributes redirectAttrs){
        Vehiculo vehiculo=service.buscarVehiculoPorId(id);
        service.borrarVehiculo(id);
        redirectAttrs.addFlashAttribute("mensaje", "Vehiculo borrado: " + vehiculo.getMarca()+" "+vehiculo.getModelo());
        return "redirect:/vehiculos";
    }

    @GetMapping("/editar/{id}")
    public String editarVehiculo(@PathVariable Long id, Model model){
        Vehiculo vehiculo=service.buscarVehiculoPorId(id);
        model.addAttribute("vehiculo", vehiculo);
        return "vehiculos-editar";
    }

    @PostMapping("/guardarcambios")
    public String guardarCambios(@ModelAttribute Vehiculo vehiculo, RedirectAttributes redirectAttrs){
        service.editarVehiculo(vehiculo.getId(), vehiculo);
        redirectAttrs.addFlashAttribute("mensaje", "Vehiculo modificado: " + vehiculo.getMarca()+" "+vehiculo.getModelo());
        return "redirect:/vehiculos";
    }

}
