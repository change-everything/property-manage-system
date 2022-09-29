<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div style="display:inline-flex;width:100vw;height:100%">
        <div style="position: absolute;top:10px;left:30px;" @click="this.$router.push('/')">
            <img src="@/assets/logo_my.png" style="width:45px;height:45px">
            <span style="color:white">&nbsp;&nbsp;&nbsp;物业管理系统</span>
        </div>

        <!-- <div style="position: absolute;right:500px;top: 35px;color:white;font-size:17px">
            设置
        </div> -->

        <div style="position: absolute;right: 60px;top: 20px;color:white;">
            <div>
                当前登录用户：
                <el-dropdown trigger="click" @visible-change="change">
                    <span style="color:white;font-size:15px;font-widden:bord">
                        {{this.$store.state.users.userInfo.userName}} &nbsp;
                        <el-icon>
                            <arrow-down :class="listChage" />
                        </el-icon>
                    </span>
                    <template #dropdown style="color:white;font-size:17px">
                        <el-dropdown-menu>
                            <el-dropdown-item @click="this.$router.push('/systemSet')">
                                <el-icon><Setting /></el-icon>
                                系统参数设置
                            </el-dropdown-item>
                            <el-dropdown-item @click="unlogin">
                                <el-icon><SwitchButton /></el-icon>
                                退出登录
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
            <div>
                当前角色：
                <span style="color:white;font-size:15px;font-widden:bord">
                    {{this.$store.state.users.userInfo.role}} &nbsp;
                </span>
            </div>
        </div>
    </div>
</template>
<script>
import publicApi from '@/api/publicApi.js'
import {ElNotification} from 'element-plus'


export default {
    data(){
        return{
            // userName:'',
            down:false,
            listChage:'arrowTransformReturn'
        }
    },

    methods:{
        unlogin(){
            publicApi.logOut().then((res)=>{
                if(res.success == true){
                    this.$store.dispatch('users/LoginExit');
                    this.$router.replace({path: '/login'});

                    ElNotification({
                            message: res.statusMsg,
                            type: 'success',
                            duration:'1000',
                            showClose:false
                    })
                }
            });
        },
        change(tag){
            if(tag){
                this.listChage = 'arrowTransform';
            }else{
                this.listChage = 'arrowTransformReturn';
            }
        }
    },
}
</script>

<style scoped>

.arrowTransform {
    transition: 0.2s;
    transform-origin: center;
    transform: rotateZ(180deg);
}

.arrowTransformReturn {
    transition: 0.2s;
    transform-origin: center;
    transform: rotateZ(0deg);
}

</style>