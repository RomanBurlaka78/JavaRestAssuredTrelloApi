package api.base;

import api.controllers.*;
import api.controllers.ui.UiBoardSteps;
import api.utils.ListeningConfig;
import org.testng.annotations.Listeners;

@Listeners(ListeningConfig.class)
public class BaseTest {

    private BoardSteps boardSteps = new BoardSteps();
    private ActionsSteps actionsSteps = new ActionsSteps();
    private CardsSteps cardsSteps = new CardsSteps();
    private UiBoardSteps uiBoardSteps = new UiBoardSteps();
    private final LabelsSteps labelsSteps = new LabelsSteps();
    private final MembersSteps membersSteps = new MembersSteps();
    private final ChecklistsSteps checklistsSteps = new ChecklistsSteps();

    public BoardSteps getBoardSteps() {
        return boardSteps;
    }

    public ChecklistsSteps getChecklistsSteps() {
        return checklistsSteps;
    }

    public ActionsSteps getActionsSteps() {
        return actionsSteps;
    }

    public CardsSteps getCardsSteps() {
        return cardsSteps;
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
