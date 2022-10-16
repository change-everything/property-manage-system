<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div>
        <!-- 卫生费 -->
        <div id="chargeTEH" style="width:100%;height:300px"></div>

        <!-- 水费 -->
        <div id="chargeWater" style="width:100%;height:300px"></div>
        
        <!-- 电费 -->
        <div id="chargeElectricity" style="width:100%;height:300px"></div>

        <!-- 物业费 -->
        <div id="chargeProperty" style="width:100%;height:300px"></div>
    </div>
</template>
<script>
import * as echarts from 'echarts';
export default {
    data(){
        return{
            chargeTEH:'',
            chargeWater:'',
            chargeElectricity:'',
            chargeProperty:''
        }
    },
    methods:{
        init(){
             echarts.init(document.getElementById('chargeTEH')).setOption({
                tooltip: {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: 'Pressure',
                        type: 'gauge',
                        progress: {
                            show: true
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: '{value}'
                        },
                        data: [
                            {
                                value: 50,
                                name: 'SCORE'
                            }
                        ]
                    }
                ]
             }),

             echarts.init(document.getElementById('chargeWater')).setOption({
                 tooltip: {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: 'Pressure',
                        type: 'gauge',
                        progress: {
                            show: true
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: '{value}'
                        },
                        data: [
                            {
                                value: 50,
                                name: 'SCORE'
                            }
                        ]
                    }
                ]
             })

             echarts.init(document.getElementById('chargeElectricity')).setOption({
                 tooltip: {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: 'Pressure',
                        type: 'gauge',
                        progress: {
                            show: true
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: '{value}'
                        },
                        data: [
                            {
                                value: 50,
                                name: 'SCORE'
                            }
                        ]
                    }
                ]
             })

             echarts.init(document.getElementById('chargeProperty')).setOption({
                 tooltip: {
                    formatter: '{a} <br/>{b} : {c}%'
                },
                series: [
                    {
                        name: 'Pressure',
                        type: 'gauge',
                        progress: {
                            show: true
                        },
                        detail: {
                            valueAnimation: true,
                            formatter: '{value}'
                        },
                        data: [
                            {
                                value: 50,
                                name: 'SCORE'
                            }
                        ]
                    }
                ]
             })
        }
    }

}
</script>

<style>
.line{
    flex:initial
}
</style>