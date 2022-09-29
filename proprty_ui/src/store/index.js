/**
 * @author: 孙明君
 * @date: 2022-06-10
*/

// 引入vuex
//入口文件 —— 负责模块管理
import { createStore } from 'vuex';

import users from "./modules/users.js"

export default createStore({
    //模块
    modules: {
        users
    }
  })