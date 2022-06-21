package com.aukustomx.domain.service;

import com.aukustomx.domain.model.Tarjeta;
import com.aukustomx.restapi.model.TarjetaRequest;

import java.util.Date;

public class TarjetasService {

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

        //Validar que los n‘umeros iniciales sean los de Hey

        //Cualquier otra validaci‘on



        return null;
    }
}
