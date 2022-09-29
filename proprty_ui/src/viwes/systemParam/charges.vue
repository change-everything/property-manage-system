<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <charge-add ref="chargeAdd" />
        <charges-up ref="chargesUp" />
        <el-table :data="tableData" height="600" style="width: 100%" v-loading="loading">
            <el-table-column label="收费类型" prop="chargeType" width="180"  align='center'/>
            <el-table-column label="单位" prop="unit" width="180"  align='center' />
            <el-table-column label="单价" prop="price" width="180"  align='center'/>


            <el-table-column label="操作" width="180" align="center">
                <template #header>
                    <el-button @click="printCurrentPage" ><el-icon><Download /></el-icon>导出</el-button>
                    <el-button  @click="this.$refs.chargeAdd.showDialog()">添加</el-button>
                </template>
                <template #default="scope">
                    <el-button  @click="this.$refs.chargesUp.showDialog(scope.row.id)">修改</el-button>
                    <el-button  type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>

        </el-table>
    </div>
</template>

<script>
import {ElMessageBox,ElNotification} from 'element-plus'
import chargeAdd from './chargesAdd.vue'
import ChargesUp from './chargesUp.vue'
import sysParam from '../../api/systemParamApi'
import printJS from 'print-js'
export default {
  components: { chargeAdd, ChargesUp },
  inject:['reload'],
    data(){
        return{
            tableData:[]
        }
    },

    methods:{
        handleDelete(id){
            // console.log(id)
            ElMessageBox.confirm(
                '您确定要永久删除此条记录吗？',
                '警告',
                {
                    confirmButtonText: '删除',
                    cancelButtonText: '取消',
                    type: 'warning',
                })
                .then(() => {
                    sysParam.delete(id).then((res)=>{
                        ElNotification({
                            message: res.data,
                            type: res.success ? 'success' : "error",
                            duration:'1000',
                            showClose:false
                        }),
                        this.reload();
                    })
                });
        },

        allStand(){
            sysParam.select().then(res=>{
                this.tableData = res.data;
            })
        },



        // 打印函数
        printCurrentPage(){
            printJS({
                printable:this.tableData,
                properties: [
                {
                    field: 'chargeType',
                    displayName: '收费类型',
                    columnSize: 1
                },
                {
                    field: 'unit',
                    displayName: '单位',
                    columnSize: 1
                },
                {
                    field: 'price',
                    displayName: '单价',
                    columnSize: 1
                },
                ],
                type: 'json',
                header: '收费详情表',
                // 样式设置
                gridStyle: 'border: 2px solid #3971A5;',
                gridHeaderStyle: 'color: red;  border: 1px solid #3971A5;'
            })
        },
    },

    mounted(){
        this.allStand();
    }
}
</script>