import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/events",
      name: "events",
      props:true,
      component: () => import("./components/EventList")
    },
    {
      path: "/events/:id",
      name: "event-details",
      component: () => import("./components/Event")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddEvent")
    },
    {
      path: "/home",
      name: "home",
      component: () => import("./components/Home")
     }
  ]
});