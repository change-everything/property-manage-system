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
    }
    
}