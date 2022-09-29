<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
  <div style="display:inline-flex">
    <div>
        <el-card style="width:600px;"  body-style="padding:0px">
          <pie />
        </el-card>

        <el-card style="width:600px;margin-top:50px" body-style="padding:0px">
          <people />
        </el-card>
    </div>

    <div style="margin-left:50px">
        <el-card style="width:600px;height:340px" body-style="padding:0px">
          <template #header>
            代办事项
          </template>

          <div>
            <el-scrollbar max-height="300px">
              <ul>
                <li style="padding-top:10px" v-for="item in list" :key="item">{{item}}</li>
              </ul>
            </el-scrollbar>
          </div>
        </el-card>
        <el-card style="width:600px;margin-top:10px" body-style="padding:0px">
          <charge-count />
        </el-card>
    </div>
    
  </div>
</template>

<script>
import pie from '@/components/charts/pie.vue'
import people from '@/components/charts/categoryPeople.vue'
import chargeCount from '@/components/charts/chargeCount.vue'
export default {
  name: 'HelloWorld',
  components:{pie,people,chargeCount},
  props: {
    msg: String
  },
  data(){
    return{
      list:['list item 1','list item 2','list item 3','list item 4']
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
