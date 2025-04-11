package api.base;

import api.controllers.BoardSteps;
import api.controllers.ui.UiBoardSteps;
import api.utils.ListeningConfig;
import org.testng.annotations.Listeners;

@Listeners(ListeningConfig.class)
public class BaseTest {
    private BoardSteps boardSteps = new BoardSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();

    public BoardSteps getBoardSteps() {
        return boardSteps;
    }

    public UiBoardSteps getUiBoardSteps() {
        return uiBoardSteps;
    }

}
