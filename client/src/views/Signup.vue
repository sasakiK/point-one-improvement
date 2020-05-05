<template>
    <el-card style="width: 40%; margin: auto; margin-bottom: 10%;">
        <div slot="header" class="clearfix" style="text-align:center;">
                <h2>Please signup</h2>
        </div>
        <el-form style="text-align:center;">
            <el-form-item>
                <el-input type="text" placeholder="Email" v-model="email">
                </el-input>
            </el-form-item>
                <el-input placeholder="Password" v-model="password" show-password>
                </el-input>

            <el-button style="align:left;" @click="signUp">Register</el-button>
            <p>Do you have an account?
                <router-link to="/signin">sign in now!!</router-link>
            </p>
        </el-form>
    </el-card>
</template>


<script>

import firebase from 'firebase'

export default {
    name: "Signup",
    data () {
      return {
        email: undefined,
        password: undefined
      }
    },
    methods: {
      signUp: async function() {
        await firebase.auth().createUserWithEmailAndPassword(this.email, this.password)
          .then(() => {
            this.email = undefined
            this.password = undefined
            this.$message({
              showClose: true,
              message: 'Successfully registered!',
              type: 'message'
            })
          })
          .catch(error => {
            this.$message({
              showClose: true,
              message: error,
              type: 'message'
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