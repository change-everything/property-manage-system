<!--
 @author: 孙明君
 @date: 2022-06-08
 -->
<template>
    <div>
        <el-button @click="showAddDialog" type="success"><el-icon><Plus /></el-icon>添加</el-button>
        <el-dialog v-model="dialogVisable" width="400px" style="height:40vh" @closed="this.$refs.ownerInfo.resetFields();" title="业主信息添加">
            <el-form :model="ownerInfo" :rules="rules" ref="ownerInfo" :label-width="100" label-position="left">
            <el-form-item label="业主编号">
                <el-input v-model="ownerInfo.ownerNum" />    
            </el-form-item>

            <el-form-item label="姓名" prop="ownerName">
                <el-input v-model="ownerInfo.ownerName" />    
            </el-form-item>

            <el-form-item label="房号" prop="roomNum">
                <el-input v-model="ownerInfo.roomNum" />    
            </el-form-item>

            <el-form-item label="房屋面积" prop="floorArea">
                <el-input v-model.number="ownerInfo.floorArea"/>    
            </el-form-item>

            <el-form-item label="工作单位" prop="workUnit">
                <el-input v-model="ownerInfo.workUnit" />    
            </el-form-item>

            <el-form-item label="联系电话" prop="phoneNumber">
                <el-input v-model="ownerInfo.phoneNumber" /> 
            </el-form-item>
            
            <el-button @click="add">添加</el-button>
        </el-form>
        </el-dialog>
    </div>
</template>

<script>
import checkFunc from '@/utils/check.js'
import ownerApi from '@/api/ownerApi.js'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            dialogVisable:false,
            ownerInfo:{},
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
        showAddDialog(){
            this.dialogVisable = true;
            this.radomNum()
        },

        add(){
            let self = this;
            self.$refs.ownerInfo.validate((valid) => {
                if(valid){
                    ownerApi.insert(this.ownerInfo).then((res)=>{
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
            })
        },

        //随机数编号
        radomNum(){
            var num = "";   //定义用户编号
            for (var i = 0; i < 5; i++) //4位随机数，用以加在时间戳后面。
            {
                num += Math.floor(Math.random() * 10);
            }
            num = new Date().getTime() | num;  //时间戳，用来生成用户编号。
            this.ownerInfo.ownerNum = num;
        },


        /**
         * 字段校验规则
         */
        validateHouseArea(rule, value, callback){
            console.log(value)
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