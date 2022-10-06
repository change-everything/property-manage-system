<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div id="main" style="width:100%;height:300px"></div>
</template>
<script>
import * as echarts from 'echarts';
export default {
    methods:{
        init(){
            echarts.init(document.getElementById('main')).setOption({
                xAxis: {
                    type: 'category',
                    data: ['1月', '2月', '3月', '4月', '5月', '6月','7月','8月','9月','10月','11月','12月']
                },
                yAxis: {
                    type: 'value'
                },
                legend: {
                        orient: 'horizontal',
                        x: 'right',
                        y: 'top',
                        data: ['不满意','满意']
                },
                // grid: {
                //             top: '20%',  
                //             left: '3%', 
                //             right: '10%',
                //             bottom: '5%',
                //             containLabel: true
                //         },
                series: [
                    {
                    name:'不满意',
                    data: [820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934, 1290],
                    type: 'line',
                    smooth: true
                    },
                    {
                    name:'满意',
                    data: [720, 732, 701, 734, 1090, 1030, 1020,820, 932, 901, 934, 1290],
                    type: 'line',
                    smooth: true
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