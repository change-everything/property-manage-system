/**
 * @author: 孙明君
 * @date: 2022-06-15
*/
import store from '@/store/index.js'
export default {
    hasPerm(code){
        const perms = store.state.users.permCode;
        console.log(code);
        console.log('权限数据',perms);
    },


    dataURItoBlob(base64Data) {
        var byteString
        if (base64Data.split(',')[0].indexOf('base64') >= 0)
            byteString = atob(base64Data.split(',')[1])
        else byteString = unescape(base64Data.split(',')[1])
        var mimeString = base64Data
        .split(',')[0]
        .split(':')[1]
        .split(';')[0]
        var ia = new Uint8Array(byteString.length)
        for (var i = 0; i < byteString.length; i++) {
         ia[i] = byteString.charCodeAt(i)
        }
        return new Blob([ia], { type: mimeString })
    }
}