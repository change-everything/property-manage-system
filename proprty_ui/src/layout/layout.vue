<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <el-container style="height:100vh">
            <el-header style="background:#000000; height:8%;"><Header /></el-header>
            <el-container>
                <el-aside width="13vw"><Aside /></el-aside>
                <el-main >
                    <breadcrumb />
                    <el-divider />
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
  </div>
</template>

<script>
import {ElContainer,ElAside,ElHeader,ElMain} from 'element-plus'
import Header from './header.vue'
import Aside from './aside.vue'
import breadcrumb from './breadcrumb.vue'
export default {
    components: {
        ElContainer,ElAside,ElHeader,ElMain,Aside,Header,breadcrumb
    },
    
}
</script>