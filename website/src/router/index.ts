import { createRouter, createWebHistory } from "vue-router"
import Login from "@/pages/Login.vue"
import Home from "@/pages/Home.vue"
import FriendChat from "@/pages/chat/FriendChat.vue"
import HRInfo from "@/pages/HRInfo.vue"

export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      component: Login,
    },
    {
      path: "/home",
      component: Home,
      children: [
        {
          path: "/chat",
          component: FriendChat,
        },
        {
          path: "/hr-info",
          component: HRInfo,
        },
      ],
    },
  ],
})
