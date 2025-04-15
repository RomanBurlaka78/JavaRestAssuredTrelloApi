package api.base;

final public class PathParameters {

    public final static  class BoardEndPoints {

        public final static String BOARDS_BASE_PATH = "boards/";

        public static String boardStarsEnPoint = "/boardStars";
        public static String MEMBER_SHIPS_ENDPOINT = "/memberships";

    }

    public  final static class ActionsEndPoints {

        public final static String ACTIONS_BASE_PATH = "/actions/";

        public final static String DATE_ENDPOINT = "/date";
        public final static String BOARD_ENDPOINT = "/board";
        public final static String CARD_ENDPOINT = "/card";
        public final static String LIST_ENDPOINT = "/list";
        public final static String MEMBER_CREATOR_ENDPOINT = "/memberCreator";
        public final static String ORGANIZATION_ENDPOINT = "/organization";
        public final static String TEXT_ENDPOINT = "/text";
        public final static String REACTIONS_ENDPOINT = "/reactions/";
        public final static String COMMENTS_ENDPOINT = "comments";
    }

    public final static class CardsEndPoints {

        public final static String CARDS_BASE_PATH = "/cards/";
        public final static String ATTACHMENTS_ENDPOINT = "/attachments/";
        public final static String CHECKITEM_ENDPOINT = "/checkItemStates";
        public final static String CHECKLISTS_ENDPOINT = "/checklists";
        public final static String LISTS_ENDPOINT = "/list";
        public final static String MEMBERS_ENDPOINT = "/Members";
        public final static String STICKERS_ENDPOINT = "/stickers";

    }

    public final static class CheckListsPath {

    }

    public final static class LabelsPath {

    }

    public final static class ListsPath {

    }

    public final static class MembersPath {

    }


    public final static String CUSTOM_FIELDS_BASE_PATH = "/customFields";
    public final static String LISTS_BASE_PATH = "/lists/";
    public final static String MEMBERS_BASE_PATH = "/members/";
    public final static String CHECKLISTS_BASE_PATH = "/checklists/";
    public final static String LABELS_BASE_PATH = "/labels";
    public final static String ATTACHMENTS_BASE_PATH = "/attachments";
}
