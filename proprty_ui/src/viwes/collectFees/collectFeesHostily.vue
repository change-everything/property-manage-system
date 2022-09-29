
<template xmlns:text-align="http://www.w3.org/1999/xhtml" xmlns:margin-top="http://java.sun.com/xml/ns/persistence/orm"
          xmlns:border-top="http://www.w3.org/1999/xhtml">

  <div>
    <el-form :inline="true" :data="formSearch">
      <el-form-item label="房间号">
        <el-input v-model="formSearch.roomNum" style="width:220px;" placeholder="请输入房间号" clearable/>
      </el-form-item>
      <el-form-item label="业主编号">
        <el-input v-model="formSearch.ownerNum" style="width:220px;" placeholder="请输入业主编号" clearable/>
      </el-form-item>
      <el-form-item label="员工号">
        <el-input v-model="formSearch.empNum" style="width:220px;" placeholder="请输入员工号" clearable/>
      </el-form-item>



      <el-form-item>
        <el-button type="primary" @click="search(1)"><el-icon><Search /></el-icon>查询</el-button>
        <el-button type="primary" @click="this.reload();"><el-icon><Refresh /></el-icon>重置</el-button>
      </el-form-item>
    </el-form>



    <div style="height:10px"></div>




    <el-table :data="tableData" height="500" style="width: 100%" >

      <el-table-column type="index" :index="currentIndex"  label="序号" width="80" align='center' />
      <el-table-column prop="roomNum" label="房号" width="180"  align='center'/>
      <el-table-column prop="ownerNum" label="业主编号" width="180" align='center' />
      <el-table-column prop="chaDate" label="收费日期" width="180" align='center' />


      <el-table-column prop="empNum" label="员工号" width="180" align='center' />

      <el-table-column label="操作" width="180" align="center">



        <template #default="scope">
          <el-button type="warning"  @click="insertTd(scope.row)" style="margin-right:10px">查看</el-button>

          <el-dialog title="收费单"    v-model="dialogVisible" width="40%" append-to-body>
            <div id="print">

              <table  id="table2" border="1" width="550" cellspacing="0" frame=void >

                <tr>
                  <td colspan="2" style="text-align: left">房号:{{billRoomNum}}</td>
                  <td colspan="2"  align="right" style="text-align: right">业主姓名:{{billOwnerName}}</td>
                </tr>
                <tr>
                  <th>序号</th>
                  <th>收费类型</th>
                  <th>数量</th>
                  <th>金额</th>
                </tr>



                <tr>
                  <td>1</td>
                  <td>物业费</td>
                  <td>{{propertyChaQuantity}}</td>
                  <td>{{ propertyChaSum }}</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>卫生费</td>
                  <td>{{ sanitaryChaQuantity }}</td>
                  <td>{{ sanitaryChaSum }}</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>水费</td>
                  <td>{{ waterChaQuantity }}</td>
                  <td>{{ waterChaSum }}</td>
                </tr>
                <tr>
                  <td>4</td>
                  <td>电费</td>
                  <td>{{ electricityChaQuantity }}</td>
                  <td>{{ electricityChaSum }}</td>
                </tr>

                <tr>
                  <td>合计</td>
                  <td colspan="2">{{text}}</td>

                  <td >{{ totalChaSum }}</td>
                </tr>

                <tr  >
                  <td colspan="2"  style="text-align: left">收费日期:{{billChaDate}}</td>
                  <td colspan="2"  align="right" style="text-align: right">员工号:{{billOwnerNum}}</td>
                </tr>
              </table>
            </div>
            <template #footer>

    <span class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>

      <el-button type="warning" @click="printCurrentPage" style="margin-right:10px">打印</el-button>

    </span>
            </template>
          </el-dialog>



          <!--        <el-button type="warning" @click="printCurrentPage" style="margin-right:10px">导出</el-button>-->
          <!--          <el-button  type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>-->
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
<script >
import printJS from 'print-js'
import feesApi from '@/api/feesApi.js'


