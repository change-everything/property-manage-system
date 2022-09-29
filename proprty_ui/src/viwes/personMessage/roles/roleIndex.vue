<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>

        <el-radio-group v-model="radio1" size="large">
            <el-radio-button label="树形结构" />
            <el-radio-button label="表格视图" />
        </el-radio-group>

        
        <role-add ref="roleAdd" />
        <role-dist ref="roleDist" />
        <el-table :data="tableData" style="width: 100%" @expand-change="getRoleInfo" height="80vh" :show-header="false" v-loading="loading">
            <el-table-column type="expand">
                <!-- <template #default="props"> -->
                    <el-table :data="roles" style="margin-left:50px;width:1200px" max-height="400px" :show-header="false" v-loading="loadingExpand">
                    <el-table-column :width="150"  type="index"/>
                    <el-table-column :width="250" prop="roleDest" />
                    
                    <el-table-column >
                        <template #default="scope">
                            <el-button link type="primary" size="small" @click="this.$refs.roleDist.showDialgog(scope.row.roleId);">分配权限</el-button>
                            <el-button link type="primary" size="small" @click="deleteRole(scope.row.roleId)">删除角色</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!-- </template> -->
            </el-table-column>
            <el-table-column prop="depName" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="this.$refs.roleAdd.showDialgog(scope.row.depNum)">添加角色</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
// import axios from 'axios'
import roleAdd from './roleAdd.vue'
import roleDist from './roleDist.vue'
import {ElMessageBox,ElNotification} from 'element-plus'
import deptApi from '@/api/depApi.js'
import roleApi from '@/api/roleApi.js'
export default {
    components:{roleAdd,roleDist},
    data(){
        return{
            tableData:[],
            roles:[],
            radio1:'表格视图',
            loading:false,
            loadingExpand:false
        }
    },

    methods:{
        //获取部门
        //获取部门信息
        getUnitInfo(){
            this.loading = true;
            deptApi.select().then((res)=>{
                this.tableData = res.data;
                // console.log(res)
                this.loading = false;
            })
        },

        //获取对应职位（动态加载）
        getRoleInfo(row,tag){
            if(tag.length == 0){
                return;
            }else{
                this.currentUnitId = row.unitId;
                this.loadingExpand = true;
                roleApi.select({depNum:row.depNum}).then((res)=>{
                    this.roles = res.data;
                    // console.log(res)
                    this.loadingExpand = false;
                })
            }
        },

        deleteRole(id){
            console.log(id);
            ElMessageBox.confirm(
                '您确定要删除该角色吗？该操作不可逆',
                '警告',
                {
                    confirmButtonText: '删除',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                .then(() => {

                    roleApi.delete(id).then((res)=>{
                        if(res.success == true){
                            ElNotification({
                                message: '删除成功',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            });
                        }else{
                            ElNotification({
                                message: '删除失败',
                                type: 'error',
                                duration:'1000',
                                showClose:false
                            });
                        }
                        this.getUnitInfo();
                    })
                });
        }
    },
    
    mounted(){
        this.getUnitInfo();
    }
}
</script>