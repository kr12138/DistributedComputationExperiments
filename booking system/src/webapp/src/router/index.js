import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        meta: {title: '主页'},
        redirect: '/home/info',
        component: () => import('../views/home.vue'),
        children:
        [
            {
                path: 'info',
                meta: {title: '说明'},
                component: () => import('../components/info.vue')
            },
            {
                path: 'login',
                meta: {title: '登录'},
                component: () => import('../components/login/login.vue')
            },
            {
                path: 'logout',
                meta: {title: '登出'},
                component: () => import('../components/login/logout.vue')
            },
            {
                path: 'register',
                meta: {title: '注册'},
                component: () => import('../components/login/register.vue')
            }
        ]
    },
    {
        path: '/flight',
        redirect: '/flight/view',
        component: () => import('../components/flight/flightNav.vue'),
        children:
        [
            {
                path: 'search',
                meta: {title: '搜索航班'},
                component: () => import('../components/flight/flightSearch.vue')
            },
            {
                path: 'view',
                meta: {title: '浏览航班'},
                component: () => import('../components/flight/flightView.vue')
            },
            {
                path: 'order/:id',
                meta: {title: '下单'},
                component: () => import('../components/flight/flightOrder.vue')
            },
        ]
    },
    {
        path: '/pay',
        redirect: '/pay/user',
        component: () => import('../components/pay/payNav.vue'),
        children:
            [
                {
                    path: 'user',
                    meta: {title: '用户余额'},
                    component: () => import('../components/pay/payUser.vue')
                },
                {
                    path: 'history',
                    meta: {title: '历史订单'},
                    component: () => import('../components/pay/payHistory.vue')
                },
                {
                    path: 'settings',
                    meta: {title: '用户设置'},
                    component: () => import('../components/pay/paySettings.vue')
                },
            ]
    },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(
  //       /* webpackChunkName: "about" */ '../views/About.vue'
  //       )
  // }
]

const router = new VueRouter({
  routes
})

export default router
