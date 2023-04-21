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
import { ref, watch } from "vue"
import { IDepartment } from "@/model"
import { del, get, put } from "@/util/reuqest"
import { ElMessage, ElMessageBox, ElTree } from "element-plus"
import Node from "element-plus/es/components/tree/src/model/node"

const filterText = ref()
const departments = ref<IDepartment[]>()

get<IDepartment[]>("/system/department").then((r) => {
  departments.value = r.metadata
})

const treeRef = ref<InstanceType<typeof ElTree>>()

const filterNode = (value: string, data: IDepartment) => {
  if (!value) return true
  return data.name?.indexOf(value) !== -1
}

const add = (data: IDepartment, node: Node) => {
  data.children?.push({ parentId: data.id })
  node.expanded = true
}
const remove = (data: IDepartment) => {
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

watch(filterText, (o) => {
  treeRef.value?.filter(o)
})

const enter = (data: IDepartment) => {
  put<number>("/system/department", data).then((r) => {
    data.id = r.metadata
  })
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
      <template #default="{ node, data }">
        <div class="d-flex justify-content-between w-100" v-if="data.id">
          <div>
            {{ data.name }}
          </div>
          <div>
            <ElButton type="primary" size="small" @click="add(data, node)">添加部门</ElButton>
            <ElButton type="danger" size="small" @click="remove(data)">删除部门</ElButton>
          </div>
        </div>
        <div class="d-flex w-100" v-else>
          <ElInput placeholder="按下回车完成输入" v-model="data.name" size="small" @keydown.enter="enter(data)" />
          <ElButton @click="enter(data)" size="small" type="primary">完成</ElButton>
        </div>
      </template>
    </ElTree>
  </div>
</template>

<style scoped></style>