<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <el-dialog v-model="dialogTableVisible" title="业主信息修改" width="400px" @closed="this.$refs.ownerInfo.resetFields();">
        <el-form
        :model="ownerInfo"
        :rules="rules" ref="ownerInfo"
        :label-width="100" label-position="left"
        >
            <el-form-item label="业主编号">
                <el-input v-model="ownerInfo.ownerNum" readonly />    
            </el-form-item>

            <el-form-item label="姓名" prop="ownerName">
                <el-input v-model="ownerInfo.ownerName" />    
            </el-form-item>

            <el-form-item label="房号" prop="roomNum">
                <el-input v-model="ownerInfo.roomNum" />    
            </el-form-item>

            <el-form-item label="房屋面积" prop="floorArea">
                <el-input v-model.number="ownerInfo.floorArea" />    
            </el-form-item>

            <el-form-item label="工作单位" prop="workUnit">
                <el-input v-model="ownerInfo.workUnit" />    
            </el-form-item>

            <el-form-item label="联系电话" prop="phoneNumber">
                <el-input v-model="ownerInfo.phoneNumber" /> 
            </el-form-item>
            
            <el-button @click="update">修改</el-button>
        </el-form>
    </el-dialog>
</template>
<script>

import {ElNotification} from 'element-plus'
import checkFunc from '@/utils/check.js'
import ownApi from '@/api/ownerApi.js'
export default {
    components:{},
    inject:['reload'],
    data(){
        return{
            ownerInfo:{},
            dialogTableVisible:false,
            rules:{
                ownerName:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                roomNum:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                floorArea:[{required: true, validator: this.validateHouseArea, trigger: 'blur'},
                        { min: 1,type: "number",message: "请输入大于0的数",trigger: "blur"}
                ],
                workUnit:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                phoneNumber:[{required: true, validator: this.validatePhone, trigger: 'blur'}]
            }
        }
    },

    methods:{
        showDialog(id){
            this.getDetailOfOwner(id);
            this.dialogTableVisible = true;
        },

        getDetailOfOwner(id){
            ownApi.detail(id).then((res)=>{
                this.ownerInfo = res.data
            })
        },
        update(){

            let self = this;
            self.$refs.ownerInfo.validate((valid) => {
                if(valid){

                    ownApi.updateInfo(this.ownerInfo).then((res)=>{
                        if(res.success == true){
                            ElNotification({
                                message: '修改成功',
                                type: 'success',
                                duration:'1000',
                                showClose:false
                            })
                            this.reload();
                        }
                    })
                }else{
                    return false;
                }
            });
        },




        /**
         * 字段校验规则
         */
        validateHouseArea(rule, value, callback){
            if (value==null || value === "") {
                callback(new Error("必填字段，不能为空"));
            }else if(value <= 0 || value > 10000){
                callback(new Error("数字区间错误"))   
            }else {
                callback();
            }
        },

        validatePhone(rule, value, callback){
            if (value==null ||  value === "") {
                callback(new Error("必填字段,不能为空"));
            }else {
                if(checkFunc.thePhoneIsTure(value)){
                    callback();
                }else{
                    callback(new Error("请输入正确电话号码"))
                }
            }
        }
    }
}
</script>