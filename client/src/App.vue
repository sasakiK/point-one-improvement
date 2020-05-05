<template>
  <el-container id="app">
    <el-header id="nav"><global-header/></el-header>
    <el-main><router-view/></el-main>
  </el-container>
</template>

<script>
import GlobalHeader from './components/GlobalHeader'
export default {
  components: {
    GlobalHeader
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
</style>
