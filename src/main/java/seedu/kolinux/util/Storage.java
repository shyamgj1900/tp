package seedu.kolinux.util;

import java.io.File;

public class Storage {

    private static final String DIR_PATH = "./data";
    private static File directory = new File(DIR_PATH);

    public void initStorage() {
        directory.mkdir();
    }
}
