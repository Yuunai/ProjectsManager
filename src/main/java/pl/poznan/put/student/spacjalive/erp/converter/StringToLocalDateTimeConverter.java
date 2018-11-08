package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
	
	@Override
	public LocalDateTime convert(String s) {
		
		if (s.isEmpty()) {
			return null;
		}
		
		DateTimeFormatter dateTimeFormatter = null;
		LocalDateTime localDateTime = null;
		
		if (Pattern.matches("([0-9]{1,2}\\.){2}[0-9]{1,4} [0-9]{1,2}\\:[0-9]{1,2}", s)) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("d.M.u H:m");
		} else if (Pattern.matches("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}(:[0-9]{1,2})?", s)) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-d H:m");
		} else if (Pattern.matches("[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}T[0-9]{1,2}:[0-9]{1,2}(:[0-9]{1,2})?", s)) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("u-M-dTH:m");
		}
		
		if (dateTimeFormatter != null) {
			localDateTime = LocalDateTime.parse(s, dateTimeFormatter);
			if (localDateTime.getYear() < 2000)
				localDateTime = localDateTime.plusYears(2000);
		}
		
		return localDateTime;
	}
}
