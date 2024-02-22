package io.sytac.dataharvester.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

class UtilTest {
    public static final String DEFAULT_ZONE = "Europe/Amsterdam";

    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss.SSS";
    @Test
    void test_get_show_zoned_date_time_when_default() {
        Assertions.assertEquals(ZonedDateTime.of(2024, 1, 1, 1, 1, 0, 0, ZoneId.of("Europe/Amsterdam")), Util.getShowZonedDateTime(LocalDateTime.of(2024, 1, 1, 1, 1, 0), "PK"));
    }

    @Test
    void test_get_show_zoned_date_time_when_us() {
        Assertions.assertEquals(ZonedDateTime.of(2024, 1, 1, 1, 1, 0, 0, ZoneId.of("America/Los_Angeles")), Util.getShowZonedDateTime(LocalDateTime.of(2024, 1, 1, 1, 1, 0), "US"));
    }

    @Test
    void test_get_local_date_time_from_string() {
        Assertions.assertEquals(LocalDateTime.of(2023, 2, 27, 3, 20, 17, 111000000), Util.getLocalDateTimeFromString("27-02-2023 03:20:17.111"));
    }

    @Test
    void test_get_default_zoned_time() {
        Assertions.assertEquals(ZonedDateTime.of(2023,2,27,4,20,17,111000000,ZoneId.of(DEFAULT_ZONE)).format(DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT)), Util.getDefaultZonedTime(Util.getShowZonedDateTime(Util.getLocalDateTimeFromString("27-02-2023 03:20:17.111"), "PT")));
    }

    @Test
    void test_get_age() {
        Assertions.assertEquals(34, Util.getAge("01/01/1990"));
    }

    @Test
    void test_when_20_seconds_passed(){
        Assertions.assertEquals(Boolean.TRUE,Util.isXSecondsPassed(Instant.now().minusSeconds(20), 20L));
    }

    @Test
    void test_when_20_seconds_not_passed(){
        Assertions.assertEquals(Boolean.FALSE,Util.isXSecondsPassed(Instant.now().minusSeconds(19), 20L));
    }


}