package seedu.kolinux.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromptHandlerTest {

    private static final String PROMPT_MESSAGE = "What do you want to be when you grow up?";
    private static final String PROMPT_REPLY = "I want to go Russia and start a farm.";

    @Test
    public void getReplyFromPrompt_validUserInput_promptAnswered() {
        InputStream in = new ByteArrayInputStream(PROMPT_REPLY.getBytes());
        System.setIn(in);

        PromptHandler promptHandler = new PromptHandler(PROMPT_MESSAGE);

        assertEquals(PROMPT_REPLY, promptHandler.getReplyFromPrompt());
    }
}
