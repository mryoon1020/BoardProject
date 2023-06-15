import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/board/list',
    name: 'ListView',
    component: () => import(/* webpackChunkName: "board" */ '../views/ListView.vue')
  },
  {
    path: '/board/read',
    name: 'ReadView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "board" */ '../views/ReadView.vue')
  },
  {
    path: '/board/write',
    name: 'WriteView',
    component: () => import(/* webpackChunkName: "board" */ '../views/WriteView.vue')
  },
  {
    path: '/board/update',
    name: 'UpdateView',
    component: () => import(/* webpackChunkName: "board" */ '../views/UpdateView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
