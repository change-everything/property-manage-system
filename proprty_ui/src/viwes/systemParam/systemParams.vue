<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <el-tabs v-model="activeName" tabPosition="left"  class="tabs" @tab-click="handleClick" stretch>
        <el-tab-pane label="收费标准" name="/charges"><router-view /></el-tab-pane>
        <el-tab-pane label="系统设置" name="/systemSet"><router-view /></el-tab-pane>
    </el-tabs>
</template>

<script>
export default {
    data(){
        return{
            activeName:'/charges'
        }
    },
    methods:{
        handleClick(TabsPaneContext){
            this.$router.push(TabsPaneContext.props.name)
        }
    },

    mounted(){
        this.$router.push('/charges')
    }
}
</script>

<style scoped>
/* .tabs{
    height: 100%;
    width: 100%;
} */
</style>