<!--
 @author: 孙明君
 @date: 2022-06-09
 -->
<template>
    <div>
        <el-dialog v-model="dialogVisable" title="员工部门调整">
        <div style="display:inline-flex" v-loading="loading">
            <div style="display:inline-flex;">
                <label style="width:80px">原部门:</label>
                <el-input v-model="unitMoveBefore" readonly />
            </div>

            <div style="display:inline-flex;margin-left:20px">
                <label style="margin-right:5px">新部门:</label>
                <el-select v-model="empInfo.empDepNum">
                    <el-option v-for="item in units" :key="item.depNum" :label="item.depName" :value="item.depNum"/>
                </el-select>
            </div>
        </div>
        <div>
            <el-button @click="ensureChange">调整</el-button>
        </div>
    </el-dialog>
    </div>
</template>
<script>

import empApi from '@/api/employeeApi.js'
import deptApi from '@/api/depApi.js'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            empInfo:{},
            unitMoveBefore:'',
            empUnit:'',
            loading:false
        }
    },
    methods:{
        showDialog(id,depName){
            this.dialogVisable = true;
            this.unitMoveBefore = depName;
            // console.log(id);
            empApi.detail(id).then((res)=>{
                console.log(res)
                this.empInfo = res.data;
            })
        },
        //获得部门表
        getUnit(){
            this.loading = true;
            deptApi.select().then((res)=>{
                // console.log(res);
                this.units = res.data;
                this.loading = false;
            })
        },

         //确认修改
         ensureChange(){

             delete this.empInfo.departmentInfo;
             
             empApi.updateInfo(this.empInfo).then((res)=>{
                if(res.success == true){
                     ElNotification({
                        message: '修改成功',
                        type: 'success',
                        duration:'1000',
                        showClose:false
                    })
                }else{
                     ElNotification({
                        message: '修改失败',
                        type: 'error',
                        duration:'1000',
                        showClose:false
                    })
                }
                this.reload();
             })
         }
    },

    mounted(){
        this.getUnit();
    }
}
</script>