/*
 * Copyright 2023 Enaium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { Router, RouteRecordRaw } from "vue-router"
import { IMenu } from "@/model"
import { useUserStore } from "@/store"
import { get } from "@/util/reuqest"

const modules = import.meta.glob("../pages/**/**.vue")

export const initMenu = (router: Router) => {
  const userStore = useUserStore()
  if (userStore.menus.length == 0 && userStore.id) {
    get<IMenu[]>(`/hr/${userStore.id}/menu`).then((r) => {
      userStore.menus = r.metadata
    })
  }
  formatMenus(router, userStore.menus)
}

export const formatMenus = (router: Router, menus: IMenu[]) => {
  let fmMenus: RouteRecordRaw[] = []
  menus.forEach((menu) => {
    let children: RouteRecordRaw[] = []
    if (menu.children) {
      children = formatMenus(router, menu.children)
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

    if (menu.path) {
      router.addRoute(route)
    }

    fmMenus.push(route)
  })
  return fmMenus
}
