package io.sytac.dataharvester.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUtilTest {
    @Test
    void test_get_full_name_when_first_and_last_name_is_not_null() {
        Assertions.assertEquals("Abc Def", UserUtil.getFullName("Abc", "Def"));
    }

    @Test
    void test_get_full_name_when_last_name_is_null() {
        Assertions.assertEquals("foo ", UserUtil.getFullName("foo", null));
    }

    @Test
    void test_get_first_person_of_cast_when_present() {
        Assertions.assertEquals("Kuby Farner",UserUtil.getFirstPersonOfCast("Kuby Farner, Rakota Cotus, Uhivia Homabia, Elbert Jsai, Xixton Faoth, Qubecca Ketz"));
    }

    @Test
    void test_get_first_person_of_cast_when_not_present() {
        Assertions.assertEquals("",UserUtil.getFirstPersonOfCast(null));
    }
}