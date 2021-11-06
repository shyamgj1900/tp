package seedu.kolinux.util;

import org.junit.jupiter.api.Test;

import java.io.File;

public class DirectoryCreatorTest {

    private File file = new File("data/");
    private DirectoryCreator directoryCreator = new DirectoryCreator();

    @Test
    public void initDirectory_noArguments_directoryCreated() {
        directoryCreator.initDirectory();
        assert file.isDirectory();
    }
}
