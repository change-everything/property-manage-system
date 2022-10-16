<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div id="car" style="width:100%;height:200px"></div>
</template>

<script>
import * as echarts from 'echarts';
import commonApi from '../../api/commonApi'
export default {
  data(){
    return{
      pieData:[]
    }
  },

  methods:{
    async getData(){
      await commonApi.getCommunity().then(res =>{
         this.pieData.push({'name':"出租率",'value':res.data.rentalRate})
         this.pieData.push({'name':"入住率",'value':res.data.occupancyRate})
         this.pieData.push({'name':"空置率",'value':res.data.vacancyRate})


         /**
          * 动态数据填充
          */
         echarts.init(document.getElementById('car')).setOption({
           series:[
             {
               data:this.pieData
             }
           ]
         })
       })
    },

    initCard(){
      echarts.init(document.getElementById('car')).setOption({
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b}({d}%)'
          },
        series: [
            {
                type: 'pie',
                radius: '90%',
                stillShowZeroSum: false,
                center: ['60%', '50%'],
                data:[],
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
    this.getData();
    this.initCard();
  }
}

</script>