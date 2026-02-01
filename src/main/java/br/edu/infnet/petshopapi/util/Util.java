package br.edu.infnet.petshopapi.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    private Util() {}

    public static LocalDate dateFormatter(String data) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(data, dateTimeFormatter);
    }
}
