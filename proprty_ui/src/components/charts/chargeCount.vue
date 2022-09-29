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
                    data: ['101', '102', '103', '104', '105', '106', '106']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        data: [120, 200, 150, 80, 70, 110, 130],
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