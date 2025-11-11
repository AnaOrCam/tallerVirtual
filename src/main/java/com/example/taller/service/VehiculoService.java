package com.example.taller.service;

import com.example.taller.model.Cliente;
import com.example.taller.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehiculoService {

    private final ArrayList<Vehiculo> listaVehiculos =new ArrayList<>();

    public Vehiculo registrarVehiculo(Vehiculo vehiculo){
        this.listaVehiculos.add(vehiculo);
        return vehiculo;
    }

    public ArrayList<Vehiculo> listarVehiculos(){
        return this.listaVehiculos;
    }

    public Vehiculo buscarVehiculoPorId(Long id){
        Vehiculo vehiculoEncontrado=null;
        for (Vehiculo vehiculo : listaVehiculos){
            if (vehiculo.getId().equals(id)) vehiculoEncontrado=vehiculo;
        }
        return vehiculoEncontrado;
    }

    public Vehiculo borrarVehiculo(Long id){
        Vehiculo vehiculo=buscarVehiculoPorId(id);
        listaVehiculos.remove(vehiculo);
        return vehiculo;
    }

    public Vehiculo editarVehiculo(Long id, Vehiculo vehiculoModificado){
        Vehiculo vehiculoAntiguo=buscarVehiculoPorId(id);
        if (!vehiculoAntiguo.getMatricula().equals(vehiculoModificado.getMatricula())) vehiculoAntiguo.setMatricula(vehiculoModificado.getMatricula());
        if (!vehiculoAntiguo.getMarca().equals(vehiculoModificado.getMarca())) vehiculoAntiguo.setMarca(vehiculoModificado.getMarca());
        if (!vehiculoAntiguo.getModelo().equals(vehiculoModificado.getModelo())) vehiculoAntiguo.setModelo(vehiculoModificado.getModelo());
        return vehiculoModificado;
    }
}
