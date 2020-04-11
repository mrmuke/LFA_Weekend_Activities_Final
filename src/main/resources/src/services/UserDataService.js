import http from "../http-common";

class UserDataService {
    create(data) {
        return http.post("/users", data);
     }
     getAll() {
         return http.get("/users");
       }
     get(id) {
         return http.get(`/users/${id}`);
     }
     get(userName){
        return http.get('/currentUser')
     }
}
export default new UserDataService();