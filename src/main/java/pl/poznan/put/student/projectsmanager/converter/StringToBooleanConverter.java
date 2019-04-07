package pl.poznan.put.student.projectsmanager.converter;

import org.springframework.core.convert.converter.Converter;

public class StringToBooleanConverter implements Converter<String, Boolean> {
	@Override
	public Boolean convert(String s) {
		if (s.equals("true") || s.equals("1")) {
			return true;
		} else {
			return false;
		}
	}
}
