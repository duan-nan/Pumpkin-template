<template>
  <div class="index-container animate__animated animate__fadeIn">
    <a-spin :spinning="loading" tip="正在努力拉取数据...">
      <!--CPU/RAM表格数据-->
      <div class="info-box">
        <cpu :cpu="CpuData"/>
        <ram :jvm="JvmData" :mem="MemData"/>
      </div>
      <!--表格数据-->
      <div class="echart-box">
        <jvm-echarts :jvm="JvmData"/>
        <runtime-echarts :mem="MemData"/>
      </div>
      <!--JAVA虚拟机信息-->
      <div class="jdk-box">
        <jdk :jdk="JvmData"/>
      </div>

    </a-spin>
  </div>
</template>

<script>
import {getSystemMonitorInfo} from "@/api/systemApi";
import Cpu from "@/views/system/monitor/component/Cpu";
import Ram from "@/views/system/monitor/component/Ram";
import Jdk from "@/views/system/monitor/component/Jdk";
import JvmEcharts from "@/views/system/monitor/component/JvmEcharts";
import RuntimeEcharts from "@/views/system/monitor/component/RuntimeEcharts";

export default {
  name: "ServerMonitor",
  components: {
    Cpu,
    Ram,
    Jdk,
    JvmEcharts,
    RuntimeEcharts
  },
  data() {
    return {
      timer: "",
      loading: false,
      CpuData: {},
      JvmData: {},
      MemData: {},
    };
  },
  mounted() {
    this.timer = setInterval(this.refreshData, 3000);
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  created() {
    this.initData();
  },
  methods: {
    async initData() {
      this.loading = true;
      const res = await getSystemMonitorInfo();
      if (res.success) {
        this.CpuData = res.data.cpu;
        this.JvmData = res.data.jvm;
        this.MemData = res.data.mem;
      }
      this.loading = false;
    },
    async refreshData() {
      const res = await getSystemMonitorInfo();
      if (res.success) {
        this.CpuData = res.data.cpu;
        this.JvmData = res.data.jvm;
        this.MemData = res.data.mem;
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.index-container {
  width: 100%;

  .info-box {
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin: 0 10px;
  }

  .jdk-box {
    margin: 10px 10px 0 10px;
  }

  .echart-box {
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin: 10px 10px 0 10px;
  }
}
</style>
