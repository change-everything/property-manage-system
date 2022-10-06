<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
 <!-- background-color="#0066CC" -->
<template>
    <el-menu 
        style="width:100%;height:100%;color:white" 
        active-text-color="#ffd04b"
        text-color="white"
        background-color="#000000"
        :default-active="this.$route.path"
        router
        >
        <el-menu-item index="/owner">
            <template #title>
                <el-icon><Avatar /></el-icon>
                业主信息
            </template>
        </el-menu-item>

        <el-sub-menu index="2">
            <template #title>
                <el-icon><Coin /></el-icon>
                缴费处理
            </template>
            <el-menu-item index="/collectfees">
                <template #title>
                    <el-icon><Ticket /></el-icon>
                    缴费处理
                </template>
            </el-menu-item>
            <el-menu-item index="collectfeeshostily">
                <template #title>
                    <el-icon><Tickets /></el-icon>
                    历史缴费
                </template>
            </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="3">
            <template #title>
                <el-icon><OfficeBuilding /></el-icon>
                人事管理
            </template>
            <el-menu-item index="/employee">
                <template #title>
                    <el-icon><User /></el-icon>
                    员工信息
                </template>
            </el-menu-item>
            <el-menu-item index="/role">
                <template #title>
                    <el-icon><Postcard /></el-icon>
                    角色信息
                </template>
            </el-menu-item>
            <el-menu-item index="/unit">
                <template #title>
                    <el-icon><School /></el-icon>
                    部门信息
                </template>
            </el-menu-item>
        </el-sub-menu>

        <el-menu-item index="/systemParams">
            <template #title>
                <el-icon><SetUp /></el-icon>
                系统参数
            </template>
        </el-menu-item>
    </el-menu>
</template>
<script>
import {ElMenu,ElMenuItem,ElSubMenu} from 'element-plus'

export default {
    components:{
        ElMenu,ElMenuItem,ElSubMenu
    },
    data(){
        return{

        }
    },

    methods:{
        handleOpen(key,keyPath){
            alert(key,keyPath)
        },

        handleClose(key,keyPath){
            alert(key,keyPath)
        }
    }
}
</script>