<!--
 @author: 孙明君
 @date: 2022-06-16
 -->
<template>
    <div>
        <el-button @click="click">数据请求</el-button>

        <div>{{res}}</div>
    </div>
</template>
<script>

// import api from "@/api/ownerApi.js"
// import axios from '@/utils/request.js'
import axios from '@/api/publicApi.js'
export default {
    data(){
        return{
            res:{}
        }
    },
    methods:{
        click(){
            
        //    axios.get('/api/emp/1/3').then((res)=>{
        //        this.res = res;
        //    })'


        axios.login({username:'10001',password:'root'}).then((res)=>{
            this.res = res;
        })

           

        // axios({
        //     methods:'get',
        //     url:'http://127.0.0.1:8082/EmployeeInfos/select',
        //     data:{
        //         pageNum:1,
        //         pageSize:10,
        //         queryMap:{
        //             a:'aa',
        //             b:'bb'
        //         }
        //     }
        // }).then((res)=>{
        //     this.res = res;
        // })
        }
    }
}
</script>