package seedu.kolinux.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class ModuleDbTest {

    private static final ModuleDb moduleDb = new ModuleDb();
    private static final double[] workload = new double[] {2,1,1,3,3};
    private static JsonArray semesterData = new JsonArray();
    private static JsonObject attributes = new JsonObject();
    private final ModuleDetails testModule = new ModuleDetails("CS2101", "4", "Computing",
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
            "Effective Communication for Computing Professionals",
            "Center for Engl Lang Comms", workload, semesterData, attributes);

    private final String invalidModuleCode = "x";


    @BeforeAll
    public static void setUp() {
        moduleDb.initModuleDb();
        attributes.addProperty("su", true);
    }

    @Test
    public void getModuleInfo_validModuleCode_moduleDetails() {
        ModuleDetails mod = moduleDb.getModuleInfo(testModule.getModuleCode());
        assertEquals(mod.toString(), testModule.toString());


    }

    @Test
    public void getModuleInfo_invalidModuleCode_nullModuleDetails() {
        ModuleDetails mod = moduleDb.getModuleInfo(invalidModuleCode);
        assertNull(mod);
    }
}