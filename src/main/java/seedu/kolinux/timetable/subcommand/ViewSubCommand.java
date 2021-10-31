package seedu.kolinux.timetable.subcommand;

import static seedu.kolinux.timetable.Timetable.timetableData;
import static seedu.kolinux.timetable.lesson.Lesson.schoolHours;

public class ViewSubCommand extends SubCommand {

    public ViewSubCommand() {

    }

    /**
     * Prints the timetable to the CLI.
     */
    public void viewTimetable() {
        System.out.println(TIMETABLE_HEADER);
        for (int i = 1; i < ROW_SIZE; i++) {
            String time = schoolHours[i - 1] + " - " + schoolHours[i];
            System.out.print("|" + time + getSpaces((TABLE_FIRST_COLUMN_WIDTH - time.length())) + "|");
            for (int j = 1; j < COLUMN_LAST_INDEX; j++) {
                System.out.print(toPrint(timetableData[i][j]));
            }
            System.out.println(toPrint(timetableData[i][COLUMN_LAST_INDEX]));
            System.out.println(TIMETABLE_ROW_DIVIDER);
        }
    }

    /**
     * Formats the string of the lesson which is to be printed in each box of the
     * timetable by adding spaces to the front and back of the lesson entry.
     *
     * @param data The lesson information found in the timetableData
     * @return The formatted string to be printed in each entry of the timetable
     */
    private String toPrint(String data) {
        if (data != null) {
            int spacesFront = (TABLE_COLUMN_WIDTH - data.length()) / 2;
            int spacesBack = (TABLE_COLUMN_WIDTH - data.length()) / 2 + checkOddOrEven(data);
            return getSpaces(spacesFront) + data + getSpaces(spacesBack) + "|";
        }
        return getSpaces(TABLE_COLUMN_WIDTH) + "|";
    }

    /**
     * Adds spaces to format the timetable properly.
     *
     * @param numberOfSpaces The number of spaces to be added in each entry of the timetable
     * @return The string with the spaces to be added to each entry of the timetable
     */
    private String getSpaces(int numberOfSpaces) {
        return String.format("%1$" + numberOfSpaces + "s", "");
    }

    /**
     * Checks if the length of the description for the timetable entry is even or odd,
     * this is done in order to ensure the description is in the middle each box in the timetable.
     * Ensures the format of the timetable is neat.
     *
     * @param lesson The description of the lesson
     * @return The number of extra spaces to be added to the string to ensure proper formatting
     */
    private int checkOddOrEven(String lesson) {
        if (lesson.length() % 2 == 0) {
            return 0;
        }
        return 1;
    }

}