export default {
  components:{},
  inject:['reload'],
  data(){
    return{
      // 展开行,数组形式
      expands: [],
      // 每行的唯一key值 用其id表示
      getRowKeys(row) {
        return row.id;
      },

      text: '0',
      num:0,
      unit: ["仟", "佰", "拾", "", "仟", "佰", "拾", ""],
      formSearch:{},
      tableData:[],
      pageSize:10,


      total:'',
      currentPage:1,
      // 控制添加用户对话框的显示和隐藏
      dialogVisible: false,

      //收费单

      billRoomNum:"",//房号
      billOwnerName:"",//业主姓名
      billChaDate:"",//收费日期
      billOwnerNum:"",//员工号

      propertyChaQuantity:"",//物业费数量
      propertyChaSum:"",//物业费金额
      sanitaryChaQuantity:"",//卫生费数量
      sanitaryChaSum:"",//卫生费金额
      waterChaQuantity:"",//水费数量
      waterChaSum:"",//水费金额
      electricityChaQuantity:"",//电费数量
      electricityChaSum:"",//电费金额
      totalChaSum:"",//总金额
      //收费表和下拉框共同的数据来源
      json1:[],




    }
  },

  methods:{
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

    toDx(n) {   //阿拉伯数字转换函数
      switch (n) {
        case "0":
          return "零";
        case "1":
          return "壹";
        case "2":
          return "贰";
        case "3":
          return "叁";
        case "4":
          return "肆";
        case "5":
          return "伍";
        case "6":
          return "陆";
        case "7":
          return "柒";
        case "8":
          return "捌";
        case "9":
          return "玖";
      }
    },

    insertTd(data){

      feesApi.export(data.roomNum).then((res)=>{
        this.json1 = res.data;
        console.log(res)
      })


      this.dialogVisible = true;
      // let loading = ElLoading.service();


      setTimeout(()=>{
        // console.log(data)

        // console.log(data);
        this.billRoomNum=data.roomNum;
        this.billChaDate=data.chaDate;
        this.billOwnerNum=data.ownerNum;
        var i;
        for(i=0;i<this.json1.length;i++){
          if (this.json1[i].chaType == "物业费"){
            this.propertyChaQuantity=this.json1[i].chaQuantity;
            this.propertyChaSum=this.json1[i].chaSum;
          }else if (this.json1[i].chaType == "卫生费"){
            this.sanitaryChaQuantity=this.json1[i].chaQuantity;
            this.sanitaryChaSum=this.json1[i].chaSum;
          }else if (this.json1[i].chaType == "水费"){
            this.waterChaQuantity=this.json1[i].chaQuantity;
            this.waterChaSum=this.json1[i].chaSum;
          }else if (this.json1[i].chaType == "电费"){
            this.electricityChaQuantity=this.json1[i].chaQuantity;
            this.electricityChaSum=this.json1[i].chaSum;
          }
          this.billOwnerName=this.json1[i].ownerName;
        }
        this.totalChaSum=this.propertyChaSum+this.sanitaryChaSum+this.waterChaSum+this.electricityChaSum;
        //金钱转换
        //  this.totalChaSum *= 100;
        this.totalChaSum += "";
        let length = this.totalChaSum.length;

        let result = "";
        for (let i = 0; i < length; i++) {
          if (i === 2) {
            result = result+'元';
          } else if (i === 6) {
            result = "万" + result;
          }
          if (this.totalChaSum.charAt(length - i - 1) === 0) {
            if (i !== 0 && i !== 1) {
              if (result.charAt(0) !== '零' &&  result.charAt(0) !== '万') {
                result = "零" + result;
              }
            }
            continue;
          }
          result = this.toDx(this.totalChaSum.charAt(length - i - 1)) + this.unit[this.unit.length - i - 1] + result;
        }
        result += result.charAt(result.length - 1) === '元' ? "整" : "";
        this.text = result;
        // loading.close();
      },"500");
    },

    handleEdit(index,row){
      this.$refs.chargeInfoUp.showDialog(row.chaId);
    },
    
    init(pageStart){

      feesApi.selectPage({pageNum:pageStart,pageSize:this.pageSize}).then((res)=>{
        this.tableData =res.data.records;
        this.total = res.data.total;
        // console.log(res);
      })
    },

    pageChange(newPage){
      this.init(newPage);
      this.currentPage = newPage;
    },
    currentIndex(index){
      return  (this.currentPage-1) * 10 + index + 1;
    },
    search(pageNum){


      feesApi.search({pageNum:pageNum,pageSize:this.pageSize,query:this.formSearch}).then((res)=>{
        this.tableData = res.data.records;
        this.total = res.data.total;
        // console.log(res);
      })
    },
    // 打印函数
    printCurrentPage(){

      printJS({
        printable:'print',

        type: 'html',
        header: '收费单',
        // 样式设置
        targetStyles: ['*'],
      })
    }
  },

  mounted(){
    this.init(1);
    // this.getTotal();
  }
}
</script>
<style>
table td {
  text-align:center;
}
table tr:last-child {
  border-left: none !important;
  border-right: none !important;
  border-bottom: none !important;
}
table tr:last-child td {
  border-left: none !important;
  border-right: none !important;
  border-bottom: none !important;

}
table tr:first-child {
  border-left: none !important;
  border-right: none !important;
  border-top: none !important;
}
table tr:first-child td {
  border-left: none !important;
  border-right: none !important;
  border-top: none !important;
}


</style>
