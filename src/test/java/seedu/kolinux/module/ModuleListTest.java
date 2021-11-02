package seedu.kolinux.module;

import com.google.gson.JsonArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ModuleListTest {
    private static final double[] workload = new double[] {2,1,1,3,3};
    private static JsonArray semesterData = new JsonArray();
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
            "of Singapore.", "Architectural History of Singapore", "Architecture",
            workload, semesterData);

    private static final String INVALID_MODULE_CODE = "x";
    private static final ModuleDb moduleDb = new ModuleDb();
    private static final ModuleList moduleList = new ModuleList();

    @BeforeAll
    public static void initModuleDb() {
        moduleDb.initModuleDb();
    }

    @BeforeEach
    public void clear() {
        moduleList.clear();
    }

    @Test
    public void storeModuleByCode_newValidModuleCode_validStoredModule() {
        moduleList.addModuleByCode(TEST_MODULE_DETAILS.getModuleCode(), moduleDb);

        int listLength = moduleList.getMyModulesSize();
        ArrayList<ModuleDetails> list = moduleList.getMyModules();

        assertEquals(TEST_MODULE_DETAILS.toString(), list.get(listLength - 1).toString());

    }

    @Test
    public void storeModuleByCode_invalidModuleCode_emptyMyModulesList() {
        moduleList.addModuleByCode(INVALID_MODULE_CODE, moduleDb);

        ArrayList<ModuleDetails> list = moduleList.getMyModules();

        assertArrayEquals(list.toArray(), new ArrayList<ModuleDetails>().toArray());

    }

    @Test
    public void storeModuleByCode_existingValidModuleCode_uniqueModulesInMyModulesList() {
        moduleList.addModuleByCode(TEST_MODULE_DETAILS.getModuleCode(), moduleDb);
        moduleList.addModuleByCode(TEST_MODULE_DETAILS.getModuleCode(), moduleDb);

        int length = moduleList.getMyModulesSize();

        assertEquals(1, length);

    }

    @Test
    public void deleteModuleByCode_existingModuleCode_emptyMyModulesList() {
        moduleList.addModuleByCode(TEST_MODULE_DETAILS.getModuleCode(), moduleDb);

        moduleList.deleteModuleByCode(TEST_MODULE_DETAILS.getModuleCode());
        ArrayList<ModuleDetails> list = moduleList.getMyModules();

        assertArrayEquals(list.toArray(), new ArrayList<ModuleDetails>().toArray());

    }

    @Test
    public void deleteModuleByCode_nonExistentModuleCode_unchangedMyModulesList() {
        moduleList.addModuleByCode(TEST_MODULE_DETAILS.getModuleCode(), moduleDb);
        ArrayList<ModuleDetails> listBeforeDelete = moduleList.getMyModules();

        moduleList.deleteModuleByCode(INVALID_MODULE_CODE);
        ArrayList<ModuleDetails> listAfterDelete = moduleList.getMyModules();

        assertArrayEquals(listAfterDelete.toArray(), listBeforeDelete.toArray());

    }
}
