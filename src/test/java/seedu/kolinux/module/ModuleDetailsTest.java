package seedu.kolinux.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

public class ModuleDetailsTest {

    private static final JsonReader jsonReader = new JsonReader();
    private static final ModuleDetails TEST_MODULE_DETAILS = new ModuleDetails("AC5001",
            "4", "Design and Environment","This module introduces "
            +
            "the architectural history of Singapore from the pre-colonial era to the contemporary "
            +
            "period from an interdisciplinary perspective. It explores the social and cultural "
            +
            "role of architects, and to how that role has been interpreted and has evolved. It also "
            +
            "offers critical views on the role of architecture in constructing the national identity "
            +
            "of Singapore.","Architectural History of Singapore","Architecture");

    @Test
    public void getModuleCode_testModuleCode_validModuleCode() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();

        assertEquals(modules.get(0).getModuleCode(), TEST_MODULE_DETAILS.getModuleCode());

    }

    @Test
    public void getModuleCredit_testModuleCredit_validModuleCredit() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();

        assertEquals(modules.get(0).getModuleCredit(), TEST_MODULE_DETAILS.getModuleCredit());

    }

    @Test
    public void getModuleDescription_testModuleDescription_validModuleDescription() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();
        if (modules != null) {
            assertNotEquals(modules.get(1).getDescription(), TEST_MODULE_DETAILS.getDescription());
        }
    }

    @Test
    public void getModuleTitle_testModuleTitle_invalidModuleTitle() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();
        if (modules != null) {
            assertNotEquals(modules.get(20).getTitle(), TEST_MODULE_DETAILS.getTitle());
        }
    }

    @Test
    public void getModuleDepartment_testModuleDepartment_validModuleDepartment() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getDepartment(), TEST_MODULE_DETAILS.getDepartment());
        }
    }

    @Test
    public void getModuleFaculty_testModuleFaculty_validModuleFaculty() {
        ArrayList<ModuleDetails> modules = jsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getFaculty(), TEST_MODULE_DETAILS.getFaculty());
        }
    }

}
