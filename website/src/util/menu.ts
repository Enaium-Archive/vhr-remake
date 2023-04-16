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

import { get } from "@/util/reuqest"
import { IMenu } from "@/util/model"
import { RouteRecordRaw } from "vue-router"
import router from "@/router"

export const initMenu = () => {
  get<IMenu[]>("/system/menu").then((r) => {
    formatMenus(r.metadata)
  })
}

const formatMenus = (menus: IMenu[]) => {
  let fmMenus: RouteRecordRaw[] = []
  menus.forEach((menu) => {
    let children: RouteRecordRaw[] = []
    if (menu.children) {
      children = formatMenus(menu.children)
    }

    router.addRoute({
      path: menu.path!,
      name: menu.name!,
      component() {
        if (menu.component!.startsWith("Home")) {
          require("../views/" + menu.component! + ".vue")
        } else if (menu.component!.startsWith("Emp")) {
          require("../views/emp/" + menu.component! + ".vue")
        } else if (menu.component!.startsWith("Per")) {
          require("../views/per/" + menu.component! + ".vue")
        } else if (menu.component!.startsWith("Sal")) {
          require("../views/sal/" + menu.component! + ".vue")
        } else if (menu.component!.startsWith("Sta")) {
          require("../views/sta/" + menu.component! + ".vue")
        } else if (menu.component!.startsWith("Sys")) {
          require("../views/sys/" + menu.component! + ".vue")
        }
      },
      children: children,
    })
  })
  return fmMenus
}
