<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <div class="form_board">
            <div style="width:360px;float:right;margin-right:80px;margin-top:110px;">
                <el-form
                    label-width="70px"
                    label-position="left"
                    :model="loginForm"
                    :rules="rules"
                    ref="loginForm"
                    style="max-width: 380px;">

                    <el-form-item label="用户名" prop="username" style="color:black;">
                        <el-input  v-model="loginForm.username" placeholder="请输入用户名" />
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input v-model="loginForm.password" @focus="pwdHasFocus" @blur="pwdLoseFocus" :show-password="true" type="password" placeholder="请输入密码" />
                    </el-form-item>
                    <el-row>
                        <el-col :offset="18">
                            <input type="checkbox" name="remember-me" title="记住我" />自动登录<br/>
                            <!-- <el-checkbox :true-label="true" :false-label="false" v-model="rememberMe">记住密码</el-checkbox> -->
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :offset="12">
                            <el-button type="primary" size="large" @click="login">登录</el-button>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
        </div>


        <!-- 云彩背景 -->
        <!-- <div class="cloud_bg background_entiry">
            <img src="../images/bg.png" style="position:absolute;left: 0;top: 0;height: 100%;width: 150%;z-index:-10000">
            <img src="../images/cloud_two.png" style="z-index:-10000" class="cloud_move_two">
            <img src="../images/cloud_one.png" style="z-index:-10000">
            <img src="../images/cloud.png" class="cloud_move">
        </div> -->

        <div class="cloud_bg">
            <img src="../images/groud2.png" style="position:absolute;left: 0;
            top: 0;height: 100%;width: 100%;z-index:-10000"/>
        </div>

        <div>
            <!-- 动画猫头鹰 -->
            <img src="../images/maotou.png" style="position:absolute;z-index:-502;top:180px;left:570px">

            <!-- 圆手 -->
            <img src="../images/hand.png" v-show="isNotPwd" style="position:absolute;z-index:-502;top:250px;left:530px">
            <img src="../images/hand.png" v-show="isNotPwd" style="position:absolute;z-index:-502;top:250px;left:680px">

            <!-- 手掌 -->
            <img src="../images/hand_two.png" v-show="isPwd" style="position:absolute;z-index:-502;top:230px;left:580px;transform: rotateY(180deg)">
            <img src="../images/hand_two.png" v-show="isPwd"  style="position:absolute;z-index:-502;top:230px;left:625px;transform: rotateZ(-10deg)">

        </div>


        
        
    </div>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElButton,ElNotification} from 'element-plus'
import publicApi from '@/api/publicApi.js'
export default {
    components:{
        ElForm,ElFormItem,ElInput,ElButton
    },
    data(){
        return {
            background: {
                // 背景图片地址
                backgroundImage: 'url(' + require('../images/download_userLogin.png') + ')',
                // 背景图片是否重复
                backgroundRepeat: 'no-repeat',
            },
            loginForm:{
                username:'10001',
                password:'root',
            },
            isPwd:false,
            isNotPwd:true,
            rules:{
                username:[{required: true, message: '用户名不允许为空', trigger: 'blur'}],
                password:[{required: true, message: '密码不允许为空', trigger: 'blur'}]
            },
            rememberMe:true
        }
    },
    methods:{
        login(){
            var self = this;
            self.$refs.loginForm.validate((valid) => {
                if(valid){
                    publicApi.login(this.loginForm).then((res)=>{
                        if(res.success == true){
                            console.log(res)

                            let data = {
                                userInfo:{
                                    userId:res.data.empId,
                                    userName:res.data.empName,
                                    userNum:res.data.empNum,
                                    role:res.data.role
                                },
                                permCode:res.data.permCode
                            }
                            
                            //用vuex存储用户信息,token信息,menu信息
                            this.$store.dispatch('users/LoginSuccess',data)
                            this.$store.dispatch('users/SaveToken',res.data.token)
                            //用session存储token                                        
                            sessionStorage.setItem('setToken',JSON.stringify(res.data.token))

                            ElNotification({
                                message: '登录成功',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            })

                            this.$router.push('/')
                        }else{
                            if(res.statusCode === 2003 || res.statusCode === 2007){
                                this.loginForm = {};
                                ElNotification({
                                    message: '用户名或密码错误',
                                    type: 'error',
                                    duration:'1500',
                                    showClose:false
                                })
                            }
                        }
                    })
                }else{
                    return false;
                }
            })
        },
        pwdHasFocus(){
            this.isPwd = true;
            this.isNotPwd = false;
        },
        pwdLoseFocus(){
            this.isPwd = false;
            this.isNotPwd = true;
        }
    }
}
</script>


<style scoped>
.form_board {
    height: 320px;
    width: 690px;
    float: left;
    margin-left: 500px;
    margin-top: 260px;
    position:absolute;
    z-index: -100;
    background-color: whitesmoke;
    border-radius: 20px;
    opacity: 0.8;
}

.background_entiry{
    background:#2494F2;
    height:100vh;
    width:100vw;
}

.cloud_bg{
    position: absolute;
    overflow: hidden;
    z-index: -1000000000;
    height: 100%;
    width: 100%;
}


@keyframes move {
  0% {margin-left: 0; }
  100% {margin-left: 3000px;}
}

@keyframes move_two {
  0% {margin-left: -20; }
  100% {margin-left: 1000px;}
}

.cloud_move{
    z-index:-10000;
    /* animation: move 120s infinite */
}

.cloud_move_two{
    z-index:-10000;
    animation: move_two 100s infinite
}
</style>