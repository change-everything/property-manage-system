<!--
 @author: 孙明君
 @date: 2022-06-09
 -->
<template>
    <el-dialog v-model="dialogVisable" title="部门信息修改">
        <el-form v-model="unitInfos" v-loading="loading">
            <el-form-item label="部门名称">
                <el-input v-model="unitInfos.depName" />
            </el-form-item>
            
            <el-form-item label="部门编号">
                <el-input v-model="unitInfos.depNum" />
            </el-form-item>

            <el-form-item label="部门主管">
                <el-input v-model="unitInfos.depHead" />
            </el-form-item>

            <el-form-item label="部门电话">
                <el-input v-model="unitInfos.depTelNum" />
            </el-form-item>
            <el-button @click="update">修改</el-button>
        </el-form>
    </el-dialog>
</template>
<script>
// import axios from 'axios'
import depApi from '@/api/depApi.js'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            unitInfos:{},
            loading:false
        }
    },

    methods:{
        showDialog(id){
            this.dialogVisable = true;
            this.getUnitInfo(id);
        },

        getUnitInfo(id){
            this.loading = true;
            depApi.detail(id).then((res)=>{
                this.unitInfos = res.data
                this.loading = false;
            })
        },

        update(){
            depApi.update(this.unitInfos).then((res)=>{
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