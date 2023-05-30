<template>
  <a-collapse
      style="margin: 0 3%"
      accordion
      :bordered="false">
    <a-collapse-panel
        v-for="items in projectList"
        :key="items.title"
        :header="items.title">
      <a-collapse >
        <a-collapse-panel
            v-for="item in items.children"
            :key="item.title"
            :header="item.title">
          <p>{{ item.desc }}</p>
        </a-collapse-panel>
      </a-collapse>
    </a-collapse-panel>
  </a-collapse>
</template>

<script>
import {projectList} from "@/views/welcome/config/ProjectInfo";

export default {
  name: "ProjectList",
  data() {
    return {
      projectList: projectList
    }
  }
}
</script>

<style scoped>

</style>
