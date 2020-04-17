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
     getCurrentUser(userName){
        return http.get(`/users?userName=`+userName);
     }
     update(id, data) {
         return http.put(`/users/${id}`, data);
     }
}
export default new UserDataService();