package seedu.kolinux.module;


import seedu.kolinux.commands.ModuleCommand;
import seedu.kolinux.exceptions.KolinuxException;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import static seedu.kolinux.commands.ModuleCommand.STORE_SUBCOMMAND;
import static seedu.kolinux.commands.ModuleCommand.SET_GRADE_SUBCOMMAND;


/**
 * ModuleListStorage class facilitates loading tasks from the file and saving tasks in the file.
 */
public class ModuleListStorage {
    private static final String STORAGE_PATH = "./data/moduleList.txt";
    private static final int FILE_ERROR_EXIT_CODE = 0;

    /**
     * Loads stored modules from file.
     *
     * @param storedModules File at STORAGE_PATH
     * @throws FileNotFoundException If the file at STORAGE_PATH does not exist
     */
    private static void loadStoredModules(File storedModules)
            throws FileNotFoundException {
        Scanner scanner = new Scanner(storedModules);
        try {
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println(STORAGE_PATH + " is empty");
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parsedArguments = line.split("/");

            try {
                ModuleCommand storeCommand = new ModuleCommand(STORE_SUBCOMMAND, parsedArguments);
                storeCommand.executeCommand();
                ModuleCommand gradeCommand = new ModuleCommand(SET_GRADE_SUBCOMMAND, parsedArguments);
                gradeCommand.executeCommand();

            } catch (KolinuxException exception) {
                System.out.println(STORAGE_PATH + " is corrupted. Please delete moduleList.txt and restart "
                        + "the program.");
                System.exit(FILE_ERROR_EXIT_CODE);
            }
        }
    }


    /**
     * Loads pre-existing modules from file at STORAGE_PATH or creates a new file if it does not exist.
     */
    public static void setupStorage() {
        try {
            File storedModules = new File(STORAGE_PATH);
            if (!storedModules.createNewFile()) {
                System.out.println("Data loaded from " + STORAGE_PATH);
                loadStoredModules(storedModules);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error in reading " + STORAGE_PATH + " occurred.");
            System.exit(FILE_ERROR_EXIT_CODE);
        } catch (IOException e) {
            System.out.println("An error in creating " + STORAGE_PATH + " occurred.");
            System.exit(FILE_ERROR_EXIT_CODE);
        }
    }


    /**
     * Overwrites the file at STORAGE_PATH with the ModuleDetails from the myModules list in the given ModuleList class.
     *
     * @param moduleList List of ModuleDetails to be stored in STORAGE_PATH
     */
    public static void writeModulesToFile(ModuleList moduleList) {
        try {
            FileWriter fw = new FileWriter(STORAGE_PATH, false);
            ArrayList<ModuleDetails> inputModules = moduleList.getMyModules();
            for (ModuleDetails module : inputModules) {
                fw.write('\n' + module.getEncodedFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(STORAGE_PATH + " can't be overwritten. Changes will be lost when the program is closed");
        }
    }

}
