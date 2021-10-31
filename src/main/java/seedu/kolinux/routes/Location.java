package seedu.kolinux.routes;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.IOException;
import java.util.ArrayList;

public class Location {
    public static final String FILEPATH_STOP_NAMES = "/busStopNames.txt";

    /**
     * Reads the list of bus stops from the file and returns the contents
     * of the file.
     *
     * @return the bus stop names present in the file
     * @throws KolinuxException if the user command is not in the correct format
     * @throws IOException if the there any IO errors
     */
    public String getBusStopList() throws KolinuxException, IOException {
        ArrayList<String> lines = new ArrayList<>();
        Route.readFromFile(lines, FILEPATH_STOP_NAMES);
        return String.join("\n", lines);
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph A1 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberAOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kr bus terminal":
            return 0;
        case "lt13":
            return 1;
        case "as 5":
            return 2;
        case "com 2":
            return 3;
        case "biz 2":
            return 4;
        case "opp tcoms":
            return 5;
        case "pgp":
            return 6;
        case "kr mrt":
            return 7;
        case "lt27":
            return 8;
        case "uhall":
            return 9;
        case "opp uhc":
            return 10;
        case "yih":
            return 11;
        case "clb":
            return 12;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph A2 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberATwo(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kr bus terminal":
            return 0;
        case "it":
            return 1;
        case "opp yih":
            return 2;
        case "museum":
            return 3;
        case "uhc":
            return 4;
        case "opp uhall":
            return 5;
        case "s 17":
            return 6;
        case "opp kr mrt":
            return 7;
        case "pgpr":
            return 8;
        case "tcoms":
            return 9;
        case "opp hssml":
            return 10;
        case "opp nuss":
            return 11;
        case "com 2":
            return 12;
        case "ventus":
            return 13;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph D1 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberDOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "opp hssml":
            return 0;
        case "opp nuss":
            return 1;
        case "com 2":
            return 2;
        case "ventus":
            return 3;
        case "it":
            return 4;
        case "opp yih":
            return 5;
        case "museum":
            return 6;
        case "utown":
            return 7;
        case "yih":
            return 8;
        case "clb":
            return 9;
        case "lt13":
            return 10;
        case "as 5":
            return 11;
        case "biz 2":
            return 12;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph D2 if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberDTwo(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "pgp":
            return 0;
        case "kr mrt":
            return 1;
        case "lt27":
            return 2;
        case "uhall":
            return 3;
        case "opp uhc":
            return 4;
        case "museum":
            return 5;
        case "utown":
            return 6;
        case "uhc":
            return 7;
        case "opp uhall":
            return 8;
        case "s 17":
            return 9;
        case "opp kr mrt":
            return 10;
        case "pgpr":
            return 11;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph E if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberE(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kent vale":
            return 0;
        case "ea":
            return 1;
        case "sde 3":
            return 2;
        case "it":
            return 3;
        case "opp yih":
            return 4;
        case "utown":
            return 5;
        case "raffles hall":
            return 6;
        default:
            return -1;
        }
    }

    /**
     * Checks the given bus stop name with the corresponding bus stop
     * number in the graph K if any.
     *
     * @param command the bus stop name
     * @return the corresponding bus stop number
     */
    public int getStopNumberK(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "pgp":
            return 0;
        case "kr mrt":
            return 1;
        case "lt27":
            return 2;
        case "uhall":
            return 3;
        case "opp uhc":
            return 4;
        case "yih":
            return 5;
        case "clb":
            return 6;
        case "opp sde 3":
            return 7;
        case "japanese pri school":
            return 8;
        case "kent vale":
            return 9;
        case "museum":
            return 10;
        case "uhc":
            return 11;
        case "opp uhall":
            return 12;
        case "s 17":
            return 13;
        case "opp kr mrt":
            return 14;
        case "pgpr":
            return 15;
        default:
            return -1;
        }
    }
}
