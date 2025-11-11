package com.example.taller.controller;

import com.example.taller.model.Reparacion;
import com.example.taller.model.Vehiculo;
import com.example.taller.service.ReparacionService;
import com.example.taller.service.VehiculoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reparaciones")
public class ReparacionController {

    private final ReparacionService reparacionService;
    private final VehiculoService vehiculoService;

    public ReparacionController(ReparacionService reparacionService, VehiculoService vehiculoService){
        this.reparacionService=reparacionService;
        this.vehiculoService=vehiculoService;
    }

    @GetMapping()
    public String listarReparaciones(Model model){
        model.addAttribute("reparaciones",reparacionService.listarReparaciones());
        return "reparaciones";
    }

    @GetMapping("/nuevo")
    public String registrarReparacion(Model model){
        model.addAttribute("reparacion", new Reparacion());
        model.addAttribute("vehiculos",vehiculoService.listarVehiculos());
        return "reparacion-reg";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reparacion reparacion) {
        if (reparacion.getVehiculo() != null) {
            Vehiculo vehiculo = vehiculoService.buscarVehiculoPorId(reparacion.getVehiculo().getId());
            reparacion.setVehiculo(vehiculo);
            reparacionService.registrarReparacion(reparacion);
        }
        return "redirect:/reparaciones";
    }

    @GetMapping("/{id}")
    public String buscar(@PathVariable Long id, Model model) {
        Reparacion reparacion = reparacionService.buscarReparacionPorId(id);
        if (reparacion != null) {
            model.addAttribute("reparacion", reparacion);
        }
        return "reparacion-buscada";
    }

    @GetMapping("/borrar/{id}")
    public String borrarReparacion(@PathVariable Long id, RedirectAttributes redirectAttrs){
        Reparacion reparacion=reparacionService.buscarReparacionPorId(id);
        reparacionService.borrarReparacion(id);
        redirectAttrs.addFlashAttribute("mensaje", "Reparacion borrada: " + reparacion.getVehiculo().getMarca()+" "+reparacion.getVehiculo().getModelo()+" "+reparacion.getFechaEntrada());
        return "redirect:/reparaciones";
    }

    @GetMapping("/editar/{id}")
    public String editarReaparacion(@PathVariable Long id, Model model){
        Reparacion reparacion=reparacionService.buscarReparacionPorId(id);
        model.addAttribute("reparacion", reparacion);
        return "reparaciones-editar";
    }

    @PostMapping("/guardarcambios")
    public String guardarCambios(@ModelAttribute Reparacion reparacion, RedirectAttributes redirectAttrs){
        reparacionService.editarReparacion(reparacion.getId(), reparacion);
        redirectAttrs.addFlashAttribute("mensaje", "Reparación modificada: ID: " + reparacion.getId()+" ("+reparacion.getFechaEntrada()+')');
        return "redirect:/reparaciones";
    }

}
