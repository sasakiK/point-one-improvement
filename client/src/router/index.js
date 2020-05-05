import Vue from 'vue'
import Router from 'vue-router'
import Improvement from '../views/Improvement.vue'
import firebase from 'firebase'

Vue.use(Router)

let router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'Improvement',
      component: Improvement,
      meta: { requiresAuth: true }
    },
    {
      path: '/about',
      name: 'About',
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
      path: '/signup',
      name: 'Signup',
      component: () => import(/* webpackChunkName: "singup" */ '../views/Signup.vue')
    },
    {
      path: '/signin',
      name: 'Signin',
      component: () => import(/* webpackChunkName: "singin" */ '../views/Signin.vue')
    }
  ]
})

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  if (requiresAuth) {
    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
          next()
        } else {
          next({
            path: '/signin',
            query: {redirect: to.fullPath }
          })
        }
    })
  } else {
    next()
  }
})

export default router
