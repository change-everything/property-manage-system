<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div id="car" style="width:100%;height:300px"></div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  methods:{
    initCard(){
      echarts.init(document.getElementById('car')).setOption({
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}:{c} ({d}%)'
          },
        series: [
            {
                type: 'pie',
                radius: '90%',
                center: ['60%', '50%'],
                data: [
                    { value: 1048, name: '外来车辆' },
                    { value: 735, name: '本区车辆' },
                    { value: 580, name: '剩余车位' }
                ],
                emphasis: {
                    itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
            ]
        })
    }
  },

  mounted(){
    this.initCard();
  }
}

</script>