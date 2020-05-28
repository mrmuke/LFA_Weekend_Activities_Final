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
     getCurrentUser(emailAddress){
        return http.get(`/users?emailAddress=`+emailAddress);
     }
     update(id, data) {
         return http.put(`/users/${id}`, data);
     }
}
export default new UserDataService();