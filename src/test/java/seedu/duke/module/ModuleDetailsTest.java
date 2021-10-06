package seedu.duke.module;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

public class ModuleDetailsTest {

    private static final ModuleDetails MODULE_DETAILS = new ModuleDetails("AC5001",
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
    public void testModuleCode() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getModuleCode(), MODULE_DETAILS.getModuleCode());
        }
    }

    @Test
    public void testModuleCredit() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getModuleCredit(), MODULE_DETAILS.getModuleCredit());
        }
    }

    @Test
    public void testModuleDescription() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getDescription(), MODULE_DETAILS.getDescription());
        }
    }

    @Test
    public void testModuleTitle() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getTitle(), MODULE_DETAILS.getTitle());
        }
    }

    @Test
    public void testModuleDepartment() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getDepartment(), MODULE_DETAILS.getDepartment());
        }
    }

    @Test
    public void testModuleFaculty() {
        ArrayList<ModuleDetails> modules = JsonReader.readJsonData();
        if (modules != null) {
            assertEquals(modules.get(0).getFaculty(), MODULE_DETAILS.getFaculty());
        }
    }

}
