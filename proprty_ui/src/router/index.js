/**
 * @author: 孙明君
 * @date: 2022-06-08
*/
import { createRouter,createWebHistory} from "vue-router";

import store from '@/store/index.js'
const routes = [
  {
    path: '/',
    name:'Layout',
    meta:{
      path:'/',
      title:'首页',
    },
    component: () => import("../layout/layout.vue"),
    children:[
      {
        path:'',
        name:"index",
        component: ()=>import("../components/HelloWorld.vue"),
        meta:{
          path:'/',
          title:''
        }
      },
      {
        path:'/owner',
        name:"owner",
        component: ()=>import("@/viwes/owner/ownerIndex.vue"),
        meta:{
          path:'/owner',
          title:'业主信息'
        }
      },
      {
        path:'/systemParams',
        name:'系统参数',
        component: ()=> import("@/viwes/systemParam/systemParams.vue"),
        meta:{
          path:'/systemParams',
          title:'系统参数'
        },
        children:[
          {
            path:'/charges',
            name:'收费标准',
            component: ()=> import("@/viwes/systemParam/charges.vue"),
            meta:{
              path:'/systemParams',
              title:'收费标准'
            }
          },
          {
            path:'/systemSet',
            name:'系统设置',
            component: ()=> import("@/viwes/systemParam/systemSet"),
            meta:{
              path:'/systemSet',
              title:'系统设置'
            }
          }
        ]
      },

      {
        path:'/employee',
        name:"员工信息",
        component: ()=>import("@/viwes/personMessage/employee/employeeIndex.vue"),
        meta:{
          path:'/employee',
          title:'员工信息'
        }
      },

      {
        path:'/unit',
        name:"部门信息",
        component: ()=>import("@/viwes/personMessage/unit/unitIndex.vue"),
        meta:{
          path:'/unit',
          title:'部门信息'
        }
      },
      {
        path:'/role',
        name:"角色信息",
        component: ()=>import("@/viwes/personMessage/roles/roleIndex.vue"),
        meta:{
          path:'/role',
          title:'角色信息'
        }
      },
      {
        path:'/collectfees',
        name:"缴费处理",
        component: ()=>import("@/viwes/collectFees/collectfeesIndex.vue"),
        meta:{
          path:'/collectfees',
          title:'缴费处理'
        }
      },
      {
        path:'/collectfeeshostily',
        name:"历史缴费",
        component: ()=>import("@/viwes/collectFees/collectFeesHostily.vue"),
        meta:{
          path:'/collectfeeshostily',
          title:'历史缴费'
        }
      },
      // {
      //   path:'/datamonitor',
      //   name:"数据监控",
      //   component: ()=>import("@/viwes/datamonitor/index.vue"),
      //   meta:{
      //     path:'/datamonitor',
      //     title:'数据监控'
      //   }
      // },
      {
        path:'/403',
        name:"403",
        component: ()=>import("@/components/errorPage/403.vue"),
        meta:{
          path:'/403',
          title:'403'
        }
      }
    ]
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../viwes/login.vue")
  },
  {
    path: "/test",
    name: "test",
    component: () => import("../viwes/test.vue")
  }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: routes
})



//路由守卫
router.beforeEach((to, from, next) => {
  //放行登录路由
  // console.log(to);
  if(to.path == '/login'){
    next()
  }else{
    if(store.state.users.isLogin){
      next();
    }else{
      next({path:'/login'});
    }
  }
})

export default router