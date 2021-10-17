package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Lesson;
import seedu.kolinux.module.timetable.Timetable;
import seedu.kolinux.util.Parser;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlannerLister {

    private String date;

    private static final String INVALID_DATE_MESSAGE = "Please provide a valid date. Format: yyyy-mm-dd";

    private ArrayList<Lesson> lessonsOnDate = new ArrayList<>();

    public PlannerLister(String date) throws KolinuxException {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new KolinuxException(INVALID_DATE_MESSAGE);
        }
        this.date = date;
        getLessonsOnDate();
    }

    /**
    private void convertLessonToEvent(Lesson lesson) {
        String[] arguments = new String[4];
        arguments[0] = lesson.getModuleCode() + " " + lesson.getLessonType();
        arguments[1] = date;
    }*/

    private void getLessonsOnDate() throws KolinuxException {
        int dayAsInteger;
        try {
            dayAsInteger = Parser.findDayFromDate(date);
        } catch (ParseException exception) {
            throw new KolinuxException(INVALID_DATE_MESSAGE);
        }

        String day = Parser.parseDay(dayAsInteger);
        lessonsOnDate = (ArrayList<Lesson>) Timetable.lessonStorage
                .stream()
                .filter(lesson -> day.equalsIgnoreCase(lesson.getDay()))
                .collect(Collectors.toList());
    }
}
