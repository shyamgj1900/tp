package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private static final String EVENT_DATA_STRING = "Some data | 2021-10-25|1200| 1500";
    private static final String EVENT_FROM_DATA = "12:00 - 15:00 Some data";

    @Test
    public void constructEvent_eventDataString_eventConstructed() throws KolinuxException {
        Event event = new Event(EVENT_DATA_STRING);
        assertEquals(EVENT_FROM_DATA, event.toString());
        assertEquals("2021-10-25", event.getDate());
    }
}
