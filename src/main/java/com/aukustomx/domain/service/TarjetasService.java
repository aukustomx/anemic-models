package com.aukustomx.domain.service;

import com.aukustomx.domain.model.Tarjeta;
import com.aukustomx.domain.repository.TarjetaRepository;

import java.util.Date;

public class TarjetasService {

    private TarjetaRepository tarjetaRepository;

    public Tarjeta agregarTarjeta(String numeroTarjeta, Date fechaVencimiento, String tipoTarjeta) {

        //Validamos longitud sea 16
        if (numeroTarjeta.trim().length() != 16) {
            throw new RuntimeException("The card number must be exactly 16");
        }

        //Validamos que sean 16 n‘umeros
        if (!numeroTarjeta.trim().matches("[0-9]{16}]")) {
            throw new RuntimeException("The card number accepts only numbers");
        }

        //Validar que la fecha sea mayor a la actual
        if (fechaVencimiento.before(new Date())) {
            throw new RuntimeException("The expiration date must be a future date");
        }

        //Validar que los n‘umeros iniciales sean los de Hey
        var heyCardKey = "1234";
        if (!numeroTarjeta.startsWith(heyCardKey)) {
            throw new RuntimeException("The card number must me a valid Hey Card Key");
        }

        //Cualquier otra validaci‘on
        System.out.println("Apply any other validation");

        //Creamos la entidad Tarjeta
        var tarjeta = new Tarjeta();
        tarjeta.setNumeroTarjeta(numeroTarjeta);
        tarjeta.setFechaVencimiento(fechaVencimiento);
        tarjeta.setTipoTarjeta(tipoTarjeta);

        var savedTarjeta = tarjetaRepository.guardarTarjeta(tarjeta);
        System.out.println("Saved tajeta: " +
                "N‘umero: " + numeroTarjeta +
                ", Fecha vencimiento: " + fechaVencimiento +
                ", Tipo: " + tipoTarjeta);

        return savedTarjeta;
    }
}
