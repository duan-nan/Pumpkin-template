<template>
  <a-card class="animate__animated animate__flipInX" style="width: 50%;border-radius: 5px">
    <div id="runtime-view" class="views-box"></div>
  </a-card>
</template>

<script>
import dayjs from "dayjs";
import * as echarts from "echarts";

export default {
  name: "RuntimeEchart",
  props: ["mem"],
  data() {
    return {
      usedMem: [],
      freeMem: [],
      time: [],
    };
  },
  watch: {
    mem() {
      this.updateData();
      this.formatView();
    },
  },
  methods: {
    updateData() {
      if (this.usedMem.length <= 5 || this.time.length <= 5) {
        this.time.push(dayjs().format("HH:mm:ss"));
        this.usedMem.push(this.mem.used);
        this.freeMem.push(this.mem.free);
      } else {
        this.time.shift();
        this.usedMem.shift();
        this.freeMem.shift();
      }
    },
    formatView() {
      var myChart = echarts.init(document.getElementById("runtime-view"));
      myChart.setOption({
        title: {
          text: "服务器内存情况",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "cross",
            label: {
              backgroundColor: "#6a7985",
            },
          },
        },
        xAxis: {
          type: "category",
          data: this.time,
        },
        yAxis: {
          type: "value",
          axisLabel: {
            formatter: "{value} G",
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "2%",
          containLabel: true,
        },
        legend: {
          data: ["服务器内存使用", "服务器剩余内存"],
        },
        series: [
          {
            name: "服务器内存使用",
            data: this.usedMem,
            type: "line",
            stack: "Total",
            smooth: true,
            markPoint: {
              data: [{type: "max", name: "Max"}],
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(255, 0, 135)",
                },
                {
                  offset: 1,
                  color: "rgb(135, 0, 157)",
                },
              ]),
            },
          },
          {
            name: "服务器剩余内存",
            data: this.freeMem,
            type: "line",
            stack: "Total",
            smooth: true,
            markPoint: {
              data: [{type: "max", name: "Max"}],
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(255, 191, 0)",
                },
                {
                  offset: 1,
                  color: "rgb(224, 62, 76)",
                },
              ]),
            },
          },
        ],
      });
      setTimeout(function () {
        window.onresize = function () {
          myChart.resize();
        };
      }, 200);
    },
  },
};
</script>

<style lang="scss" scoped>
.views-box {
  height: 250px;
}
</style>
