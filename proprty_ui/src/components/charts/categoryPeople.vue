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
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                legend: {
                        orient: 'horizontal',
                        x: 'left',
                        y: 'top',
                        data: ['总人数','外来人员','本地人员']
                },
                grid: {
                            top: '20%',  
                            left: '3%', 
                            right: '10%',
                            bottom: '5%',
                            containLabel: true
                        },
                series: [
                    {
                    name:'总人数',
                    data: [820, 932, 901, 934, 1290, 1330, 1320],
                    type: 'line',
                    smooth: true
                    },
                    {
                    name:'外来人员',
                    data: [720, 732, 701, 734, 1090, 1030, 1020],
                    type: 'line',
                    smooth: true
                    },
                    {
                    name:'本地人员',
                    data: [520, 932, 401, 634, 1110, 1330, 1320],
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