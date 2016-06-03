package examenMayo.funcionalidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Fecha {
	static {
		Locale.setDefault(new Locale("es", "ES"));// para que me muestre los
													// meses/d�as en espa�ol
	}


	/**
	 * Obtiene el d�a de la semana seg�n el idioma espa�ol de Espa�a.
	 * 
	 * @param localDate
	 * 
	 * @return D�a de la semana seg�n el idioma espa�ol de Espa�a.
	 */
	public static String getDiaDeLaSemana(TemporalAccessor localDate) {
		return DayOfWeek.from(localDate).getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	/**
	 * Obtiene el mes seg�n el idioma espa�ol de Espa�a.
	 * 
	 * @param localDate
	 * 
	 * @return Mes seg�n el idioma espa�ol de Espa�a.
	 */
	public static String getMes(LocalDate localDate) {
		return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
	}

	/**
	 * @param date
	 * @return
	 */
	public static LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static Date toDate(LocalDate localDate) {
		return java.sql.Date.valueOf(localDate);

	}
}
