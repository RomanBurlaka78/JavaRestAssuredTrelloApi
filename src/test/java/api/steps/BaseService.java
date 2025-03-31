package api.steps;

public abstract class BaseService {

    // Здесь можно хранить методы по созданию и удалению доски / листов / карточек ..., а также методы для инициализации переменных
    //в таком случае не надо будет повторять данные методы в каждом степе, достаточно унаследовать степы от данного класа.
    //Другими словами -> вынести сюда весь повторяющийся код из каждого степа



//     public String createABord(String boardName){
//        requestSpecification.queryParam("name", boardName);
//        Response response = apiClient.post(PathParameters.BOARD_BASE_PATH, requestSpecification);
//        requestSpecification = RestAssured.given().spec(specification.installRequest());
//        return response.jsonPath().getString("id");
//    }
//
//    public String getTheFirstListsId(String boardId){
//        Response resp = apiClient.get(PathParameters.BOARD_BASE_PATH + boardId + PathParameters.LISTS_BASE_PATH, requestSpecification);
//        List arrayList = resp.jsonPath().getList("id");
//        requestSpecification = given(specification.installRequest());
//        return (String) arrayList.get(0);
//    }
//
//    public void deleteBoard(String boardId) {
//        apiClient.delete(PathParameters.BOARD_BASE_PATH + boardId, requestSpecification);
//
//    }
}
