/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'

const URL_select = '/dept'



export default{
    select(){
        return request.get(URL_select);
    },


    delete(id){
        return request.delete(URL_select+'/'+id)
    },

    update(data){
        return request.put(URL_select,data)
    },

    insert(data){
        return request.post(URL_select,data);
    },

    detail(id){
        return request.get(URL_select+'/'+id);
    }
}