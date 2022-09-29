/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'

const URL_select = '/sys'

export default{
    select(){
        return request.get(URL_select);
    },

    insert(data){
        return request({
            method:'post',
            url:URL_select,
            data:data
        })
    },

    delete(id){
        return request({
            method:'delete',
            url:URL_select+'/'+id
        })
    },

    update(data){
        return request({
            method:'put',
            url:URL_select,
            data:data
        })
    },

    detail(id){
        return request({
            method:'get',
            url:URL_select+"/"+id
        })
    }
}