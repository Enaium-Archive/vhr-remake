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

const loading = ref(false)
const currentPosition = ref<IPosition>({})

const add = () => {
  console.log(currentPosition.value.name);
}
</script>

<template>
  <div>
    <ElInput
      size="small"
      v-model="currentPosition.name"
      v-loading="loading"
      element-loading-text="正在加载..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
      placeholder="添加职位..."
      @keydown.enter="add"
    />
    <ElButton type="primary" size="small" @click="add">
      <template #icon>
        <Plus />
      </template>
      添加
    </ElButton>
  </div>
  <div>
    <ElTable>
      
    </ElTable>
  </div>
</template>

<style scoped></style>