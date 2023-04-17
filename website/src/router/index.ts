import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router"
import Login from "@/pages/Login.vue"
import Home from "@/pages/Home.vue"
import FriendChat from "@/pages/chat/FriendChat.vue"
import HRInfo from "@/pages/HRInfo.vue"
import { useUserStore } from "@/store"
import { get } from "@/util/reuqest"
import { IMenu } from "@/util/model"

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
const modules = import.meta.glob("../pages/**/**.vue")
const initMenu = () => {
  const userStore = useUserStore()
  if (userStore.menus.length > 0) {
    formatMenus(userStore.menus)
  } else {
    get<IMenu[]>("/system/menu").then((r) => {
      userStore.menus = r.metadata
    })
  }
}

const formatMenus = (menus: IMenu[]) => {
  let fmMenus: RouteRecordRaw[] = []
  menus.forEach((menu) => {
    let children: RouteRecordRaw[] = []
    if (menu.children) {
      children = formatMenus(menu.children)
    }

    const route: RouteRecordRaw = {
      path: menu.path!,
      name: menu.name!,
      children: children,
    }
    if (menu.component) {
      if (menu.component.startsWith("Home")) {
        route.component = modules["../pages/" + menu.component + ".vue"]
      } else if (menu.component.startsWith("Emp")) {
        route.component = modules["../pages/emp/" + menu.component + ".vue"]
      } else if (menu.component.startsWith("Per")) {
        route.component = modules["../pages/per/" + menu.component + ".vue"]
      } else if (menu.component.startsWith("Sal")) {
        route.component = modules["../pages/sal/" + menu.component + ".vue"]
      } else if (menu.component.startsWith("Sta")) {
        route.component = modules["../pages/sta/" + menu.component + ".vue"]
      } else if (menu.component.startsWith("Sys")) {
        route.component = modules["../pages/sys/" + menu.component + ".vue"]
      }
    }
    console.log(route);
    router.addRoute(route)
    fmMenus.push(route)
  })
  return fmMenus
}

export default router
