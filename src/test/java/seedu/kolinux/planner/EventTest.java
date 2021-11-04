package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private static final String EVENT_DATA_STRING = "Some data | 2021-10-25|1200| 1500";
    private static final String CORRUPTED_DATA_STRING = "some corrupted data||0x|[]*";
    private static final String EVENT_FROM_DATA = "12:00 - 15:00 Some data";

    private static final String DATETIME_ERROR =
            "Please provide a valid date and time!\n"
                    + "Date: yyyy-mm-dd\n"
                    + "Time: hhMM";

    @Test
    public void constructEvent_eventDataString_eventConstructed() throws KolinuxException {
        Event event = new Event(EVENT_DATA_STRING);
        assertEquals(EVENT_FROM_DATA, event.toString());
        assertEquals("2021-10-25", event.getDate());
    }

    @Test
    public void constructEvent_corruptedEventDataString_exceptionThrown() {
        try {
            new Event(CORRUPTED_DATA_STRING);
        } catch (KolinuxException exception) {
            assertEquals(DATETIME_ERROR, exception.getMessage());
        }
    }
}
