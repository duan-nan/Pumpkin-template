<template>
  <a-row :gutter="16" class="main-container">
    <a-col class="gutter-row" :span="7">
      <a-card style="border-radius: 5px" class="animate__animated animate__fadeIn">
        <my-info/>
      </a-card>
    </a-col>
    <a-col class="gutter-row" :span="17">
      <a-card style="border-radius: 5px" class="animate__animated animate__fadeIn">
        <a-tabs default-active-key="1">
          <a-tab-pane key="1" tab="基本信息">
            <basic-info/>
          </a-tab-pane>
        </a-tabs>
      </a-card>
    </a-col>
  </a-row>

</template>

<script>
import MyInfo from "@/views/person/component/MyInfo";
import BasicInfo from "@/views/person/component/BasicInfo";

export default {
  name: "Person",
  components: {BasicInfo, MyInfo}
}
</script>

<style scoped>

</style>

