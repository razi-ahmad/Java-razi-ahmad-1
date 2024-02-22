package io.sytac.dataharvester.util;

import java.util.Objects;

public class UserUtil {

    public static final String NAME_SPLITTER = ",";

    public static String getFullName(String firstName, String lastName) {
        return firstName + " " + Objects.toString(lastName, "");
    }

    public static String getFirstPersonOfCast(String cast) {
        if (Objects.isNull(cast)) {
            return "";
        }
        return cast.split(NAME_SPLITTER)[0];
    }
}
