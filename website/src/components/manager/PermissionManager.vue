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
import { ref } from "vue"
import { IRole } from "@/model"
import { del, get } from "@/util/reuqest"
import { ElMessage, ElMessageBox } from "element-plus"
import PermissionEdit from "@/components/PermissionEdit.vue"

const roles = ref<IRole[]>()
const currentRole = ref<IRole>({})
const roleDialog = ref(false)

get<IRole[]>("/system/role").then((r) => {
  roles.value = r.metadata
})

const remove = (role: IRole) => {
  ElMessageBox.confirm(`此操作将永久删除【${role.nameZh}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/system/role/${role.id}`).then((r) => {
        if (r.code == 200) {
          ElMessage({ type: "success", message: "删除成功" })
        }
      })
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消删除",
      })
    })
}

const edit = (role: IRole) => {
  currentRole.value = role
  roleDialog.value = true
}
</script>

<template>
  <ElTable :data="roles">
    <ElTableColumn prop="id" label="编号" />
    <ElTableColumn prop="name" label="名称" />
    <ElTableColumn prop="nameZh" label="中文名称" />
    <ElTableColumn>
      <template #default="scope">
        <el-button size="small" @click="edit(scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="remove(scope.row)">删除</el-button>
      </template>
    </ElTableColumn>
  </ElTable>
  <ElDialog v-model="roleDialog">
    <PermissionEdit :role="currentRole" @done="roleDialog = false" />
  </ElDialog>
</template>

<style scoped></style>