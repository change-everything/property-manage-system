import request from '@/utils/request.js'

const URL = '/noPayCharge'

export default{
    selectPage(data){
        return request.get(URL+'/'+data.pageNum+'/'+data.pageSize);
    },


    search(data){
        return request.post(URL+'/'+data.pageNum+'/'+data.pageSize,data.query);
    },


    export(rootNum){
        return request.get(URL+'/bill/'+rootNum);
    },


    commit(data){
        return request.post('/noPayCharge/pay',data);
    }

}
