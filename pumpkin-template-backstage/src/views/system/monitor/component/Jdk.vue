<template>
  <a-card class="animate__animated animate__flipInX" style="border-radius: 5px">
    <span class="title">java虚拟机信息</span>
    <a-divider/>
    <div class="data-box">
      <div class="data" style="margin-top: 20px">
        <div class="one-box">
          <span class="label">Java名称</span>
          <span class="value">{{ jdk.name }}</span>
        </div>
        <div class="two-box">
          <span class="label">Java版本</span>
          <span class="value">{{ jdk.version }}</span>
        </div>
      </div>
      <a-divider/>
      <div class="data">
        <div class="one-box">
          <span class="label">启动时间</span>
          <span class="value">{{ jdk.startTime }}</span>
        </div>
        <div class="two-box">
          <span class="label">运行时长</span>
          <span class="value">{{ jdk.runTime }}</span>
        </div>
      </div>
      <a-divider/>
      <div class="data">
        <div style="margin-right: 60px">安装路径</div>
        <div>{{ jdk.home }}</div>
      </div>
      <a-divider/>
    </div>
  </a-card>
</template>

<script>
export default {
  name: "Jdk",
  props: ["jdk"],
};
</script>

<style scoped>
.ant-divider-horizontal {
  margin: 10px 0;
}

.title {
  font-size: 17px;
  font-weight: bolder;
}

.data-box {
  padding: 0 10px;
}

.data {
  display: flex;
  justify-content: left;
  align-items: center;
}

.data .one-box {
  width: 50%;
  display: flex;
  justify-content: left;
  align-items: center;
}

.data .one-box .label {
  width: 20%;
}

.data .one-box .value {
  width: 80%;
}

.data .two-box {
  width: 50%;
  display: flex;
  justify-content: left;
  align-items: center;
}

.data .two-box .label {
  width: 20%;
}

.data .two-box .value {
  width: 80%;
}
</style>
