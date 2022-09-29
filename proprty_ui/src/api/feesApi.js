import request from '@/utils/request.js'

const URL = '/charge'

export default{
    selectPage(data){
        return request.get(URL+'/'+data.pageNum+'/'+data.pageSize);
    },


    search(data){
        return request.post(URL+'/'+data.pageNum+'/'+data.pageSize,data.query);
    },


    ///charge/bill/{roomNum}
    export(rootNum){
        return request.get(URL+'/bill/'+rootNum);
    }

}
