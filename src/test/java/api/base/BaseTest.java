package api.base;

import api.controllers.BoardSteps;
import api.controllers.ui.UiBoardSteps;
import api.utils.ListeningConfig;
import org.testng.annotations.Listeners;
import api.controllers.ActionsSteps;

@Listeners(ListeningConfig.class)
public class BaseTest {

    private BoardSteps boardSteps = new BoardSteps();
    private ActionsSteps actionsSteps = new ActionsSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();

    public BoardSteps getBoardSteps() {
        return boardSteps;
    }

    public ActionsSteps getActionsSteps() {
        return actionsSteps;
    }

    public UiBoardSteps getUiBoardSteps() {
        return uiBoardSteps;
    }

}
