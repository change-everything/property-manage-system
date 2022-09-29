/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'
// import request from "axios";

// const URL_select = 'http://mock/owner/ownerAll';
// const URL_update = 'http://mmock/owner/update';
// const URL_delete = 'http://mock/owner/delete';
// const URL_detail = 'http://mock/owner/detail';


const URL = '/owner';

export default{

    selectAllByPage(data){
        return request({
                    method:'get',
                    url:URL+'/'+data.pageNum+'/'+data.pageSize
                })
    },

    detail(id){
        return request.get(URL+'/'+id);
    },

    insert(data){
        return request.post(URL,data);
    },

    search(data){
        return request.post(URL+'/'+data.pageNum+'/'+data.pageSize,data.query)
    },

    updateInfo(data){
        return request({
            method:'put',
            url:URL,
            data:data
        })

    },

    deleteInfo(id){
        return request.delete(URL+'/'+id);
    }
}



