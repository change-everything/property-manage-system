<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <div class="img_card">
            <img src="../images/login_img.png" style="width:85%;height:85%">
        </div>
        <div class="form_board">
            <div style="width:360px;float:right;margin-right:80px;margin-top:80px;">
                <el-form
                    :model="loginForm"
                    :rules="rules"
                    ref="loginForm"
                    style="max-width: 400px;">

                    <el-form-item prop="username" style="color:black;">
                        <el-input style="height:50px" :prefix-icon="UserFilled"  v-model="loginForm.username" size="large" placeholder="请输入用户名" >
                            <template #prefix><el-icon size="30px"><User /></el-icon></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item  prop="password">
                        <el-input style="height:50px" v-model="loginForm.password" size="large" @focus="pwdHasFocus" @blur="pwdLoseFocus" :show-password="true" type="password" placeholder="请输入密码" >\
                            <template #prefix><el-icon size="30px"><Lock /></el-icon></template>
                        </el-input>
                    </el-form-item>
                    <!-- <el-row>
                        <el-col :offset="18">
                            <input type="checkbox" name="remember-me" title="记住我" />自动登录<br/>
                            </el-col>
                    </el-row> -->
                    <el-row style="margin-top:15px">
                        <el-col >
                            <el-button style="width:360px;height:50px" type="primary" size="large" @click="login">登录</el-button>
                        </el-col>
                    </el-row>
                     <el-row style="margin-top:10px">
                        <el-col >
                            <loginFace />
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
            <img src="../images/backgroud.png" style="position:absolute;left: 0;
            top: 0;height: 100%;width: 100%;z-index:-10000"/>
        </div>

        <!-- <div> -->
            <!-- 动画猫头鹰 -->
            <!-- <img src="../images/maotou.png" style="position:absolute;z-index:-502;top:180px;left:570px"> -->

            <!-- 圆手 -->
            <!-- <img src="../images/hand.png" v-show="isNotPwd" style="position:absolute;z-index:-502;top:250px;left:530px">
            <img src="../images/hand.png" v-show="isNotPwd" style="position:absolute;z-index:-502;top:250px;left:680px"> -->

            <!-- 手掌 -->
            <!-- <img src="../images/hand_two.png" v-show="isPwd" style="position:absolute;z-index:-502;top:230px;left:580px;transform: rotateY(180deg)">
            <img src="../images/hand_two.png" v-show="isPwd"  style="position:absolute;z-index:-502;top:230px;left:625px;transform: rotateZ(-10deg)"> -->

        <!-- </div> -->


        
        
    </div>
</template>
<script>
import {ElForm,ElFormItem,ElInput,ElButton,ElNotification} from 'element-plus'
import publicApi from '@/api/publicApi.js'
import loginFace from '@/components/login/loginFace.vue'
import {User,Lock} from '@element-plus/icons-vue'
export default {
    components:{
        ElForm,ElFormItem,ElInput,ElButton,loginFace,User,Lock
    },
    data(){
        return {
            background: {
                // 背景图片地址
                // backgroundImage: 'url(' + require('../images/backgroud.png') + ')',
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
    margin-left: 44%;
    margin-top: 16%;
    position:absolute;
    z-index: 10;
    /* background-color: whitesmoke;
    border-radius: 20px;
    opacity: 0.8; */
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

.img_card{
    position: absolute;
    z-index: -1000;
    margin-left: 15%;
    margin-top: 7%;
}
</style>