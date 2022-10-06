<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div>  
        <el-dialog v-model="dialogVisable" style="height:40vh" width="600px" title="员工信息修改" @closed="this.$refs.empsInfo.resetFields();"> 
            <el-form v-model="empsInfo" :label-width="100" label-position="left" :rules="rules" ref="empsInfo" v-loading="loading" >
            <el-form-item label="员工编号" prop="empNum">
                <el-input v-model="empsInfo.empNum" style="width:400px" readonly />    
            </el-form-item>

            <el-form-item label="姓名" prop="empName">
                <el-input v-model="empsInfo.empName" style="width:400px" />
            </el-form-item>

            <el-form-item label="出生年月" prop="birthDate">
                <el-date-picker v-model="empsInfo.birthDate" type="date" placeholder="请选择出生日期" style="width:400px"/>
            </el-form-item>

            <el-form-item label="性别" prop="gender">
                <el-select v-model="empsInfo.gender" placeholder="请选择性别" style="width:400px">
                    <el-option label="男" value="1" />
                    <el-option label="女" value="2" />
                </el-select>
            </el-form-item>

            <el-form-item label="住址" prop="address">
                <el-input v-model="empsInfo.address" style="width:400px" />    
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
                <el-input v-model="empsInfo.phone" style="width:400px" /> 
            </el-form-item>
            <el-button @click="update" type="primary">修改</el-button>
        </el-form>
        </el-dialog>
    </div>
</template>

<script>
// import axios from 'axios'
import checkFunc from '@/utils/check.js'
import empApi from '@/api/employeeApi.js'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            empsInfo:{
                roleId:-1
            },
            dialogVisable:false,
            zhiwu:{},
            rules:{
                empName:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                gender:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                address:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                phone:[{required: true, validator: this.validatePhone, trigger: 'blur'}],
                birthDate:[{required: true, validator: this.validateBorn, trigger: 'blur'}],
                empPwd:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
            },
            loading:false
        }
    },
    methods:{
        
        showUpDialog(num){
            this.dialogVisable = true;
            this.loading = true;
            empApi.detail(num).then((res)=>{
                // console.log(res)
                this.empsInfo = res.data;
                this.loading = false;
            })
        },

        update(){
            console.log(this.empsInfo)
            delete this.empsInfo.departmentInfo;
            empApi.updateInfo(this.empsInfo).then((res)=>{
                // console.log(res)
                if(res.data == true){
                    ElNotification({
                        message: '更新成功',
                        type: 'success',
                        duration:'1000',
                        showClose:false
                    })
                }else{
                    ElNotification({
                        message: '更新失败',
                        type: 'error',
                        duration:'1000',
                        showClose:false
                    })
                }
                this.reload();
            })
        },
        //随机生成密码
        radomPwd(){
            let e = e || 8;
            var t = "ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz0q23456789@#.",
            a = t.length,
            n = "";
            for (let i = 0; i < e; i++) n += t.charAt(Math.floor(Math.random() * a));
            this.empsInfo.empPwd = n;
        },



        //表单校验函数
        validatePhone(rule, value, callback){
            if (value==null ||  value === "") {
                callback(new Error("必填字段,不能为空"));
            }else {
                if(checkFunc.thePhoneIsTure(value)){
                    callback();
                }else{
                    callback(new Error("请输入正确电话号码"))
                }
            }
        },

        validateBorn(rule, value, callback){
            if (value==null ||  value === "") {
                callback(new Error("必填字段,不能为空"));
            }else {
                let nowDate = new Date();
                let publishDate = new Date(value);
                if (publishDate > nowDate) {
                    callback(new Error("不允许大于当前时间"));
                } else {
                    callback();
                } 
            }
        }
    },
    mounted(){
       
    }
}
</script>