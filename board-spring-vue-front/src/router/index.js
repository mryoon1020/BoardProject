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
    name: 'BoardListView',
    component: () => import(/* webpackChunkName: "board" */ '../views/BoardListView.vue')
  },
  {
    path: '/board/read',
    name: 'BoardReadView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "board" */ '../views/BoardReadView.vue')
  },
  {
    path: '/board/write',
    name: 'BoardWriteView',
    component: () => import(/* webpackChunkName: "board" */ '../views/BoardWriteView.vue')
  },
  {
    path: '/board/update',
    name: 'BoardUpdateView',
    component: () => import(/* webpackChunkName: "board" */ '../views/BoardUpdateView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
