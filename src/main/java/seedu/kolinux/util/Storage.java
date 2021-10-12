package seedu.kolinux.util;

import java.io.File;

public class Storage {

    private static String dirPath = "./data";
    private static File directory = new File(dirPath);

    public static void initStorage() {
        directory.mkdir();
    }
}
