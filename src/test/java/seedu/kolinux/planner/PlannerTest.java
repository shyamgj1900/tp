package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Lesson;
import seedu.kolinux.module.timetable.Timetable;

import java.sql.Time;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerTest {

    private Planner planner = new Planner();
    private Timetable timetable = new Timetable();

    private static final String[][] VALID_LESSON_ARGUMENTS
            = new String[][]{{"CS2113T", "LEC", "Friday", "1600", "1800"},
                {"CS2113T", "TUT", "Wednesday", "1100", "1200"},
                {"CS2101", "LEC", "Friday", "1000", "1200"}};
    private static final String[][] VALID_EVENT_ARGUMENTS
            = new String[][]{{"Pop Quiz 3", "2021-10-26", "1500", "1515"},
                {"Pop Quiz 4", "2021-10-26", "1530", "2000"},
                {"Pop Quiz 5", "2021-10-26", "2000", "2130"}};
    private static final String[] INVALID_EVENT_DATE_ARGUMENTS
            = new String[]{"Something bad", "16/10/2020", "4pm", "7pm"};
    private static final String[] INVALID_EVENT_FORMAT_ARGUMENTS
            = new String[]{"Something worse", "2021-10-26", "0700"};
    private static final String[] WRONG_TIME_ORDER_ARGUMENTS
            = new String[]{"Go back in time", "2021-04-06", "2000", "1600"};
    private static final String[][] CONFLICTED_TIME_ARGUMENTS
            = new String[][]{{"Do something", "2021-10-26", "1505", "1700"},
                {"Do something", "2021-10-26", "1430", "1505"},
                {"Do something", "2021-10-26", "1400", "1600"},
                {"Do something", "2021-10-26", "1505", "1510"},
                {"Conflict with lecture", "2021-10-22", "1500", "1700"}};
    private static final String VALID_LIST_1
            = "\n15:00 - 15:15 Pop Quiz 3";
    private static final String VALID_LIST_2
            = "\n15:00 - 15:15 Pop Quiz 3\n"
                    + "20:00 - 21:30 Pop Quiz 5";
    private static final String VALID_LIST_3
            = "\n15:00 - 15:15 Pop Quiz 3\n"
                    + "15:05 - 15:10 Do something";
    private static final String VALID_LIST_4
            = "\n15:00 - 17:00 Conflict with lecture\n"
                    + "16:00 - 18:00 CS2113T LEC";
    private static final String VALID_LIST_5
            = "\n15:00 - 17:00 Conflict with lecture (id: 3)";
    private static final String DATETIME_ERROR
            = "Please provide a valid date and time!\n"
                    + "Date: yyyy-mm-dd\n"
                    + "Time: hhMM";
    private static final String FORMAT_ERROR =
            "Please check the format of your input! Format: planner add DESCRIPTION/DATE/START_TIME/END_TIME";
    private static final String TIME_ORDER_ERROR =
            "Please check the format of the time! The end time is earlier than the start time...";
    private static final String TIME_CONFLICT_ERROR =
            "You already have an event ongoing for that time period, do you still want to add? (y/n)";

    @Test
    public void addEvent_validEventInput_eventAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        assertEquals(VALID_LIST_1, planner.listEvents("2021-10-26", false));
        planner.clearEvents();
    }

    @Test
    public void addEvent_invalidEventDateInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_DATE_ARGUMENTS);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(DATETIME_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_invalidEventFormatInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(INVALID_EVENT_FORMAT_ARGUMENTS);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(FORMAT_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_wrongTimeOrderInput_eventNotAdded() {
        try {
            Event invalidEvent = new Event(WRONG_TIME_ORDER_ARGUMENTS);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(TIME_ORDER_ERROR, exception.getMessage());
        }
    }

    @Test
    public void addEvent_startTimeWithinAnotherEvent_eventNotAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        try {
            Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS[0]);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(TIME_CONFLICT_ERROR, exception.getMessage());
        }
        planner.clearEvents();
    }

    @Test
    public void addEvent_endTimeWithinAnotherEvent_eventNotAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        try {
            Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS[1]);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(TIME_CONFLICT_ERROR, exception.getMessage());
        }
        planner.clearEvents();
    }

    @Test
    public void addEvent_eventContainsAnotherEvent_eventNotAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        try {
            Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS[2]);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(TIME_CONFLICT_ERROR, exception.getMessage());
        }
        planner.clearEvents();
    }

    @Test
    public void addEvent_eventWithinAnotherEvent_eventNotAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        try {
            Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS[3]);
            planner.addEvent(invalidEvent, false);
        } catch (KolinuxException exception) {
            assertEquals(TIME_CONFLICT_ERROR, exception.getMessage());
        }
        planner.clearEvents();
    }

    @Test
    public void addEvent_eventWithinAnotherEventButAllowConflict_eventAdded() throws KolinuxException {
        planner.clearEvents();
        Event validEvent = new Event(VALID_EVENT_ARGUMENTS[0]);
        planner.addEvent(validEvent, false);
        Event invalidEvent = new Event(CONFLICTED_TIME_ARGUMENTS[3]);
        planner.addEvent(invalidEvent, true);
        assertEquals(VALID_LIST_3, planner.listEvents("2021-10-26", false));
        planner.clearEvents();
    }

    @Test
    public void addEvent_eventConflictWithTimetableAllowConflict_eventAdded() throws KolinuxException {
        planner.clearEvents();
        timetable.clearTimetable();
        Lesson lesson = new Lesson(VALID_LESSON_ARGUMENTS[0]);
        timetable.addLesson(lesson);
        Event event = new Event(CONFLICTED_TIME_ARGUMENTS[4]);
        planner.addEvent(event, true);
        assertEquals(VALID_LIST_4, planner.listEvents("2021-10-22", false));
        planner.clearEvents();
        timetable.clearTimetable();
    }

    @Test
    public void listEvent_attemptToDeleteLessonFromPlanner_lessonHiddenFromUser() throws KolinuxException {
        planner.clearEvents();
        timetable.clearTimetable();
        Lesson lesson = new Lesson(VALID_LESSON_ARGUMENTS[0]);
        timetable.addLesson(lesson);
        Event event = new Event(CONFLICTED_TIME_ARGUMENTS[4]);
        planner.addEvent(event, true);
        assertEquals(VALID_LIST_5, planner.listEvents("2021-10-22", true));
    }

    @Test
    public void deleteEvent_validIdInput_eventDeleted() throws KolinuxException {
        planner.clearEvents();
        String idToBeDeleted = null;
        for (String[] validInput : VALID_EVENT_ARGUMENTS) {
            Event validEvent = new Event(validInput);
            if (Arrays.equals(validInput, new String[]{"Pop Quiz 4", "2021-10-26", "1530", "2000"})) {
                idToBeDeleted = validEvent.getId();
            }
            planner.addEvent(validEvent, false);
        }
        planner.deleteEvent(idToBeDeleted);
        assertEquals(VALID_LIST_2, planner.listEvents("2021-10-26", false));
        planner.clearEvents();
    }
}
