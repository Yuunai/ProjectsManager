package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime>{


    @Override
    public LocalDateTime convert(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.u H:m");

        LocalDateTime localDateTime = LocalDateTime.parse(s, dateTimeFormatter);

        return localDateTime;
    }
}
