<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div class="main">
      <img src="@/images/403.png" style="width:600px;height:500px" />

      <div class="text">
        
        <div style="font-size:35px">
          无访问权限，请联系管理员!!!
        </div>

        <div style="margin-left:200px;margin-top:50px">
          <el-button type="success" @click="this.$router.push('/')">返回首页</el-button>
        </div>
      </div>
    </div>
</template>

<style scoped>
.main{
  height: 90%;
  width: 100%;
  display:flex;
  align-items:center;
  justify-content: center; 
}


.text{
  margin-left: 200px;
}
</style>