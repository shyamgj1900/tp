package seedu.kolinux.planner;

import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;
import seedu.kolinux.timetable.lesson.Lesson;
import seedu.kolinux.timetable.Timetable;
import seedu.kolinux.util.Parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/** Represents the methods needed to sync with the module list to fetch lessons and exams data. */
public class ModuleSyncer {

    private String date;

    private static final String INVALID_DATE_MESSAGE = "Please provide a valid date. Format: yyyy-mm-dd";
    private static final String FATAL_ERROR = "Fatal error occurred, please restart Kolinux.";

    private ArrayList<Lesson> lessonsOnDate = new ArrayList<>();
    private ArrayList<Event> lessonsAndExamsAsEventsOnDate = new ArrayList<>();

    /**
     * The list of Lesson and the corresponding Event on a specified date will be populated upon the
     * construction of this object. Events will then be created using the exam data, and added to the
     * list.
     *
     * @param date Date to get the list of lessons and exams
     */
    public ModuleSyncer(ModuleList moduleList, String date) {
        this.date = date;
        getLessonsOnDate();
        convertLessonListToEventList();
        getExamsOnDate(moduleList, date);
    }

    public ArrayList<Event> getLessonsAndExamsAsEventsOnDate() {
        return lessonsAndExamsAsEventsOnDate;
    }

    /**
     * Constructs an ExamsGetter to get the exam dates and times of the modules in the module list. The
     * exams occurring on the date specified will be constructed as events and will be added to
     * convertedLessonsOnDate. The module list will always be updated with the latest version.
     *
     * @param moduleList Module list stored by the user
     * @param date Date specified
     */
    private void getExamsOnDate(ModuleList moduleList, String date) {
        ExamsGetter examsGetter = new ExamsGetter(moduleList);
        ArrayList<Event> examsOnDate = examsGetter.getExams();
        for (Event exam : examsOnDate) {
            if (exam.getDate().equals(date)) {
                lessonsAndExamsAsEventsOnDate.add(exam);
            }
        }
    }

    /**
     * Populates the lessonsOnDate array list by the list of lessons occurring on a date, specified by
     * the date attribute of this class.
     */
    private void getLessonsOnDate() {
        int dayAsInteger;
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

    /**
     * Constructs an Event object from a Lesson object.
     *
     * @param lesson Lesson to be converted
     * @return Event corresponding to the lesson
     */
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
            // Should not be executed since the necessary checks of the arguments were done before constructing
            // the Lesson object.
            System.out.println(FATAL_ERROR);
        }
        return event;
    }

    /**
     * Constructs list of Event objects from the list of Lesson objects in lessonsOnDate.
     */
    private void convertLessonListToEventList() {
        lessonsAndExamsAsEventsOnDate = (ArrayList<Event>) lessonsOnDate
                .stream()
                .map(lesson -> convertLessonToEvent(lesson))
                .collect(Collectors.toList());
    }
}
