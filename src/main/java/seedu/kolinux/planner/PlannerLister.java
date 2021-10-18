package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.timetable.Lesson;
import seedu.kolinux.module.timetable.Timetable;
import seedu.kolinux.util.Parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlannerLister {

    private String date;

    private static final String INVALID_DATE_MESSAGE = "Please provide a valid date. Format: yyyy-mm-dd";
    private static final String FATAL_ERROR = "Fatal error occurred, please restart Kolinux.";

    private ArrayList<Lesson> lessonsOnDate = new ArrayList<>();
    private ArrayList<Event> convertedLessonsOnDate = new ArrayList<>();

    public PlannerLister(String date) {
        this.date = date;
        getLessonsOnDate();
        convertLessonListToEventList();
    }

    public ArrayList<Event> getConvertedLessonsOnDate() {
        return convertedLessonsOnDate;
    }

    private void getLessonsOnDate() {
        int dayAsInteger = 0;
        try {
            dayAsInteger = Parser.findDayFromDate(date);
        } catch (ParseException exception) {
            System.out.println(INVALID_DATE_MESSAGE);
            return;
        }

        assert ((dayAsInteger >= 1) && (dayAsInteger <= 7));
        String day = Parser.parseDay(dayAsInteger);
        lessonsOnDate = (ArrayList<Lesson>) Timetable.lessonStorage
                .stream()
                .filter(lesson -> day.equalsIgnoreCase(lesson.getDay()))
                .collect(Collectors.toList());
    }

    private Event convertLessonToEvent(Lesson lesson) {
        String[] arguments = new String[4];
        arguments[0] = lesson.getModuleCode() + " " + lesson.getLessonType();
        arguments[1] = date;
        arguments[2] = lesson.getStartTime();
        arguments[3] = lesson.getEndTime();
        Event event = null;

        try {
            event = new Event(arguments);
            event.setIsLesson();
        } catch (KolinuxException exception) {
            System.out.println(FATAL_ERROR);
        }
        return event;
    }

    private void convertLessonListToEventList() {
        convertedLessonsOnDate = (ArrayList<Event>) lessonsOnDate
                .stream()
                .map(lesson -> convertLessonToEvent(lesson))
                .collect(Collectors.toList());
    }
}
