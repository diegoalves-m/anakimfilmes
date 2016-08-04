package br.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Diego Alves
 */
public class DateController {

    private static SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfBr = new SimpleDateFormat("dd-MM-yyyy");

    public static Date dataFormat(String data) throws ParseException {
        java.util.Date nData = sdfEua.parse(data);
        return new Date(nData.getTime());
    }

    public static String formatBr(String date) throws ParseException {
        String formBr = sdfBr.format(sdfEua.parse(date));
        return formBr;
    }

    public static String formatEua(String date) throws ParseException {
        String formEua = sdfEua.format(sdfBr.parse(date));
        return formEua;
    }

    public static String inverter(Object data) {
        String dateReturn = sdfBr.format(data);
        return dateReturn;
    }

    // métodos da api nova 
    public static String dataAtual() {
        LocalDate atual = LocalDate.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return atual.format(formatador);
    }

    public static String dataDevolucao(int dias) {
        LocalDate atual = LocalDate.now();
        LocalDate devolucao = atual.plusDays(dias);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return devolucao.format(formatador);
    }

    public static LocalDate stringToDate(String data) {
        String dataString = data;
        String formatador[] = dataString.split("-");
        int dia = Integer.parseInt(formatador[0]);
        int mes = Integer.parseInt(formatador[1]);
        int ano = Integer.parseInt(formatador[2]);
        LocalDate dataPronta = LocalDate.of(ano, mes, dia);
        return dataPronta;
    }
    
    // converter data da api antiga para a nova
    
    public static LocalDate dateToLocalDate(Date data) {
        Instant instant = Instant.ofEpochMilli(data.getTime());
        LocalDate retLocalDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return retLocalDate;
    }

}
