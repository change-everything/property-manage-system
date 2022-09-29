/**
 * @author: 孙明君
 * @date: 2022-06-09
*/
import axios from 'axios'
import router from "@/router";
import store from '../store';
import { ElNotification } from 'element-plus'


axios.defaults.baseURL='/api'

const request = axios.create({
	timeout: 5000,
	headers: {
		'Content-Type': "application/json; charset=utf-8"
	}
})

//请求封装 ———— 添加token
request.interceptors.request.use(config => {
	config.headers['Authorization'] = store.state.users.token;
	return config
})


//响应拦截处理
request.interceptors.response.use(
	(response) => {
		console.log("response ->" ,response)

		let res = response

		if (res.status === 200) {

			if(res.data.statusCode === 3001){
				ElNotification.error("该账户无权限");
				router.push('/403');
			}else if(res.data.statusCode === 2001){
				ElNotification.error("请先登录");
				router.push('/login');
			}
			return response.data
		}if(res.status === 404){
			ElNotification.error('页面不存在')
		}
		else{
			ElNotification.error('服务器连接异常')
			return Promise.reject(response.data.data.message)
		}
	},

	(error) => {
	// ! 服务器响应失败时，干些事情： 导致响应失败的原因有很多，其中之一是 token 过期
    // 响应失败时的error(错误对象)，它经过了axios的2层包装，服务器响应的真实数据在 error.response.data 中。
    // axios包装的提示信息是：error.message，与服务器响应的真实数据是两回事
    const realData = error.response.data

    /* 处理token失效---后端处理 */
    if (error.response && realData && realData.code === 233333) {
      // 以上三个条件全部满足时，才说明token超时
      // 1. 触发actions中的logout函数，清除无效token、当前用户信息
      store.commit('users/clearAllInfo')
      // 2. 跳转到登录页面
      router.push({ path: '/login' })
      // 3. return 一个执行错误，用于终止当前的promise执行链
      return Promise.reject(error)
    } else {
      /*  如果token未失效，则是其他错误 */
      // 1. 提示错误信息
      ElNotification.error("服务器响应异常，请稍后再试")
      // 2. return 一个执行错误
      return Promise.reject(error)
    }
	}
)

export default request