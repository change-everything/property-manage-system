<!--
 @author: 孙明君
 @date: 2022-06-09
 -->
<template>
    <el-dialog v-model="dialogVisable" title="部门信息添加">
        <el-form v-model="unitInfos" :rules="rules" ref="unitInfos">
            <el-form-item label="部门名称" prop="depName">
                <el-input v-model="unitInfos.depName" />
            </el-form-item>
            
            <el-form-item label="部门编号" prop="depNum">
                <el-input v-model="unitInfos.depNum" />
            </el-form-item>

            
            <el-form-item label="部门电话" prop="depTelNum">
                <el-input v-model="unitInfos.depTelNum" />
            </el-form-item>
            <el-button @click="add">添加</el-button>
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
            rules:{
                depName:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                depNum:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                depTelNum:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}]
            }
        }
    },

    methods:{
        
        add(){
            depApi.insert(this.unitInfos).then((res)=>{
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
        }
    }
}
</script>