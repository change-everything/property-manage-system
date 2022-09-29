<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <el-dialog title="部门角色添加" v-model="dialogVisable" width="400px">
        <el-form v-model="roles">
            <el-form-item label="角色名称">
                <el-input v-model="roles.roleName" placeholder="请输入角色（职务）名称" />
            </el-form-item>
            <el-form-item label="角色描述">
                <el-input v-model="roles.roleDest" placeholder="请输入角色描述" />
            </el-form-item>
            <el-button @click="add">添加</el-button>
        </el-form>
    </el-dialog>
</template>
<script>
import roleApi from '@/api/roleApi.js'
import {ElNotification} from 'element-plus'

export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            depNum:'',
            roles:{}
        }
    },

    methods:{
        showDialgog(depNum){
            this.dialogVisable = true;
            this.depNum = depNum;
            // console.log(unitId)
        },

        add(){
            this.roles.depNum = this.depNum;

            roleApi.insert(this.roles).then((res)=>{
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
                // console.log(res);
            })
        }
    }
}
</script>