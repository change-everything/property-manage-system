<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>  
        <el-button @click="showAddDialog" type="success"><el-icon><Plus /></el-icon>添加</el-button>
        <el-dialog v-model="dialogVisable" style="height:40vh" width="600px" title="员工信息添加" @closed="this.$refs.empsInfo.resetFields();">
            <el-form :model="empsInfo" :label-width="100" label-position="left" :rules="rules" ref="empsInfo">
            <el-form-item label="员工编号" prop="empNum">
                <el-input v-model="empsInfo.empNum" style="width:400px" readonly placeholder="选择部门后自动生成"  />    
            </el-form-item>

            <el-form-item label="姓名" prop="empName" >
                <el-input v-model="empsInfo.empName" style="width:400px" placeholder="请输入姓名" /> 
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
                <el-input v-model="empsInfo.address" style="width:400px" placeholder="请输入当前住址" />   
            </el-form-item>
            <el-form-item label="联系电话" prop="phone">
                <el-input v-model="empsInfo.phone" style="width:400px" placeholder="请输入联系电话" />
            </el-form-item>

            <el-form-item label="所在部门" prop="empDepNum">
                <el-select v-model="empsInfo.empDepNum" placeholder="请选择部门" style="width:400px" @change="getZhiwuByUnit">
                    <el-option v-for="item in units"
                        :key="item.depNum"
                        :label="item.depName"
                        :value="item.depNum"
                     />
                </el-select>
            </el-form-item>

            <el-form-item label="职务" prop="roleId">
                <el-select v-model="empsInfo.roleId" placeholder="请选择职务" style="width:400px" no-data-text="请先选择部门">
                    <el-option v-for="item in zhiwu" 
                        :key="item.roleId"
                        :label="item.roleDest"
                        :value="item.roleId"
                    />
                </el-select>
            </el-form-item>

            <el-form-item label="密码" prop="empPwd">
                <el-input v-model="empsInfo.empPwd" style="width:400px" placeholder="请设置登录密码" /><el-button @click="radomPwd">随机</el-button>  
            </el-form-item>

            <el-button @click="add" type="primary">添加</el-button>
        </el-form>
        </el-dialog>
    </div>
</template>

<script>
// import axios from 'axios'
import checkFunc from '@/utils/check.js'
import deptApi from '@/api/depApi.js'
import empApi from '@/api/employeeApi.js'
import roleApi from '@/api/roleApi.js'
import {ElNotification} from  'element-plus'
export default {
    inject: ["reload"],
    data(){
        return{
            empsInfo:{},
            dialogVisable:false,
            zhiwu:{},
            rules:{
                empName:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                gender:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                address:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                empDepNum:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                phone:[{required: true, validator: this.validatePhone, trigger: 'blur'}],
                birthDate:[{required: true, validator: this.validateBorn, trigger: 'blur'}],
                roleId:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                empPwd:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
            }
        }
    },
    methods:{
        
        showAddDialog(){
            this.dialogVisable = true;
        },
        add(){
            empApi.insertInfo(this.empsInfo).then((res)=>{
                if(res.success == true){
                    ElNotification({
                        message: '添加成功',
                        type: 'success',
                        duration:'1000',
                        showClose:false
                    })
                }else{
                    ElNotification({
                        message: '添加失败',
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

        //获取部门信息
        getUnitInfo(){
            deptApi.select().then((res)=>{
                console.log(res);
                this.units = res.data;
            })
        },

        //根据部门获取职位信息
        getZhiwuByUnit(){
            this.radomNum();

            roleApi.select({depNum:this.empsInfo.empDepNum}).then((res)=>{
                
                this.zhiwu = res.data;
            })
        },

        //随机数编号
        radomNum(){
            var num = "";   //定义用户编号
            for (var i = 0; i < 8; i++) //4位随机数，用以加在时间戳后面。
            {
                num += Math.floor(Math.random() * 10);
            }
            num = new Date().getTime() & num;  //时间戳，用来生成用户编号。
            // for(let i=0;i<this.units.length;i++){
            //     if(this.units[i].unitId == this.empsInfo.empDepNum){
            //         num = this.units[i].code + num;
            //         break;
            //     }
            // }
            this.empsInfo.empNum = num;
        },


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
        this.getUnitInfo();
    }
}
</script>