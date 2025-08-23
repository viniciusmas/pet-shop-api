package br.edu.infnet.petshopapi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    private Util() {}

    public static LocalDateTime dateFormatter(String data) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(data, dateTimeFormatter);
    }
}
