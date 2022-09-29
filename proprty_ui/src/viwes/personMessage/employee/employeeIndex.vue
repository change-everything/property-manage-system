<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>

        <el-form :inline="true" :model="searchForm" class="demo-form-inline">
            <el-form-item label="员工编号">
                <el-input v-model="searchForm.empNum" placeholder="请输入员工编号" />
            </el-form-item>
            <el-form-item label="电话号码">
                <el-input v-model="searchForm.phone" placeholder="请输入电话号码" />
            </el-form-item>
            <el-form-item label="部门">
                <el-select v-model="searchForm.empDepNum" placeholder="请选择部门">
                    <el-option v-for="item in units"
                        :key="item.depNum"
                        :label="item.depName"
                        :value="item.depNum"
                     />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="search"><el-icon><Search /></el-icon>查询</el-button>
                <el-button type="primary" @click="this.reload();"><el-icon><Refresh /></el-icon>重置</el-button>
            </el-form-item>
        </el-form>

        <div style="display:inline-flex">
            <el-button @click="printCurrentPage" type="warning" style="margin-right:10px" ><el-icon><Download /></el-icon>导出</el-button>
            <emp-add />
        </div>
        <div style="height:10px"></div>

        <emp-up ref="empUp" />
        <el-table :data="tableData" height="600" style="width: 100%" v-loading="loading">
            <el-table-column type="index" :index="currentIndex"  label="序号" width="60" align='center' />
            <el-table-column prop="empNum" label="员工号" width="120"  align='center'/>
            <el-table-column prop="empName" label="姓名" width="120" align='center' />
            <el-table-column prop="birthDate" label="出生年月" width="110" align='center' />
            <el-table-column prop="gender" label="性别" width="60" align='center' />
            <el-table-column prop="address" label="住址" width="270" align='center' />
            <el-table-column prop="phone" label="联系电话" width="160" align='center' />
            <el-table-column prop="empDepName" label="所在部门" width="100" align='center' />
            <el-table-column prop="roleDest" label="职务" width="100" align='center' />

            <el-table-column label="操作" width="200" align="center">
                <template #default="scope">
                    <el-button  @click="this.$refs.empUp.showUpDialog(scope.row.empId)">修改</el-button>
                    <el-button  type="danger" @click="handleDelete(scope.row)"><el-icon><Delete /></el-icon>删除</el-button>
                </template>
            </el-table-column>

            <template #empty>
                <el-empty description="似乎闻到了熟悉的气味....." />
            </template>
        </el-table>


        <el-pagination
        background 
        layout="total,prev, pager, next,jumper" 
        :total="total" 
        :current-page="currentPage"
        :page-size="pageSize"
        @current-change="pageChange" />
    </div>
</template>
<script>
// import axios from 'axios'
import empApi from '@/api/employeeApi.js'
import printJS from 'print-js'
import empAdd from './employeeAdd.vue'
import empUp from './employeeUp.vue'
import deptApi from '@/api/depApi.js'
import {ElMessageBox,ElNotification} from 'element-plus'
export default {
    components:{empAdd,empUp},
    inject: ["reload"],
    data(){
        return{
            tableData:[],
            total:'',
            currentPage:1,
            searchForm:{
                empNum:'',
                phone:'',
                empDepNum:''
            },
            units:[],
            pageSize:10,
            loading:false
        }
    },
    methods:{
        handleDelete(row){
            console.log(row)
            ElMessageBox.confirm(
                '您确定要永久删除此条记录吗？',
                '警告',
                {
                    confirmButtonText: '删除',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                .then(() => {

                    empApi.delete({empId:row.empId}).then((res)=>{
                        if(res.success == true){
                            ElNotification({
                                message: '删除成功',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            })
                        }else{
                            ElNotification({
                                message: '删除失败',
                                type: 'error',
                                duration:'1000',
                                showClose:false
                            })
                        }
                        this.getEmployeeInfo(this.currentPage);
                    });
                });
        },
        currentIndex(index){
            return  (this.currentPage-1) * 10 + index + 1;
        },
        // 打印函数
        printCurrentPage(){
            printJS({
                printable:this.tableData,
                properties: [
                {
                    field: 'empNum',
                    displayName: '员工编号',
                    columnSize: 1
                },
                {
                    field: 'empName',
                    displayName: '姓名',
                    columnSize: 1
                },
                {
                    field: 'birthDate',
                    displayName: '出生年月',
                    columnSize: 1
                },
                {
                    field: 'gender',
                    displayName: '性别',
                    columnSize: 1
                },
                {
                    field: 'address',
                    displayName: '住址',
                    columnSize: 1
                },
                {
                    field: 'phone',
                    displayName: '联系电话',
                    columnSize: 1
                },
                {
                    field: 'empDepName',
                    displayName: '所在部门',
                    columnSize: 1
                },
                {
                    field: 'roleDest',
                    displayName: '职务',
                    columnSize: 1
                }
                ],
                type: 'json',
                header: '员工信息表',
                // 样式设置
                gridStyle: 'border: 2px solid #3971A5;',
                gridHeaderStyle: 'color: red;  border: 1px solid #3971A5;'
            })
        },
        pageChange(page){
            if(this.searchForm.empNum != '' || this.searchForm.phone != '' || this.searchForm.empDepNum != ''){
                this.search(page);
            }else{
                this.getEmployeeInfo(page);
            }
            this.currentPage = page;
        },
        getEmployeeInfo(pageNum){
            this.loading = true;
            empApi.selectInfoByPage({pageNum:pageNum,pageSize:this.pageSize}).then((res)=>{
                this.tableData = res.data.records;
                this.currentPage = res.data.current;
                this.total = res.data.total;
                this.loading = false;
            })
        },
        search(){
            this.loading = true;
            empApi.selectByInput({pageNum:1,pageSize:10,query:this.searchForm}).then((res)=>{
                this.tableData = res.data.records;
                this.currentPage = res.data.current;
                this.total = res.data.total;
                this.loading = false;
                // console.log(res)
            })
        },
        //获取部门信息
        getUnitInfo(){
            deptApi.select().then((res)=>{
                // console.log(res);
                this.units = res.data;
            })
        }
    },

    mounted(){
        this.getEmployeeInfo(1);
        this.getUnitInfo();
    }
}
</script>