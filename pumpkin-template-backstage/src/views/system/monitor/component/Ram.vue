<template>
  <a-card class="jvm-box animate__animated animate__flipInX" style="border-radius: 5px">
    <span style="font-size: 16px; font-weight: bold">RAM</span>
    <a-divider/>
    <div class="data-box">
      <div class="title">
        <span class="label">属性</span>
        <span class="mem">内存</span>
        <span class="jvm">Jvm</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">总内存</span>
        <span class="mem">{{ mem.total }}G</span>
        <span class="jvm">{{ jvm.total }}MB</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">已用内存</span>
        <span class="mem">{{ mem.used }}G</span>
        <span class="jvm">{{ jvm.used }}MB</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">剩余内存</span>
        <span class="mem">{{ mem.free }}G</span>
        <span class="jvm">{{ jvm.free }}MB</span>
      </div>
      <a-divider/>
      <div class="data">
        <span class="label">使用率</span>
        <span class="mem">{{ mem.usage }}%</span>
        <span class="jvm">{{ jvm.usage }}%</span>
      </div>
      <a-divider/>
    </div>
  </a-card>
</template>

<script>
export default {
  name: "RAM",
  props: ["jvm", "mem"],
};
</script>

<style scoped>
.ant-divider-horizontal {
  margin: 10px 0;
}

.jvm-box {
  width: 50%;
}

.jvm-box .data-box {
  padding: 0 10px;
}

.jvm-box .data-box .title {
  display: flex;
  justify-content: left;
  margin-top: 20px;
  color: #aaa6a6;
}

.jvm-box .data-box .title .label {
  width: 33%;
}

.jvm-box .data-box .title .mem {
  width: 33%;
}

.jvm-box .data-box .title .jvm {
  width: 33%;
}

.jvm-box .data-box .data {
  display: flex;
  justify-content: left;
}

.jvm-box .data-box .data .label {
  width: 33%;
}

.jvm-box .data-box .data .mem {
  width: 33%;
}

.jvm-box .data-box .data .jvm {
  width: 33%;
}
</style>
