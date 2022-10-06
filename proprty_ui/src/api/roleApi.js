/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'

const URL_select = '/role'



export default{
    select(data){
        return request.get(URL_select+'/'+data.depNum);
    },


    delete(id){
        return request.delete(URL_select+'/'+id)
    },

    update(data){
        return request.put(URL_select,data)
    },

    insert(data){
        return request.post(URL_select,data);
    }
}