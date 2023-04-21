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
import { IPosition } from "@/model"
import { del, get } from "@/util/reuqest"
import { ElMessage, ElMessageBox } from "element-plus"
import PositionForm from "@/components/PositionForm.vue"

const positionDialog = ref(false)
const currentPosition = ref<IPosition>({})
const positions = ref<IPosition[]>()

get<IPosition[]>("/system/position").then((r) => {
  positions.value = r.metadata
})

const add = () => {
  currentPosition.value = {}
  positionDialog.value = true
}

const edit = (position: IPosition) => {
  currentPosition.value = position
  positionDialog.value = true
}

const remove = (position: IPosition) => {
  ElMessageBox.confirm(`此操作将永久删除【${position.name}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/system/position/${position.id}`).then((r) => {
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
</script>

<template>
  <ElButton type="primary" size="small" @click="add">
    <template #icon>
      <Plus />
    </template>
    添加
  </ElButton>
  <ElTable :data="positions">
    <ElTableColumn type="selection" width="55" />
    <ElTableColumn prop="id" label="编号" width="55" />
    <ElTableColumn prop="name" label="职位名称" width="180" />
    <ElTableColumn prop="createDate" width="150" label="创建时间" />
    <ElTableColumn label="是否启用">
      <template #default="scope">
        <ElTag size="small" type="success" v-if="scope.row.enabled">已启用</ElTag>
        <ElTag size="small" type="danger" v-else>未启用</ElTag>
      </template>
    </ElTableColumn>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" @click="edit(scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="remove(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </ElTable>
  <ElDialog v-model="positionDialog">
    <PositionForm :position="currentPosition" @done="positionDialog = false" />
  </ElDialog>
</template>

<style scoped></style>