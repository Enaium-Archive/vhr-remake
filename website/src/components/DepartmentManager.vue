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
import { Search } from "@element-plus/icons-vue"
import { ref } from "vue"
import { IDepartment } from "@/model"
import { del, get } from "@/util/reuqest";
import { ElMessage, ElMessageBox, ElTree } from "element-plus";

const filterText = ref()
const departmentDialog = ref(false)
const superior = ref("")
const department = ref<IDepartment>({})
const departments = ref<IDepartment[]>()

const initDepartment = () => {
  get<IDepartment[]>("/system/department/tree").then((r) => {
    departments.value = r.metadata
  })
}

initDepartment()

const treeRef = ref<InstanceType<typeof ElTree>>()

const filterNode = (value: string, data: IDepartment) => {
  if (!value) return true
  return data.name?.indexOf(value) !== -1
}

const add = (data: IDepartment) => {}
const remove = (data: IDepartment) => {
  if (data.parent) {
    ElMessage({ type: "error", message: "父部门删除失败" })
  } else {
    ElMessageBox.confirm(`此操作将永久删除【${data.name}】, 是否继续?`, "提示", {
      confirmButtonText: "确定",
      cancelButtonText: "取消",
      type: "warning",
    })
      .then(() => {
        del(`/system/department/${data.id}`).then((r) => {
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
}
</script>

<template>
  <div>
    <ElInput placeholder="请输入部门名称进行搜索..." :prefix-icon="Search" v-model="filterText" />
    <ElTree
      ref="treeRef"
      :data="departments"
      :expand-on-click-node="false"
      :filter-node-method="filterNode"
      v-if="departments"
    >
      <template #default="{ node, data }" class="d-flex justify-content-between w-100">
        <div>{{ data.name }}</div>
        <div>
          <ElButton type="primary" size="small" @click="add(data)">添加部门</ElButton>
          <ElButton type="danger" size="small" @click="remove(data)">删除部门</ElButton>
        </div>
      </template>
    </ElTree>
  </div>
  <ElDialog v-model="departmentDialog">
    <div>
      <table>
        <tr>
          <td>
            <el-tag>上级部门</el-tag>
          </td>
          <td>{{ superior }}</td>
        </tr>
        <tr>
          <td>
            <el-tag>部门名称</el-tag>
          </td>
          <td>
            <el-input v-model="department.name" placeholder="请输入部门名称..."></el-input>
          </td>
        </tr>
      </table>
    </div>
  </ElDialog>
</template>

<style scoped></style>