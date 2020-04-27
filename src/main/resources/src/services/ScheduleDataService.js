import http from "../http-common";

class ScheduleDataService {
  getAll() {
    return http.get("/schedules");
  }

  get(id) {
    return http.get(`/schedules/${id}`);
  }

  create(data) {
    return http.post("/schedules", data);
  }

  update(id, data) {
    return http.put(`/schedules/${id}`, data);
  }

  delete(id) {
    return http.delete(`/schedules/${id}`);
  }
}
export default new ScheduleDataService();