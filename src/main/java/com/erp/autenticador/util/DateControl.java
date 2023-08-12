package com.erp.autenticador.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateControl {

    public static final String DATA_SEPARADA_POR_BARRA = "dd/MM/yyyy";
    public static final String DATA_E_HORA_SEPARADA_POR_BARRA_COM_SEGUNDOS = "dd/MM/yyyy HH:mm:sss";
    public static final String DATA_E_HORA_SEPARADA_POR_BARRA = "dd/MM/yyyy HH:mm";
    public static final String HORA_SEPARADA_POR_DOIS_PONTO= "HH:mm";

    public static Integer getDiaSemanaPelaData(LocalDate data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(data.atStartOfDay(ZoneId.of("Brazil/East")).toInstant()));
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return getDia(day);
    }
    public static int getDia(int dia){
        switch (dia){
            case 1: return 6;
            case 2: return 0;
            case 3: return 1;
            case 4: return 2;
            case 5: return 3;
            case 6: return 4;
            case 7: return 5;
        }
        return 9;
    }
    public static String getDescricaoDia(int dia){
        switch (dia){
            case 0: return "SEGUNDA";
            case 1: return "TERCA";
            case 2: return "QUARTA";
            case 3: return "QUINTA";
            case 4: return "SEXTA";
            case 5: return "SABADO";
            case 6: return "DOMINGO";
        }
        return null;
    }
    public static LocalDate getHoje(){
        return LocalDate.now(Clock.system(ZoneId.of("Brazil/East")));
    }
    public static LocalDateTime getHojeComHora(){
        return LocalDateTime.now(Clock.system(ZoneId.of("Brazil/East")));
    }

    public static LocalDate stringToLocalDate(String data) {
        if(data==null)return null;
        return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate dateToLocalDate(Date dia) {
        return Instant.ofEpochMilli(dia.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public static LocalTime dateToLocalTime(Date dia) {
        return Instant.ofEpochMilli(dia.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }
}
