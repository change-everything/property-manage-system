<!--
 @author: 孙明君
 @date: 2022-06-16
 -->y
<template>
    <div id="chargeMain" style="width:100%;height:300px"></div>
</template>
<script>
import * as echarts from 'echarts';
export default {
    methods:{
        init(){
            echarts.init(document.getElementById('chargeMain')).setOption({
                title:{
                    name:'住户用电量'
                },
                xAxis: {
                    type: 'category',
                    data: ['系统问题', '空调维修', '清洁绿化', '道闸问题', '电梯问题', '其他问题']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [120, 200, 150, 80, 70, 110],
                        type: 'bar'
                    }
                ]
            })
        }
    },
    mounted(){
        this.init();
    }
}
</script>