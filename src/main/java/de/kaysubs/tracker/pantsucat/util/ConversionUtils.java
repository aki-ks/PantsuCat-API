package de.kaysubs.tracker.pantsucat.util;

import java.time.Instant;
import java.util.Date;

public class ConversionUtils {

    public static Date parseDate(String string) {
        return Date.from(Instant.parse(string));
    }

}
