import Vue from "vue";
import VueRouter from "vue-router";

import Main from "../views/Main";
import Login from "../views/Login";

import UserList from '../views/user/List';
import UserProfile from '../views/user/Profile';
import NotFound from "../views/NotFound";

//使用
Vue.use(VueRouter);
//导出
export default new VueRouter({
  mode:'history',
  routes: [
    {
      //登录页
      path: '/main',
      component: Main,
      //  写入子模块
      children: [
        {
          path: '/user/profile/:id',
          name: "UserProfile",
          component: UserProfile
        }, {
          path: '/user/list/',
          name: "UserList",
          component: UserList,
          props: true
        }
      ]
    },
    //首页
    {
      path: '/login',
      component: Login

    },{
      path: '/goHome',
      redirect: '/main'
    },{
      path:'*',
      component: NotFound
    }
  ]
})
