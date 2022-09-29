<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <el-dialog title="角色权限分配" v-model="dialogVisable" width="300px" top="10vh">
        <el-tree
            :data="tree"
            show-checkbox
            node-key="permId"
            default-expand-all
            highlight-current
            :props="defaultProps"
            :check-strictly="true"
            ref="treeRef"

            v-loading="loading"
        />

        <el-button type="primary" @click="changePerm">分配</el-button>
    </el-dialog>
</template>

<script>
import publicApi from '@/api/publicApi.js'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            roleId:'',
            tree:[],
            defaultProps: { children: "children", label: "permName",disabled: 'disabled'},
        }
    },

    methods:{
        showDialgog(roleId){
            this.dialogVisable = true;
            
            this.roleId = roleId;
            // console.log(roleId)
            this.getAllPerm();
            this.getRoleDetailPerm(roleId);
        },

        getAllPerm(){
            publicApi.getPerrment().then((res)=>{
                this.tree = res.data;
            })
        },

        getRoleDetailPerm(id){
            publicApi.getPermDetail(id).then((res)=>{
                // console.log(res);
                this.$refs.treeRef.setCheckedKeys(res.data, false)
            })
        },

        changePerm(){
            publicApi.commitPerm({roleId:this.roleId,perms:this.$refs.treeRef.getCheckedKeys()}).then((res)=>{
                if(res.success == true){
                    ElNotification({
                        message: '权限修改成功，重新登陆后生效',
                        type: 'success',
                        duration:'1000',
                        showClose:false
                    });
                }else{
                    ElNotification({
                        message: '权限修改失败，请稍后再试',
                        type: 'error',
                        duration:'1000',
                        showClose:false
                    });
                }
                this.reload();
            })

            // console.log(this.$refs.treeRef.getCheckedKeys());
            
        }
    }
}
</script>