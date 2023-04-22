<!--
  - Copyright 2023 Enaium
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<script setup lang="ts">
import { ElMessage, ElMessageBox } from "element-plus"
import { useUserStore } from "@/store"
import { useRoute } from "vue-router"

const route = useRoute()

const userStore = useUserStore()

const handleCommand = (command: string | number | object) => {
  if (command == "logout") {
    ElMessageBox.confirm("此操作将注销登录, 是否继续?", "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(() => {
        userStore.token = null
        userStore.id = null
        userStore.menus = []
      })
      .catch(() => {
        ElMessage({
          type: "info",
          message: "取消注销",
        })
      })
  } else if (command == "hrinfo") {
    window.$router.push({ path: "/hr-info" })
  }
}

const handleSelect = (key: string, keyPath: string[]) => {
  window.$router.push({ path: key })
}
</script>

<template>
  <ElContainer class="">
    <ElHeader class="d-flex justify-content-between align-items-center" style="background-color: #409eff">
      <div style="font-size: 30px; color: white">微人事重制版</div>
      <ElDropdown style="cursor: pointer" @command="handleCommand">
        <span class="d-flex align-items-center">123</span>
        <template #dropdown>
          <ElDropdownMenu>
            <ElDropdownItem command="hrinfo">个人中心</ElDropdownItem>
            <ElDropdownItem command="setting">设置</ElDropdownItem>
            <ElDropdownItem command="logout">注销登录</ElDropdownItem>
          </ElDropdownMenu>
        </template>
      </ElDropdown>
    </ElHeader>
    <ElContainer>
      <ElAside width="200px">
        <ElMenu @select="handleSelect" unique-opened>
          <ElSubMenu
            :index="menuIndex"
            v-for="(menu, menuIndex) in userStore.menus[0].children"
            :key="menuIndex"
          >
            <template #title>
              {{ menu.name }}
            </template>
            <ElMenuItem :index="child.path" v-for="(child, childIndex) in menu.children" :key="childIndex">
              {{ child.name }}
            </ElMenuItem>
          </ElSubMenu>
        </ElMenu>
      </ElAside>
      <ElMain>
        <ElBreadcrumb separator="/" v-if="route.path != '/home'">
          <ElBreadcrumbItem :to="{ path: '/home' }">首页</ElBreadcrumbItem>
          <ElBreadcrumbItem>{{ route.name }}</ElBreadcrumbItem>
        </ElBreadcrumb>
        <div v-if="route.path == '/home'">欢迎来到微人事！</div>
        <RouterView class="mt-1" />
      </ElMain>
    </ElContainer>
  </ElContainer>
</template>

<style scoped></style>