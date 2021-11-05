package seedu.kolinux.module;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleDetailsTest {

    private static final JsonReader jsonReader = new JsonReader();
    private static final double[] workload = new double[] {2,1,1,3,3};
    private static JsonArray semesterData = new JsonArray();
    private static JsonObject attributes = new JsonObject();
    private static final ModuleDetails TEST_MODULE_DETAILS = new ModuleDetails("AC5001",
            "4", "Design and Environment", "This module introduces "
            +
            "the architectural history of Singapore from the pre-colonial era to the contemporary "
            +
            "period from an interdisciplinary perspective. It explores the social and cultural "
            +
            "role of architects, and to how that role has been interpreted and has evolved. It also "
            +
            "offers critical views on the role of architecture in constructing the national identity "
            +
            "of Singapore.", "Architectural History of Singapore", "Architecture", workload,
            semesterData, attributes);

    private static final String TEST_MODULE_DETAILS_CODE = "AC5001";
    private static final String TEST_MODULE_DETAILS_CREDIT = "4";
    private static final String TEST_MODULE_DETAILS_FACULTY = "Design and Environment";
    private static final String TEST_MODULE_DETAILS_DESCRIPTION = "This module introduces "
            +
            "the architectural history of Singapore from the pre-colonial era to the contemporary "
            +
            "period from an interdisciplinary perspective. It explores the social and cultural "
            +
            "role of architects, and to how that role has been interpreted and has evolved. It also "
            +
            "offers critical views on the role of architecture in constructing the national identity "
            +
            "of Singapore.";
    private static final String TEST_MODULE_DETAILS_TITLE = "Architectural History of Singapore";
    private static final String TEST_MODULE_DETAILS_DEPARTMENT = "Architecture";

    @Test
    public void getModuleCode_testModule_correctModuleCode() {
        assertEquals(TEST_MODULE_DETAILS_CODE, TEST_MODULE_DETAILS.getModuleCode());
    }

    @Test
    public void getModuleCredit_testModule_correctModuleCredit() {
        assertEquals(TEST_MODULE_DETAILS_CREDIT, TEST_MODULE_DETAILS.getModuleCredit());
    }

    @Test
    public void getModuleDescription_testModule_correctModuleDescription() {
        assertEquals(TEST_MODULE_DETAILS_DESCRIPTION, TEST_MODULE_DETAILS.getDescription());

    }

    @Test
    public void getModuleTitle_testModule_correctModuleTitle() {
        assertEquals(TEST_MODULE_DETAILS_TITLE, TEST_MODULE_DETAILS.getTitle());
    }

    @Test
    public void getModuleDepartment_testModule_correctModuleDepartment() {
        assertEquals(TEST_MODULE_DETAILS_DEPARTMENT, TEST_MODULE_DETAILS.getDepartment());
    }

    @Test
    public void getModuleFaculty_testModule_correctModuleFaculty() {
        assertEquals(TEST_MODULE_DETAILS_FACULTY, TEST_MODULE_DETAILS.getFaculty());
    }

}
