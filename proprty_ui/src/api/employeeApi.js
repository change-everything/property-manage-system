/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'

const URL_select = '/emp'

export default{
    selectInfoByPage(data){
        
        return request({
            method:'get',
            url:URL_select+'/'+data.pageNum+'/'+data.pageSize
        })
    },


    detail(id){
        return request.get(URL_select+'/'+id);
    },


    selectByInput(data){
        return request.post('/emp/'+data.pageNum+'/'+data.pageSize,data.query)
    },

    insertInfo(data){
        return request({
            method:'post',
            url:URL_select+'/'+data.roleId,
            data:data
        })
    },

    updateInfo(data){
        return request({
            method:'put',
            url:URL_select+'/'+data.roleId,
            data:data
        })
    },


    delete(data){
        return  request({
            method:'delete',
            url:URL_select+'/'+data.empId
        })
    }
}