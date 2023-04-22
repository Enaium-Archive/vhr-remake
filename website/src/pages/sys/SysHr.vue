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
import { IHr } from "@/model"
import { del, get, put } from "@/util/reuqest"
import { Delete, Edit } from "@element-plus/icons-vue"
import { ElMessage, ElMessageBox } from "element-plus"
import RoleEdit from "@/components/role/RoleEdit.vue"

const hrs = ref<IHr[]>()
const currentHr = ref<IHr>({})
const roleDialog = ref(false)

const initHr = () => {
  get<IHr[]>("/hr").then((r) => {
    hrs.value = r.metadata
  })
}
initHr()

const remove = (hr: IHr) => {
  ElMessageBox.confirm(`此操作将永久删除【${hr.name}】, 是否继续?`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      del(`/hr/${hr.id}`).then((r) => {
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

const editRole = (hr: IHr) => {
  currentHr.value = hr
  roleDialog.value = true
}

const enabledChange = (hr: IHr) => {
  put("/hr", hr).then((r) => {
    if (r.code == 200) {
      ElMessage({ type: "success", message: "更新成功" })
    }
  })
}
</script>

<template>
  <div class="d-flex flex-wrap justify-content-around">
    <ElCard class="mb-1" style="width: 350px" v-for="hr in hrs" :key="hr.id">
      <template #header>
        <span>{{ hr.name }}</span>
        <ElButton @click="remove(hr)" text>
          <template #icon>
            <Delete />
          </template>
        </ElButton>
      </template>
      <div class="d-flex justify-content-center">
        <img
          :src="hr.userface"
          :alt="hr.name"
          :title="hr.name"
          style="width: 72px; height: 72px; border-radius: 72px"
        />
      </div>
      <div style="font-size: 12px; color: #409eff">
        <div>用户名：{{ hr.name }}</div>
        <div>手机号码：{{ hr.phone }}</div>
        <div>电话号码：{{ hr.telephone }}</div>
        <div>地址：{{ hr.address }}</div>
        <div>
          用户状态：
          <ElSwitch
            v-model="hr.enabled"
            @change="enabledChange(hr)"
            active-text="启用"
            active-color="#13ce66"
            inactive-color="#ff4949"
            inactive-text="禁用"
          />
        </div>
        <div>
          用户角色：
          <ElTag type="success" style="margin-right: 4px" v-for="role in hr.roles" :key="role.id"
            >{{ role.nameZh }}
          </ElTag>
          <ElButton text @click="editRole(hr)">
            <template #icon>
              <Edit />
            </template>
          </ElButton>
        </div>
        <div>备注：{{ hr.remark }}</div>
      </div>
    </ElCard>
  </div>
  <ElDialog v-model="roleDialog">
    <RoleEdit :hr="currentHr" @done="roleDialog = false" />
  </ElDialog>
</template>

<style scoped></style>