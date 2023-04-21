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
import { Plus } from "@element-plus/icons-vue"
import { ref } from "vue"
import { IMenu, IRole } from "@/model"
import { get } from "@/util/reuqest"
import { Delete } from "@element-plus/icons-vue";

const loading = ref(false)
const roles = ref<IRole[]>()
const menus = ref<IMenu[]>()

const initRoles = () => {
  loading.value = true
  get<IRole[]>("/system/role").then((r) => {
    roles.value = r.metadata
  })
}

const initMenus = () => {
  get<IMenu[]>("/system/menu").then((r) => {
    menus.value = r.metadata
  })
}

initRoles()
</script>

<template>
  <div class="d-flex justify-content-start">
    <ElInput placeholder="请输入角色英文名" size="small">
      <template #prepend>ROLE_</template>
    </ElInput>
    <ElInput placeholder="请输入角色中文名" size="small" />
    <ElButton type="primary" size="small">
      <template #icon>
        <Plus />
      </template>
      添加角色
    </ElButton>
  </div>
  <ElCollapse>
    <ElCollapseItem :title="role.nameZh" v-for="role in roles" :key="role.id">
      <ElCard>
        <template #header>
          可访问的资源
          <ElButton text>
            <template #icon>
              <Delete />
            </template>
          </ElButton>
        </template>
      </ElCard>
    </ElCollapseItem>
  </ElCollapse>
</template>

<style scoped></style>