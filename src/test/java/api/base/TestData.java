package api.base;

import org.testng.annotations.DataProvider;

final public class TestData {

    public final static class BoardTestData {
       public final static String BOARD_NAME = "Api Board";

       public static String boardId;
       public static String labelId;
       public static String listId;
    }

    public final static class ActionsTestData{

       public final static  String BOARD_NAME = "Board for Actions";

       public static String boardId;
       public static String toDoListId;
       public static String actiontId;
       public static String cardId;
       public static String actionIdAfterCreatingACard;
       public static String idMemberCreator;
       public static String idOrganizationThatBelongToAnAction;
       public static String idOfReaction;
    }

    public final static class CardsTestData{

    }

    public final static class CheckListsTestData{

    }

    public final static class LabelsTestData{

       public static String boardName = "Board for labels";
       public static String boardId;
       public static String labelId;
       public static String labelName = "Label from API";
       public static String color = "red";
       public static String newName = "New Label from API";
       public static String newColor = "blue";

       @DataProvider(name = "createUpdateFieldLabel")
       public Object[][] createUpdateFieldLabel() {
          return new Object[][]{
                  {"color", "green"},
                  {"name", "Name field Label from API"},
          };
       }
    }

    public final static class ListsTestData{

    }

    public final static class MembersTestData{

    }


//    public static String boardId;
    public static String idList;
    public static String labelId;
    public static String cardId;
    public static String memberId;
}