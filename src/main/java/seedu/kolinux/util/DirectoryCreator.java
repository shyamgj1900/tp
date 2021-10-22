package seedu.kolinux.util;

import java.io.File;

/** Represents the information needed to ensure Kolinux is initialized with a /data directory. */
public class DirectoryCreator {

    private static final String DIR_PATH = "./data";
    private static File directory = new File(DIR_PATH);

    /**
     * Creates a directory /data if it does not exist.
     */
    public void initDirectory() {
        directory.mkdir();
    }
}
