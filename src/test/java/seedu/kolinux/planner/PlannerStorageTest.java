package seedu.kolinux.planner;

import org.junit.jupiter.api.Test;
import seedu.kolinux.exceptions.KolinuxException;
import seedu.kolinux.module.ModuleList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlannerStorageTest {

    private ModuleList moduleList = new ModuleList();
    private Planner planner = new Planner(moduleList);
    private PlannerStorage plannerStorage = new PlannerStorage();

    private static final ArrayList<String> STRINGS_TO_TEST = new ArrayList<>() {
        {
            add("submit tp|2021-11-08|1200|1300");
            add("cg2028 quiz|2021-11-13|1100|1200");
            add("drown in alcohol|2021-11-13|1800|2359");
        }
    };
    private static final ArrayList<String> STRINGS_TO_REWRITE = new ArrayList<>() {
        {
            add("submit tp|2021-11-08|1200|1300");
            add("drown in alcohol|2021-11-13|1800|2359");
        }
    };
    private static final ArrayList<String> CORRUPTED_STRINGS = new ArrayList<>() {
        {
            add("|2021-11-08|1200|1300");
            add("do something fun|2021-11-31|1200|2300");
            add("do something really fun|2021-11-30|0400|1569");
        }
    };
    private static final String PLANNER_INITIALIZED_VALID_LIST_1 = "\n12:00 - 13:00 submit tp";
    private static final String PLANNER_INITIALIZED_VALID_LIST_2 =
            "\n11:00 - 12:00 cg2028 quiz\n"
                    + "18:00 - 23:59 drown in alcohol";

    private static final String PLANNER_CORRUPTED_ERROR =
            "Some of your planner events are corrupted, they will be removed from your planner!";

    @Test
    public void writeAndReadFile_writeStringToFileThenReadBack_sameStringWrittenAndRead() {
        plannerStorage.clearFile();
        plannerStorage.writeFile(STRINGS_TO_TEST.get(0));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(1));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(2));

        ArrayList<String> stringsRead = plannerStorage.readFile();

        assertEquals(STRINGS_TO_TEST, stringsRead);
        plannerStorage.clearFile();
    }

    @Test
    public void rewriteFile_rewriteFileWithNewString_FileIsRewritten() {
        plannerStorage.clearFile();
        plannerStorage.writeFile(STRINGS_TO_TEST.get(0));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(1));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(2));

        plannerStorage.rewriteFile(STRINGS_TO_REWRITE);

        ArrayList<String> stringsRead = plannerStorage.readFile();

        assertEquals(STRINGS_TO_REWRITE, stringsRead);
        plannerStorage.clearFile();
    }

    @Test
    public void initPlanner_initWithValidData_plannerListFilledWithFileData() throws KolinuxException {
        planner.clearEvents();
        plannerStorage.writeFile(STRINGS_TO_TEST.get(0));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(1));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(2));

        planner.initPlanner();

        assertEquals(PLANNER_INITIALIZED_VALID_LIST_1, planner.listEvents("2021-11-08", false));
        assertEquals(PLANNER_INITIALIZED_VALID_LIST_2, planner.listEvents("2021-11-13", false));
        planner.clearEvents();
    }

    @Test
    public void initPlanner_initWithCorruptedData_corruptedLinesRemovedFromFile() throws KolinuxException {
        planner.clearEvents();
        plannerStorage.writeFile(STRINGS_TO_TEST.get(0));
        plannerStorage.writeFile(CORRUPTED_STRINGS.get(0));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(1));
        plannerStorage.writeFile(CORRUPTED_STRINGS.get(1));
        plannerStorage.writeFile(STRINGS_TO_TEST.get(2));
        plannerStorage.writeFile(CORRUPTED_STRINGS.get(2));

        try {
            planner.initPlanner();
        } catch (KolinuxException exception) {
            assertEquals(PLANNER_CORRUPTED_ERROR, exception.getMessage());
        }

        assertEquals(PLANNER_INITIALIZED_VALID_LIST_1, planner.listEvents("2021-11-08", false));
        assertEquals(PLANNER_INITIALIZED_VALID_LIST_2, planner.listEvents("2021-11-13", false));

        ArrayList<String> stringsRead = plannerStorage.readFile();

        assertEquals(STRINGS_TO_TEST, stringsRead);
        planner.clearEvents();
    }
}
