package io.sytac.dataharvester.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Util {

    private static final String DEFAULT_ZONE = "Europe/Amsterdam";

    private static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";

    public static ZonedDateTime getShowZonedDateTime(LocalDateTime localDateTime, String country) {
        return localDateTime.atZone(ZoneId.of(getZoneIdFromCountry(country)));
    }

    public static String getDefaultZonedTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.withZoneSameInstant(ZoneId.of(DEFAULT_ZONE)).format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT));
    }

    public static LocalDateTime getLocalDateTimeFromString(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT));
    }

    public static Integer getAge(String dateOfBirth) {
        LocalDate birthDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return calculateAge(birthDate, LocalDate.now());
    }

    public static boolean isXSecondsPassed(Instant startTime, Long seconds) {
        return Instant.now().isAfter(startTime.plusSeconds(seconds));
    }

    private static String getZoneIdFromCountry(String country) {
        switch (country.hashCode()) {
            case 2627:
                if (country.equals("RU")) return "Europe/Moscow";
            case 2564:
                if (country.equals("PT")) return "Europe/Lisbon";
            case 2331:
                if (country.equals("ID")) return "Asia/Jakarta";
            case 2155:
                if (country.equals("CN")) return "Asia/Shanghai";
            case 2142:
                if (country.equals("CA")) return "America/Toronto";
            case 2718:
                if (country.equals("US")) return "America/Los_Angeles";
            default:
                return "Europe/Amsterdam";
        }
    }

    private static Integer calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }
}
