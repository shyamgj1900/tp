package seedu.kolinux.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void findDayFromDate_validDates_daysReturned() throws ParseException {
        int day = Parser.findDayFromDate("2021-10-25");
        assertEquals(2, day);
        day = Parser.findDayFromDate("2021-10-26");
        assertEquals(3, day);
        day = Parser.findDayFromDate("2021-10-27");
        assertEquals(4, day);
        day = Parser.findDayFromDate("2021-10-28");
        assertEquals(5, day);
        day = Parser.findDayFromDate("2021-10-29");
        assertEquals(6, day);
        day = Parser.findDayFromDate("2021-10-30");
        assertEquals(7, day);
        day = Parser.findDayFromDate("2021-10-31");
        assertEquals(1, day);
    }
}
