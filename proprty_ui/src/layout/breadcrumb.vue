<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
  <el-breadcrumb separator-class="el-icon-arrow-right" style="height:5px">
    <!-- <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item> -->
    <el-breadcrumb-item v-for="(item, index) in breadList" :key="index"
    :to="{ path: item.path }">
      {{item.meta.title}}
        </el-breadcrumb-item>
  </el-breadcrumb>
</template>
<script>
export default {
 // 初始化数据对象
  name: "bread",
  data() {
    return {
      breadList: []
    };
  },
  mounted(){
    // console.log(this.$route)
    this.getBreadList(this.$route)
  },
 // 监听属性
  watch: {
    // 监听路由
    $route(val) {
      // console.log(val)
      // 调用获取路由数组方法
      this.getBreadList(val);
    },
  },
  // 自定义事件
  methods: {
    /**
     * @description 获取路由数组
     * @params val 路由参数
     * @author tw
     */
    getBreadList(val) {
      // 过滤路由matched对象
        this.breadList = val.matched;
    }
  }
}
</script>
<style scoped>
.el-breadcrumb {
    height: 100%;
    size: 160px;
    display:flex;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: nowrap;
}
</style>

