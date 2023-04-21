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
import { IMenu, IRole } from "@/model"
import { ref, watch } from "vue"
import { get, put } from "@/util/reuqest"
import { ElMessage, ElTree } from "element-plus"

const props = defineProps<{
  role: IRole
}>()

const emit = defineEmits(["done"])

const menus = ref<IMenu[]>([])

get<IMenu[]>("/system/menu").then((r) => {
  menus.value = r.metadata
})

const treeRef = ref<InstanceType<typeof ElTree>>()

const initChecked = () => {
  get<number[]>(`/system/role/${props.role.id}/menuIds`).then((r) => {
    treeRef.value?.setCheckedKeys(r.metadata)
  })
}

initChecked()

watch(
  () => props.role,
  (value) => {
    initChecked()
  },
)

const done = () => {
  put(`/system/role/${props.role.id}/menuIds?menuIds=${treeRef.value?.getCheckedKeys()}`).then((r) => {
    if (r.code == 200) {
      ElMessage({ type: "success", message: "更新成功" })
      emit("done")
    }
  })
}
</script>

<template>
  <ElTree ref="treeRef" :data="menus" :expand-on-click-node="false" node-key="id" show-checkbox>
    <template #default="{ node, data }">
      {{ data.name }}
    </template>
  </ElTree>
  <ElButton type="primary" @click="done">完成</ElButton>
</template>

<style scoped></style>