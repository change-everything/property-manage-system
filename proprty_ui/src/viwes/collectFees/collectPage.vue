<!--
 @author: 孙明君
 @date: 2022-06-09
 -->
<template>
    <el-dialog v-model="dialogVisable" title="物主缴费详情">
        <div id="show">
            <div>
                <div class="info">房号：{{feesDetail.houseNum}}</div>
                <div class="info">业主编号：{{feesDetail.ownerNum}}</div>
            </div>
            <div>
                
                <table border="1" style="width:100%;" aligh="center">
                    <tr>
                        <th>序号</th>
                        <th>收费类型</th>
                        <th>数量</th>
                        <th>金额</th>
                    </tr>
                    <template v-for="(item,index) in feesDetail.detail" :key="index">
                        <tr>
                            <td>{{index+1}}</td>
                            <td>{{item.type}}</td>
                            <td>{{item.Number}}</td>
                            <td>{{item.count}}</td>
                        </tr>
                    </template>

                    <tr>
                        <td>合计</td>
                        <td colspan="2">{{tatalWithText}}</td>
                        <td >3232</td>
                    </tr>
                </table>
                <div style="display:inline-flex">
                    <div style="float:left;margin-left:10px">收费日期：{{this.getCurrentTime()}}</div>
                    <div style="float:right;margin-right:10px">操作员：{{currentUser}}</div>
                </div>
            </div>
        </div>


        <div>
            <!-- <el-button @click="print">打印回执</el-button> -->
            <el-button @click="payCharge">结算</el-button>
        </div>

    </el-dialog>
</template>
<script>
import axios from 'axios';
import printJS from 'print-js'
import {ElMessageBox} from 'element-plus'
export default {
    data(){
        return{
            feesDetail:{},
            dialogVisable:false,
            tatalWithText:'xxxxx',
            currentUser:'001'
        }
    },

    methods:{
        showDialog(id){
            this.dialogVisable = true;
            axios({
                method:'get',
                url:'http://mock/fees/search',
                data:{
                    id:id
                }
            }).then((res)=>{
                if(res.status >= 200 && res.status < 300){
                    this.feesDetail = res.data;
                }
            })
        },
        print(){
            printJS({ printable: 'show', type: 'html', header: 'xx物业缴费凭证' })
        },

        getCurrentTime(){
            let date = new Date();
            return date.toLocaleString( );
            // this.$store.commit('updateInfo','3232');
        },


        payCharge(){
            ElMessageBox.alert('是否打印回执', '系统', {
                confirmButtonText: 'OK',
                callback: (Action) => {
                    if(Action === 'confirm'){
                        this.print();
                    }
                },
            })
        }
    }
}
</script>

<style scoped>
.info{
    font-size: 15px;
    font-weight: bold;
}
td{
    vertical-align: middle;
    text-align: center;
}
</style>