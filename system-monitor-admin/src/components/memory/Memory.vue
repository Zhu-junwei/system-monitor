<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted} from "vue";
import axios from "axios";


type EChartsOption = echarts.EChartsOption;
let option: EChartsOption;
let chartDom;
let myChart: echarts.ECharts;

onMounted(() => {
  chartDom = document.getElementById('main')!;
  myChart = echarts.init(chartDom);

  option = {
    tooltip: {
      formatter: '{a} <br/>{b} : {c}%'
    },
    series: [
      {
        name: 'Pressure',
        type: 'gauge',
        min: 0,
        max: 100,
        splitNumber: 10,
        progress: {
          show: true
        },
        detail: {
          valueAnimation: true,
          formatter: '{value}'
        },
        data: [
          {
            value: 0,
            name: '内存'
          }
        ]
      }
    ]
  };

  option && myChart.setOption(option);
})

async function getMemoryInfo() {
  axios.get('http://localhost:8080/memory/info').then(res => {
    let memoryInfo = res.data.data;
    console.log(memoryInfo);
    // option.series[0].max = parseFloat(memoryInfo.total);
    option.series[0].name = memoryInfo.total;
    option.series[0].data[0].value = memoryInfo.usageRatio;
    // option.series[0].data[0].value = parseFloat(memoryInfo.total)-parseFloat(memoryInfo.available);
    myChart.setOption(option);
  })
}

// 每3秒调用一次 myFunction
setInterval(getMemoryInfo, 3000);

</script>

<template>
  <div id="main"></div>
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