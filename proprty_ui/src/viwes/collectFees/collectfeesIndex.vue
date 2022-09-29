<template>
  <div>
    <el-form :inline="true" :data="formSearch" >
      <el-form-item label="房号" >
        <el-input v-model="formSearch.roomNum" style="width:220px;" placeholder="请输入房号"   clearable/>
      </el-form-item>
      <el-form-item label="业主编号">
        <el-input v-model="formSearch.ownerNum" style="width:220px;" placeholder="请输入业主编号" clearable />
      </el-form-item>



      <el-form-item>
        <el-button type="primary" @click="search"><el-icon><Search /></el-icon>查询</el-button>
        <el-button type="primary" @click="this.reload()"><el-icon><Refresh /></el-icon>重置</el-button>

      </el-form-item>
    </el-form>



    <div style="height:10px"></div>



    <el-table :data="tableData" height="500" style="width: 120%" header-align="center"
              :row-key="row_key"
              :expand-row-keys="expands"
              @row-click="clickRowHandle">
      <el-table-column type="expand">

        <template #default="scope">
          <el-table ref="multipleTable"  :data="scope.row.children"  header-align="center" show-summary
                  :summary-method="getSummaries">
          <el-table-column prop="chaType" label="收费类型" width="350"  align='center'/>

          <el-table-column prop="chaQuantity" label="收费数量" width="200" align='center' />
          <el-table-column prop="chaSum" label="收费金额" width="400" align='center' />
        </el-table>
        </template>
      </el-table-column>


      <el-table-column type="index" :index="currentIndex"  label="序号" width="250" align='center' />

      <el-table-column prop="roomNum" label="房号" width="300"  align='center'/>
      <el-table-column prop="ownerNum" label="业主编号" width="300" align='center' />



      <el-table-column label="操作" width="200" align="center">
        <template #default="scope">
          <el-button type="danger" @click="commit(scope.row)">缴费</el-button>
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
import {ElNotification,ElMessageBox} from 'element-plus'

import newFeesApi from "@/api/newFeesApi";



export default {
  components:{},
  inject:['reload'],
  data(){
    return{
      formSearch:{
        ownerNum:'',
        roomNum:''
      },
      tableData:[],
      total:'',
      currentPage:1,
      expands: [],
      json1:[],
      pageSize:10
    }
  },


  methods:{

    commit(row){

      ElMessageBox.confirm(
                '是否缴费？',
                '提示',
                {
                    confirmButtonText: '缴费',
                    cancelButtonText: '取消',
                    type: 'info',
                })
                .then(() => {
                    newFeesApi.commit({roomNum:row.roomNum,empNum:this.$store.state.users.userInfo.userNum}).then((res)=>{
                      let message = '';
                      let type = '';
                      if(res.success == true){
                            message = '缴费成功';
                            type="success";
                        }else{
                          message = '缴费失败,请稍后再试';
                          type='error';
                        }

                        ElNotification({
                                message: message,
                                type: type,
                                duration:'1000',
                                showClose:false
                        })
                        this.init(this.currentPage);
                    })
                });  
    },

    getSummaries(param) {
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = '合计';
          return;
        }
        const values = data.map(item => Number(item[column.property]));
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr);
            if (!isNaN(value)) {
              return prev + curr;
            } else {
              return prev;
            }
          }, 0);

        }
      });

      return sums;
    },

    row_key(row){
      return row.chaId;
    },


    clickRowHandle(row) {
      this.expands = row.chaId;
    },

    
    init(pageStart){
      newFeesApi.selectPage({pageNum:pageStart,pageSize:this.pageSize}).then((res)=>{
        console.log(res);
        this.tableData =res.data.records;
        this.total = res.data.total;
        console.log(res);
      })
    },

    pageChange(newPage){
      if(this.formSearch.ownerNum != '' || this.formSearch.roomNum != ''){
        this.search(newPage);
      }else{
        this.init(newPage);
      }
      this.currentPage = newPage;
    },

    currentIndex(index){
      return  (this.currentPage-1) * 10 + index + 1;
    },
    search(pageNum){

      newFeesApi.search({pageNum:pageNum,pageSize:this.pageSize,query:this.formSearch}).then((res)=>{
        this.tableData = res.data.records;
        this.total = res.data.total;
        // console.log(res);
      })
    }


  },

  mounted(){
    this.init(1);
  }
}
</script>