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
import DepartmentManager from "@/components/DepartmentManager.vue"
import PositionManager from "@/components/PositionManager.vue"
</script>

<template>
  <ElTabs type="card">
    <ElTabPane label="部门管理">
      <DepartmentManager />
    </ElTabPane>
    <ElTabPane label="职称管理">
      <PositionManager />
    </ElTabPane>
  </ElTabs>
</template>

<style scoped></style>