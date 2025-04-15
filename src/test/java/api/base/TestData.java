package api.base;

import org.testng.annotations.DataProvider;

final public class TestData {

   private static final String BASE_BOARD_NAME = "Board for ";

    public final static class BoardTestData {
       public final static String BOARD_NAME = BASE_BOARD_NAME + "Board";

       public static String boardId;
       public static String labelId;
       public static String listId;



    }

    public final static class ActionsTestData{

       public final static  String BOARD_NAME = BASE_BOARD_NAME + "Actions";

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

       public final static  String BOARD_NAME = BASE_BOARD_NAME + "Cards";

       public static String cardId;
       public static String boardId;
       public static String listId;
       public static String createdAttachmentId;





    }

    public final static class CheckListsTestData{

    }

    public final static class LabelsTestData{

       public static final String BOARD_NAME = BASE_BOARD_NAME + "Labels";
       public static final String LABEL_NAME = "Label from API";
       public static final String COLOR = "red";
       public static final String NEW_NAME = "New Label from API";
       public static final String NEW_COLOR = "blue";
       public static String boardId;
       public static String labelId;

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

       public static final String BORD_NAME = BASE_BOARD_NAME+ "Members";
       public static final String POS = "top";
       public static final String UPDATE_POS = "bottom";
       public static String boardId;
       public static String firstMemberId;
       public static String backgroundId;
       public static String starId;
    }


//    public static String boardId;
    public static String idList;
    public static String labelId;
    public static String cardId;
    public static String memberId;
}