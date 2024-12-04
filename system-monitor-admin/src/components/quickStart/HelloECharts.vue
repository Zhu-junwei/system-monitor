<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted} from "vue";

onMounted(() => {
  initChart();
});

function initChart() {
  // 基于准备好的dom，初始化echarts实例
  let myChart = echarts.init(document.getElementById('main'));
  // 绘制图表
  myChart.setOption({
    title: {
      text: 'ECharts 入门示例'
    },
    tooltip: {},
    xAxis: {
      data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
    },
    yAxis: {},
    series: [
      {
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
      }
    ]
  });
}

</script>

<template>
  <div id="main">
  </div>
</template>

<style scoped>
#main {
  width: 300px;
  height: 300px;
  /* 居中显示 */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid #ccc;
  border-radius: 4px;
  overflow: hidden;
}
</style>
