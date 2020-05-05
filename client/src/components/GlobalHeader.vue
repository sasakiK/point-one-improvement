<template>
    <div class="global-header">
      <el-row style="width:90%; margin:auto;">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1"><router-link to="/" style="text-decoration: none; display:block;">Principles for success 92, No.10</router-link></el-menu-item>
          <el-menu-item index="2"><router-link to="/about" style="text-decoration: none; display:block;">About</router-link></el-menu-item>
        </el-menu>
      </el-row>
    </div>
</template>

<script>
import firebase from 'firebase'

export default {
    name: "GlobalHeader",
    methods: {
      checkIsLogin: async function() {
        firebase.auth().onAuthStateChanged(function(user) {
          if (user) {
            this.isLogin = true
          }
        })
      },
    },
    data() {
      return {
        isLogin: false,
        activeIndex: '1'
      }
    },
    created() {
      this.checkIsLogin()
    }
}
</script>


<style scoped lang="scss">
.global-header {
  padding: 30px;

  .el-menu--horizontal > .el-menu-item.dock-right {
    float: right;
  }

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #C00014;
    }
  }
  .el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 2px solid #C00014;
  }
}
</style>