<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <unit-change ref="unitChange" />
        <unit-up ref="unitUp" />
        <zhiwu-change ref="zhiwuChange" />
        <unit-add ref="unitApp" />
        <div>
            <el-button  type="primary"  @click="this.$refs.unitApp.dialogVisable=true;"><el-icon><Plus /></el-icon>新建</el-button>
        </div>

        <el-table :data="tableData" style="width: 100%"  height="80vh" v-loading="loading" >
            <el-table-column type="expand">
                <template #default="props">
                    <el-table :data="props.row.emp" style="margin-left:50px;width:1200px" max-height="400px" :show-header="false" v-loading="loadingExpang">
                        <el-table-column :width="150"  type="index"/>
                        <el-table-column :width="180" prop="empNum"/>
                        <el-table-column :width="180" prop="empName" />
                        <el-table-column :width="180" prop="roleDest" />
                        <el-table-column >
                            <template #default="scope">
                                <el-button link type="primary" size="small" @click="this.$refs.unitChange.showDialog(scope.row.empId,props.row.depName);">部门调整</el-button>
                                <el-button link type="primary" size="small" @click="this.$refs.zhiwuChange.showDialog(scope.row.empId,scope.row.empDepNum)">职务调整</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </template>
            </el-table-column>
            <el-table-column label="部门名称" prop="depName" />
            <el-table-column label="部门编号" prop="depNum" />
            <el-table-column label="部门负责人" prop="depHead" />
            <el-table-column label="部门电话" prop="depTelNum" />
            
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button link type="primary" size="small" @click="resignUnit(scope.row)">部门解散</el-button>
                    <el-button link type="primary" size="small" @click="this.$refs.unitUp.showDialog(scope.row.depId)">部门修改</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import unitChange from "./unitChange.vue"
import unitUp from "./unitUp.vue"
import zhiwuChange from "./zhiWuChange.vue"
import depApi from '@/api/depApi.js'
// import roleApi from '@/api/roleApi.js'
import empApi from '@/api/employeeApi.js'
// import axios from 'axios'
import {ElNotification,ElMessageBox } from 'element-plus'
import UnitAdd from './unitAdd.vue'
export default {
    components:{unitChange,unitUp,zhiwuChange, UnitAdd},
    inject:['reload'],
    data(){
        return{
            tableData :[],
            empInfos:[],
            currentUnitId:'',
            loading:false,
            loadingExpang:false
        }
    },
    methods:{
        
        //获取部门信息
        getUnitInfo(){
            this.loading = true;
            depApi.select().then((res)=>{
                this.tableData = res.data;
                // console.log(res)
                this.loading = false;
            })
            
        },


        //获取各部门人员信息（动态加载）
        getEmpInfo(row,tag){

            if(tag.length == 0){
                return;
            }else{
                this.loadingExpang = true;
                this.currentUnitId = row.depNum;
                empApi.selectByInput({pageNum:1,pageSize:10000000,query:{empDepNum:row.depNum}}).then((res)=>{
                    this.empInfos = res.data.records;
                    // console.log(res)
                    this.loadingExpang = false;
                })
            }
        },


        //解散部门
        resignUnit(row){
            ElMessageBox.confirm(
                '不可逆操作:部门所属人员将被清出且部门将会被删除!!',
                '警告',
                {
                    confirmButtonText: '解散',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                .then(() => {
                    depApi.delete(row.depId).then((res)=>{
                        if(res.success == true){
                            ElNotification({
                                message: '成功解散',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            })
                        }else{
                            ElNotification({
                                message: '解散失败',
                                type: 'error',
                                duration:'1000',
                                showClose:false
                            })
                        }
                        this.reload();
                    })
                    
                });
        }
    },
    mounted(){
        this.getUnitInfo()
    }
    
}
</script>