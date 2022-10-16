/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'


export default{
    getCommunity(){
        var URL = '/community/info'
        return request.get(URL);
    },

    getChargeInfo(){
        var URL = '/charge/info'
        return request.get(URL);
    }
}

