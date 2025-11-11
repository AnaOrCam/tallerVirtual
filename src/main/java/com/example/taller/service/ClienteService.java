package com.example.taller.service;

import com.example.taller.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService {

    private final ArrayList<Cliente> listaClientes =new ArrayList<>();


    public Cliente registrarCliente(Cliente cliente){
        this.listaClientes.add(cliente);
        return cliente;
    }

    public ArrayList<Cliente> listarClientes(){
        return this.listaClientes;
    }

    public Cliente borrarClientes(Long id){
        Cliente clienteABorrar=buscarClientePorId(id);
        listaClientes.remove(clienteABorrar);
        return clienteABorrar;
    }

    public Cliente buscarClientePorId(Long id){
        Cliente clienteAux=null;
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId().equals(id)) clienteAux= listaClientes.get(i);
        }
        return clienteAux;
    }

    public Cliente editarCliente(Long id, Cliente clienteModificado){
        Cliente clienteAntiguo=buscarClientePorId(id);
        if (!clienteAntiguo.getNombre().equals(clienteModificado.getNombre())) clienteAntiguo.setNombre(clienteModificado.getNombre());
        if (!clienteAntiguo.getEmail().equals(clienteModificado.getEmail())) clienteAntiguo.setEmail(clienteModificado.getEmail());
        if (!clienteAntiguo.getTelefono().equals(clienteModificado.getTelefono())) clienteAntiguo.setTelefono(clienteModificado.getTelefono());
        return clienteModificado;
    }

}
