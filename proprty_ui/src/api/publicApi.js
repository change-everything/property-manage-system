/**
 * @author: 孙明君
 * @date: 2022-06-16
*/
import request from '@/utils/request.js'
import axios from 'axios'
import utils from '@/utils/tools.js'

// const URL_login = 'http://127.0.0.1/8080';

export default{
    login(data){
        return request.post('/user/login',data);
    },

    loginFace(img){
        const formData = new FormData();
        formData.append('file', utils.dataURItoBlob(img));
        return axios.post('/user/faceLogin',formData)
    },



    logOut(){
        return request({
            method:'get',
            url:'/logout'
        })
    },


    getPerrment(){
        return request.get('/perm');
    },


    getPermDetail(id){
        return request.get('/role/perm/'+id);
    },

    commitPerm(data){ 
        return request.post('/role/'+data.roleId,{permIds:data.perms});
    }


}