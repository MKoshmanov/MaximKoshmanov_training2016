package com.mkoshmanov.training.transport.daodb.util;

import java.util.Locale;

public class Utils {

	public static String checkLocale(final Locale locale, String sql) {
		String resultSql = sql;
		if (!locale.equals(Locale.ENGLISH)) {
			resultSql = sql.replace("_en", "_ru");
		}
		return resultSql;
	}
}
