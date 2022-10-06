<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template >
  <div>
    <div style="display:inline-flex">
        <el-card style="width:650px;height:150px" class="card">
          <span class="iconfont size_line" style="color:green;">&#xe638;</span>物业信息
        </el-card>

        <el-card style="width:250px;height:150px;margin-left:10px;" class="card center_">
          <span class="iconfont size_" style="color:#e49632;margin-right:10px">&#xe63a;</span>故障报修
        </el-card>
        <el-card style="width:250px;height:150px;margin-left:10px" class="card center_">
          <span class="iconfont size_" style="color:#86147d;margin-right:10px">&#xe644;</span>房屋租赁
        </el-card>
        <el-card style="width:250px;height:150px;margin-left:10px" class="card center_">
          <span class="iconfont size_" style="color:#3f96d7;margin-right:10px">&#xe600;</span>费用缴纳
        </el-card>
    </div>

    <div style="display:inline-flex;">
      <div>
        <div style="display:inline-flex;margin-top:10px">
          <el-card style="width:400px;height:290px;" class="card">
              <span class="iconfont size_line" style="color:green;">&#xe638;</span>房屋统计
              <pie />
          </el-card>

          <el-card style="width:700px;height:290px;margin-left:10px;" class="card">
              <span class="iconfont size_line" style="color:green;">&#xe638;</span>费用统计
          </el-card>
        </div>

        <div style="display:inline-flex;margin-top:10px">
          <el-card style="width:400px;height:290px;" class="card">
              <span class="iconfont size_line" style="color:green;">&#xe638;</span>满意度
              <people />
          </el-card>

          <el-card style="width:700px;height:290px;margin-left:10px" class="card">
            <span class="iconfont size_line" style="color:green;">&#xe638;</span>工单统计
              <chargeCount />
          </el-card>
        </div>
      </div>

      <div>
        <el-card style="width:320px;height:590px;margin-top:10px" class="card">
          <span class="iconfont size_line" style="color:green;">&#xe638;</span>系统公告
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import pie from '@/components/charts/pie.vue'
import people from '@/components/charts/categoryPeople.vue'
import chargeCount from '@/components/charts/chargeCount.vue'
import '../assets/iconfont/iconfont.css';
export default {
  name: 'HelloWorld',
  components:{chargeCount,people,pie},
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
.card{
  border-radius: 10px;
}


.size_{
  font-size: 35px;
}

.size_line{
  font-size: 20px;
}

.center_{
  display: flex;
  flex-direction:column;
  justify-content: center;
}
</style>
