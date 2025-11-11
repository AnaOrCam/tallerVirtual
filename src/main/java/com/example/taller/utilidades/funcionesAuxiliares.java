package com.example.taller.utilidades;

import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class funcionesAuxiliares {

    public static LocalDate conversorFechas(String fechaString){
        boolean formatoCorrecto=false;
        do{
            if (formatoFecha(fechaString)){
                formatoCorrecto=true;
            }else{
                System.out.println("El formato introducido no es válido. " +
                        "Por favor introduzca la fecha en formato dd/mm/aaaa");
            }
        }while(!formatoCorrecto);
        DateTimeFormatter formatoEs=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaString,formatoEs);
    }

    public static boolean formatoFecha(String fecha){
        if (fecha.length()==10 && fecha.contains("/")) return true;
        return false;
    }
}
