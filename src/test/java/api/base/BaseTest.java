package api.base;

import api.controllers.ActionsSteps;
import api.controllers.BoardSteps;
import api.controllers.LabelsSteps;
import api.controllers.MembersSteps;
import api.controllers.ui.UiBoardSteps;
import api.utils.ListeningConfig;
import org.testng.annotations.Listeners;

@Listeners(ListeningConfig.class)
public class BaseTest {

    private BoardSteps boardSteps = new BoardSteps();
    private ActionsSteps actionsSteps = new ActionsSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();
    private final LabelsSteps labelsSteps = new LabelsSteps();
    private final MembersSteps membersSteps = new MembersSteps();

    public BoardSteps getBoardSteps() {
        return boardSteps;
    }

    public ActionsSteps getActionsSteps() {
        return actionsSteps;
    }

    public UiBoardSteps getUiBoardSteps() {
        return uiBoardSteps;
    }

    public LabelsSteps getLabelsSteps() {
        return labelsSteps;
    }

    public MembersSteps getMembersSteps() {
        return membersSteps;
    }
}
