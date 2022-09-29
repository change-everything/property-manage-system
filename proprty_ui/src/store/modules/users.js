/**
 * @author: 孙明君
 * @date: 2022-06-10
*/
export default {
    namespaced: true, // 为每个模块添加一个前缀名，保证模块命明不冲突 
    state: () => {
        return{
            userId:'',
            userName:'',
            userNum:'',
            authority:[],
            token:'',
            role:'',


            //是否登录
            isLogin:window.sessionStorage.getItem("isLogin")?Boolean(window.sessionStorage.getItem("isLogin")): false,
            //登录用户信息
            userInfo:(window.sessionStorage.getItem("userInfo"))? JSON.parse(window.sessionStorage.getItem("userInfo")):[],
            
            //权限信息
            permCode:(window.sessionStorage.getItem("permCode"))? JSON.parse(window.sessionStorage.getItem("permCode")):[],

        }
    },
    //同步方法
    mutations: {
        updateUserId(state,userId){
            state.userId = userId;
        },

        updateUserName(state,userName){
            state.userName = userName;
        },

        updateAuthority(state,authority){
            state.authority = authority;
        },

        updateToken(state,token){
            state.token = token;
        },

        clearAllInfo(state){
            state.userId = '',
            state.userName = '',
            state.authority = [],
            state.token = '',
            state.userNum = '',
            state.role = ''
        },

        updateUserNum(state,userNum){
            state.userNum = userNum;
        },

        updateRole(state,role){
            state.role = role;
        },



        //登陆成功
        LoginSuccess(state,data){
            state.isLogin=true
            state.isRefresh=true
            window.sessionStorage.setItem("isLogin",true)
            state.userInfo=data.userInfo
            state.permCode=data.permCode
            console.log(data)
            sessionStorage.setItem('userInfo',JSON.stringify(data.userInfo))
            sessionStorage.setItem('permCode',JSON.stringify(data.permCode))
        },
        //存储token
        SaveToken(state,token){
            state.token=token
        },
        //退出登录
        LoginExit(state){
            state.isLogin=false;
            state.userInfo = [];
            window.sessionStorage.setItem("isLogin",false);
            window.sessionStorage.removeItem("userInfo");
        }

    },
    //异步方法
    actions: {

        //向mutation提交登陆成功
        LoginSuccess({commit},data){
            commit('LoginSuccess',data)
        },
        //存储token
        SaveToken({commit},token){
            commit('SaveToken',token)
        },
        //退出登录
        LoginExit({commit}){
            commit('LoginExit')
        }
    },

    getters:{
        //用户信息
        userInfo(state){
            return state.userInfo
        },
        
        //是否登录
        IsLogin(state){
            return state.isLogin
        },

        permCode(state){
            return state.permCode;
        }
    }
  }