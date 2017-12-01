package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;


public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime>{


    @Override
    public LocalDateTime convert(String s) {
        if(s.isEmpty() || !Pattern.matches("([0-9]{1,2}\\.){2}[0-9]{4}\\ [0-9]{1,2}\\:[0-9]{1,2}", s)) {
            return null;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.u H:m");

        LocalDateTime localDateTime;

        localDateTime = LocalDateTime.parse(s, dateTimeFormatter);

        return localDateTime;
    }
}
