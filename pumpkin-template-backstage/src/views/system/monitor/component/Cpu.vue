<template>
  <a-card class="cpu-box animate__animated animate__flipInX" style="border-radius: 5px">
    <span style="font-size: 16px; font-weight: bold">CPU</span>
    <a-divider/>
    <div class="data-box">
      <div class="title">
        <span class="label">属性</span>
        <span class="value">值</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">核心数</span>
        <span class="value">{{ cpu.cpuNum }}</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">用户使用率</span>
        <span class="value">{{ cpu.used }}%</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">系统使用率</span>
        <span class="value">{{ cpu.sys }}%</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">当前空闲率</span>
        <span class="value">{{ cpu.free }}%</span>
      </div>
      <a-divider/>
    </div>
  </a-card>
</template>

<script>
export default {
  name: "Cpu",
  props: ["cpu"],
};
</script>

<style scoped>
.ant-divider-horizontal {
  margin: 10px 0;
}

.cpu-box {
  width: 50%;
  margin-right: 20px;
}

.cpu-box .data-box {
  padding: 0 10px;
}

.cpu-box .data-box .title {
  display: flex;
  justify-content: left;
  margin-top: 20px;
  color: #aaa6a6;
}

.cpu-box .data-box .title .label {
  width: 60%;
}

.cpu-box .data-box .title .value {
  width: 40%;
}

.cpu-box .data-box .data {
  display: flex;
  justify-content: left;
}

.cpu-box .data-box .data .label {
  width: 60%;
}

.cpu-box .data-box .data .value {
  width: 40%;
}
</style>
