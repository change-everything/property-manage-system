<!--
 @author: 孙明君
 @date: 2022-06-09
 -->
<template>
    <div>
        <el-dialog v-model="dialogVisable" title="员工职位调整">
        <div style="display:inline-flex" v-loading="loading">
            <div style="display:inline-flex;margin-left:20px">
                <label style="margin-right:5px">新职位:</label>
                <el-select v-model="empInfo.roleId">
                    <el-option v-for="item in zhiWus" :key="item.roleId" :label="item.roleDest" :value="item.roleId" />
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
// import axios from 'axios'
import empApi from '@/api/employeeApi.js'
import roleApi from '@/api/roleApi.js'
import { ElNotification } from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            empInfo:{},
            zhiWuChangeBefore:'',
            loading:false,
            zhiWus:[],
            unitId:''
        }
    },
    methods:{
        showDialog(id,unitId){
            this.dialogVisable = true;
            this.unitId = unitId;
            this.getZhiwei(unitId);
            
            empApi.detail(id).then((res)=>{
                // console.log(res)
                this.empInfo = res.data;
                // this.zhiWuChangeBefore = res.data.job;
            })
        },

        // 获得职位表 
         getZhiwei(id){
             this.loading = true;
            //  console.log(id);
            roleApi.select({depNum:id}).then((res)=>{
                
                this.zhiWus = res.data;
                
                // console.log(this.zhiWus);
                 this.loading = false;
            })
         },

         //确认修改
         ensureChange(){
             delete this.empInfo.departmentInfo;
             this.empInfo.empDepNum = this.unitId;
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
    }
}
</script>