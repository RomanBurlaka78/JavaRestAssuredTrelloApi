package api.base;

public final class PathParameters {

    public static class BoardsPath {
        public final static String BOARDS_BASE_PATH = "boards/";
    }

    public static class CardsPath {
        public final static String CARDS_BASE_PATH = "/cards/";

    }

    public static class ActionsPath {
        public final static String ACTIONS_BASE_PATH = "/actions/";

    }

    public static class LabelsPath {
        public final static String LABELS_BASE_PATH = "/labels";

    }

    public static class ListsPath {
        public final static String LISTS_BASE_PATH = "/lists/";

    }

    public static class MembersPath {
        public final static String MEMBERS_BASE_PATH = "/members/";

    }

    public static class CheckListsPath {
        public final static String CHECKLISTS_BASE_PATH = "/checklists/";

        public  final static String checklistsEndPoint = "/checklists";

    }


    public final static String CUSTOM_FIELDS_BASE_PATH = "/customFields";

    public final static String ATTACHMENTS_BASE_PATH = "/attachments";

    public final static String attachmentsEndPoint = "/attachments/";
    public final static String checkItemsEndPoint = "/checkItemStates";


}
