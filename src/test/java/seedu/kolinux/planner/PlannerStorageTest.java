package seedu.kolinux.planner;

import org.junit.jupiter.api.BeforeAll;
import seedu.kolinux.exceptions.KolinuxException;

public class PlannerStorageTest {

    private static Planner planner = new Planner();

    @BeforeAll
    public static void initPlanner() throws KolinuxException {
        planner.initPlanner();
    }
}
