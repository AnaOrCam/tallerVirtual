package com.example.taller.service;

import com.example.taller.model.Reparacion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReparacionService {
    private final ArrayList<Reparacion> listaReparaciones=new ArrayList<>();

    public Reparacion registrarReparacion(Reparacion reparacion){
        listaReparaciones.add(reparacion);
        return reparacion;
    }

    public ArrayList<Reparacion> listarReparaciones(){
        return this.listaReparaciones;
    }

    public Reparacion buscarReparacionPorId(Long id){
        Reparacion reparacionAux=null;
        for(Reparacion reparacion : listaReparaciones){
            if(reparacion.getId().equals(id)) reparacionAux=reparacion;
        }
        return reparacionAux;
    }

    public Reparacion borrarReparacion(Long id){
        Reparacion reparacionAux=buscarReparacionPorId(id);
        listaReparaciones.remove(reparacionAux);
        return reparacionAux;
    }

    public Reparacion editarReparacion(Long id, Reparacion reparacionModificada){
        Reparacion reparacionAntigua =buscarReparacionPorId(id);
        if (!reparacionAntigua.getDescripcion().equals(reparacionModificada.getDescripcion())) reparacionAntigua.setDescripcion(reparacionModificada.getDescripcion());
        if (!reparacionAntigua.getFechaEntrada().equals(reparacionModificada.getFechaEntrada())) reparacionAntigua.setFechaEntrada(reparacionModificada.getFechaEntrada());
        if (!reparacionAntigua.getFechaSalida().equals(reparacionModificada.getFechaSalida())) reparacionAntigua.setFechaSalida(reparacionModificada.getFechaSalida());
        return reparacionModificada;
    }
}
