package seedu.kolinux.module;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ModuleDbTest {

    private static final ModuleDetails TEST_MODULE = new ModuleDetails("CS2101", "4", "Computing",
            "This module aims to equip students with the skills needed to communicate technical "
                    +
                    "information to technical and nontechnical audiences, and to create comprehensible software "
                    +
                    "documentation. A student-centric approach will\n"
                    +
                    "be adopted to encourage independent and collaborative learning while engaging students in "
                    +
                    "team-based projects. Students will learn interpersonal and intercultural\n"
                    +
                    "communication skills as well as hone their oral and written communication skills. "
                    +
                    "Assessment modes include a variety of oral and written communication tasks such as reports, "
                    +
                    "software guides, oral presentations, software demonstrations and project blogs.",
            "Effective Communication for Computing Professionals", "Center for Engl Lang Comms");

    private static final String INVALID_MODULE_CODE = "x";


    @BeforeAll
    public static void setUp() {
        ModuleDb.initModuleDb();
    }

    @Test
    public void getModuleInfo_validModuleCode_moduleDetails() {
        ModuleDetails mod = ModuleDb.getModuleInfo(TEST_MODULE.getModuleCode());
        if (mod != null) {
            assertEquals(mod.toString(), TEST_MODULE.toString());
        }
    }

    @Test
    public void getModuleInfo_invalidModuleCode_nullModuleDetails() {
        ModuleDetails mod = ModuleDb.getModuleInfo(INVALID_MODULE_CODE);
        assertNull(mod);
    }
}