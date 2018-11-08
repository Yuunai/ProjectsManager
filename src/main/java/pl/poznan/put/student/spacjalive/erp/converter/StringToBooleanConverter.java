package pl.poznan.put.student.spacjalive.erp.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToBooleanConverter implements Converter<String, Boolean> {
	@Override
	public Boolean convert(String s) {
		if (s == "true" || s == "1") {
			return true;
		} else {
			return false;
		}
	}
}
