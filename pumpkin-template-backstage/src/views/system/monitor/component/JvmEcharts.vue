<template>
  <a-card class="jvm-container animate__animated animate__flipInX" style="border-radius: 5px">
    <div id="jvm-view" class="views-box"></div>
  </a-card>
</template>

<script>
import * as echarts from "echarts";
import dayjs from "dayjs";

export default {
  name: "JvmEchart",
  props: ["jvm"],
  data() {
    return {
      usedJvm: [],
      freeJvm: [],
      time: [],
    };
  },
  watch: {
    jvm() {
      this.updateData();
      this.formatView();
    },
  },
  methods: {
    updateData() {
      if (this.usedJvm.length <= 5 || this.time.length <= 5) {
        this.time.push(dayjs().format("HH:mm:ss"));
        this.usedJvm.push(this.jvm.used);
        this.freeJvm.push(this.jvm.free);
      } else {
        this.time.shift();
        this.usedJvm.shift();
        this.freeJvm.shift();
      }
    },
    formatView() {
      var myChart = echarts.init(document.getElementById("jvm-view"));
      myChart.setOption({
        title: {
          text: "JVM内存情况",
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
            formatter: "{value} MB",
          },
        },
        legend: {
          data: ["Jvm内存使用", "Jvm剩余内存"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "2%",
          containLabel: true,
        },
        series: [
          {
            name: "Jvm内存使用",
            data: this.usedJvm,
            type: "line",
            stack: "Total",
            markPoint: {
              data: [{type: "max", name: "Max"}],
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(128, 255, 165)",
                },
                {
                  offset: 1,
                  color: "rgb(1, 191, 236)",
                },
              ]),
            },
          },
          {
            name: "Jvm剩余内存",
            data: this.freeJvm,
            type: "line",
            stack: "Total",
            markPoint: {
              data: [{type: "max", name: "Max"}],
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                {
                  offset: 0,
                  color: "rgb(0, 221, 255)",
                },
                {
                  offset: 1,
                  color: "rgb(77, 119, 255)",
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
.jvm-container {
  width: 50%;
  margin-right: 20px;

  .views-box {
    height: 250px;
  }
}


</style>
