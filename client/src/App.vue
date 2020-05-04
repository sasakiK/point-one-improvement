<template>
  <div id="app">
    <div id="nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
      <el-menu-item index="1"><router-link to="/" style="text-decoration: none; display:block;">Principles for success 92, No.10</router-link></el-menu-item>
      <el-menu-item index="2"><router-link to="/about" style="text-decoration: none; display:block;">About</router-link></el-menu-item>
      <!-- <el-menu-item index="1"><a href="/">Principles for success 92, No.10</a></el-menu-item>
      <el-menu-item index="2"><a href="/about">About</a></el-menu-item> -->
    </el-menu>

      <h1 style="padding-top:4%;">Principles for success 92, No.10</h1>

    </div>
    <router-view/>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        activeIndex: '1'
      }
    }
  }
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;

  position: fixed;
  width: 100%;
  height: 100%;
  overflow-x: hidden
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      // color: #42b983;
      color: #C00014;
    }
  }
  .el-menu--horizontal>.el-menu-item.is-active {
    border-bottom: 2px solid #C00014;
  }
}
</style>
