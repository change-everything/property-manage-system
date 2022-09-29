<!--
 @author: 孙明君
 @date: 2022-06-10
 -->
<template>
    <el-dialog v-model="dialogVisable" width="20vw" top="30vh" title="新建收费标准">
        <el-form :model="charge" ref="charge" :label-width="100" label-position="left" :rules="rules">
            <el-form-item label="收费类型" prop="type">
                <el-input v-model="charge.chargeType" placeholder="请输入收费类型" />
            </el-form-item>

            <el-form-item label="单位" prop="danwei">
                <el-select v-model="charge.unit" placeholder="请选择计量单位">
                    <el-option label="套"  value="套" />
                    <el-option label="吨"  value="吨" />
                    <el-option label="度"  value="度" />
                    <el-option label="每平方米"  value="每平方米" />
                    <el-option label="每立方米"  value="每立方米" />
                </el-select>
            </el-form-item>

            <el-form-item label="单价" prop="price">
                <el-input v-model.number="charge.price" placeholder="请输入单价" />
            </el-form-item>

            <el-button @click="insertInfo">添加</el-button>
        </el-form>
    </el-dialog>
</template>
<script>
import sysParam from '../../api/systemParamApi'
import {ElNotification} from 'element-plus'
export default {
    inject:['reload'],
    data(){
        return{
            charge:{},
            dialogVisable:false,
            rules:{
                chargeType:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                unit:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'}],
                price:[{required: true, message: '必填字段,不允许为空', trigger: 'blur'},
                { min: 1,type: "number",message: "请输入大于0的数",trigger: "blur"}]
            }
        }
    },

    methods:{
        showDialog(){
            this.dialogVisable = true;
        },

        insertInfo(){
            sysParam.insert(this.charge).then(res=>{
                ElNotification({
                    message: res.data,
                    type: res.success ? 'success' : "error",
                    duration:'1000',
                    showClose:false
                }),
                this.reload();
            })
        }
    }
}
</script>