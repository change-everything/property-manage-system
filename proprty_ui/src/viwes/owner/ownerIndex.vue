<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <el-form :inline="true" :data="formSearch" ref="formSearch">
            <el-form-item label="业主编号" prop="ownerNum">
                <el-input v-model="formSearch.ownerNum" style="width:220px;" placeholder="请输入业主编号" />
            </el-form-item>
            <el-form-item label="业主姓名" prop="ownerName">
                <el-input v-model="formSearch.ownerName" style="width:220px;" placeholder="请输入业主姓名" />
            </el-form-item>
            <el-form-item label="房号" prop="roomNum">
                <el-input v-model="formSearch.roomNum" style="width:220px;" placeholder="请输入房号" />
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="search(1)"><el-icon><Search /></el-icon>查询</el-button>
                <el-button type="primary" @click="this.reload()"><el-icon><Refresh /></el-icon>重置</el-button>
            </el-form-item>
        </el-form>

        <div style="display:inline-flex">
            <el-button type="warning" @click="printCurrentPage" style="margin-right:10px"><el-icon><Download /></el-icon>导出</el-button>
            <ownerAdd />
        </div>

        <div style="height:10px"></div>

        <ownerup ref="ownerInfoUp" />

        <el-table :data="tableData" height="600" style="width: 100%" v-loading="loading">
            <el-table-column type="index" :index="currentIndex"  label="序号" width="80" align='center' />
            <el-table-column prop="ownerNum" label="业主编号" width="180"  align='center'/>
            <el-table-column prop="ownerName" label="姓名" width="180" align='center' />
            <el-table-column prop="roomNum" label="房号" width="180" align='center' />
            <el-table-column prop="floorArea" label="房屋面积" width="180" align='center' />
            <el-table-column prop="workUnit" label="工作单位" width="180" align='center' />
            <el-table-column prop="phoneNumber" label="联系电话" width="180" align='center' />

            <el-table-column label="操作" width="180" align="center">
                <template #default="scope">
                    <el-button  @click="handleEdit(scope.$index, scope.row)">修改</el-button>
                    <el-button  type="danger" @click="handleDelete(scope.$index, scope.row)"><el-icon><Delete /></el-icon>删除</el-button>
                </template>
            </el-table-column>

            <template #empty>
                <el-empty description="脑子空空...." />
            </template>
        </el-table>


        <el-pagination 
        background 
        layout="total,prev, pager, next,jumper" 
        :total="total" 
        :current-page="currentPage"
        @current-change="pageChange" />
    </div>
</template>

<script>
import ownerup from './ownerUp.vue'
import ownerAdd from './ownerAdd.vue'
import {ElNotification,ElMessageBox} from 'element-plus'
import printJS from 'print-js'
import api from "@/api/ownerApi.js"

export default {
    components:{ownerup,ownerAdd},
    inject:['reload'],
    data(){
        return{
            formSearch:{
                ownerNum:'',
                ownerName:'',
                roomNum:''
            },
            tableData:[],    
            total:'',
            currentPage:1,
            pageSize:10
        }
    },

    methods:{
        handleEdit(index,row){
            this.$refs.ownerInfoUp.showDialog(row.ownerId);
        },

        handleDelete(index,row){

            ElMessageBox.confirm(
                '您确定要永久删除此条记录吗？',
                '警告',
                {
                    confirmButtonText: '删除',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                .then(() => {
                    api.deleteInfo(row.ownerId).then((res)=>{
                        if(res.success == true){
                            ElNotification({
                                message: '删除成功',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            })
                            this.init(this.currentPage);
                        }
                    })
                });
        },
        init(pageStart){
            api.selectAllByPage({pageNum:pageStart,pageSize:this.pageSize}).then((res)=>{
                console.log(res);
                this.tableData =res.data.records;
                this.total = res.data.total;
            })
        },
        
        pageChange(newPage){
            
            if( (this.formSearch.ownerNum != null && this.formSearch.ownerNum != '') || 
                (this.formSearch.ownerName != null && this.formSearch.ownerName != '') || 
                (this.formSearch.roomNum != null && this.formSearch.roomNum != '')){
                this.search(newPage);
            }else{
                this.init(newPage);
            }
            this.currentPage = newPage;
        },
        currentIndex(index){
            return  (this.currentPage-1) * 10 + index + 1;
        },

        search(page){
            api.search({pageNum:page,pageSize:this.pageSize,query:this.formSearch}).then((res)=>{
                this.tableData =res.data.records;
                this.total = res.data.total;
            })
        },
        // 打印函数
        printCurrentPage(){
            printJS({
                printable:this.tableData,
                properties: [
                {
                    field: 'ownerNum',
                    displayName: '业主编号',
                    columnSize: 1
                },
                {
                    field: 'ownerName',
                    displayName: '姓名',
                    columnSize: 1
                },
                {
                    field: 'roomNum',
                    displayName: '房号',
                    columnSize: 1
                },
                {
                    field: 'floorArea',
                    displayName: '房屋面积㎡',
                    columnSize: 1
                },
                {
                    field: 'workUnit',
                    displayName: '工作单位',
                    columnSize: 1
                },
                {
                    field: 'phoneNumber',
                    displayName: '联系电话',
                    columnSize: 1
                }
                ],
                type: 'json',
                header: '业主信息表',
                // 样式设置
                gridStyle: 'border: 2px solid #3971A5;',
                gridHeaderStyle: 'color: red;  border: 2px solid #3971A5;'
            })
        }

    },

    mounted(){
        this.init(1);
        // this.getTotal();
    }
}
</script>