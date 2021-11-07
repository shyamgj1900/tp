package seedu.kolinux.module;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import seedu.kolinux.commands.ModuleCommand;


public class ModuleListStorageTest {
    private static ModuleDb moduleDb = new ModuleDb();
    private static ModuleList referenceModuleList = new ModuleList();
    private static final String[] REFERENCE_MODULE_CODES = {"CS2113T", "CS2101","MUT3113", "GEH1001"};


    @BeforeAll
    public static void initModuleDb() {
        moduleDb.initModuleDb();
    }

    @BeforeEach
    public void resetReferenceModuleList() {
        referenceModuleList.clear();

        for (String referenceModuleCode : REFERENCE_MODULE_CODES) {
            referenceModuleList.addModuleByCode(referenceModuleCode, moduleDb);
        }
    }

    @Test
    public void moduleListLoadAndStore_listOfModuleDetails_matchingLoadedListAndReferenceModuleList() {
        ModuleListStorage.writeModulesToFile(referenceModuleList);

        ModuleListStorage.setupStorage();

        ModuleList loadedModuleList = ModuleCommand.getModuleList();

        assertArrayEquals(referenceModuleList.getMyModules().toArray(), loadedModuleList.getMyModules().toArray());
    }


}
