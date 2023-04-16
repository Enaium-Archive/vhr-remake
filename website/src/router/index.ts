import { createRouter, createWebHistory } from "vue-router"
import Login from "@/pages/Login.vue"
import Home from "@/pages/Home.vue"
import FriendChat from "@/pages/chat/FriendChat.vue"
import HRInfo from "@/pages/HRInfo.vue"
import { initMenu } from "@/util/menu"

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      name: "Login",
      path: "/",
      component: Login,
    },
    {
      name: "Home",
      path: "/home",
      component: Home,
      children: [
        {
          name: "在线聊天",
          path: "/chat",
          component: FriendChat,
        },
        {
          name: "个人中心",
          path: "/hr-info",
          component: HRInfo,
        },
      ],
    },
    {
      path: "/:pathMatch(.*)*",
      redirect: "/home",
    },
  ],
})

router.beforeEach((to, from, next) => {
  if (to.path == "/") {
    next()
  } else {
    initMenu()
    next()
  }
})

export default router
