package com.aukustomx;

import com.aukustomx.domain.model.Tarjeta;
import com.aukustomx.domain.service.TarjetasService;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

        var numero = "1234567812345678";
        var vencimiento = new Date();
        var tipo = "TDD";

        var tarjetasService = new TarjetasService();
        tarjetasService.agregarTarjeta(numero, vencimiento, tipo);
    }
}
