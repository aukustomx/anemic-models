package com.aukustomx;

import com.aukustomx.domain.model.Tarjeta;
import com.aukustomx.domain.service.TarjetasService;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

        var numeroTarjeta = "1234567812345678";
        var fechaVencimiento = new Date();
        var tipoTarjeta = "TDD";

        var tarjetasService = new TarjetasService();
        tarjetasService.agregarTarjeta(numeroTarjeta, fechaVencimiento, tipoTarjeta);

    }
}
