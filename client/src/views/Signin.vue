<template>
    <el-card style="width: 40%; margin: auto; margin-bottom: 10%;">
        <div slot="header" class="clearfix" style="text-align:center;">
                <h2>Please sign in</h2>
        </div>
        <el-form style="text-align:center;">
            <el-form-item>
                <el-input type="text" placeholder="Email" v-model="email">
                </el-input>
            </el-form-item>
                <el-input placeholder="Password" v-model="password" show-password>
                </el-input>

            <el-button style="align:left;" @click="signIn">Signin</el-button>
            <p>You don't have an account?</p>
            <p><router-link to="/signup">Create account now !</router-link></p>
        </el-form>
    </el-card>
</template>

<script>
  import firebase from 'firebase'

  export default {
    name: "Signin",
    data () {
      return {
        email: '',
        password: ''
      }
    },
    methods: {
      signIn: async function () {
        await firebase.auth().signInWithEmailAndPassword(this.email, this.password)
          .then(() => this.$router.push('/'))
          .catch(error => {
            this.$message({
              showClose: true,
              message: error,
              type: 'error'
            })
          })
      },
    }
  }
</script>

<style scoped>
.el-button { 
    margin-top: 5%;
}
.el-input {
    width: 85%;
}
</style>