<template>
    <div>
        <h1 style="padding-top:4%; padding-bottom:3%; color: #2c3e50;">Principles for success 92, No.10</h1>
        <el-card style="width: 80%; margin: auto; margin-bottom: 3%;">
            <div slot="header" class="clearfix" style="text-align:left; margin-left: 2%;">
                <h3>Please add today's improvements</h3>
            </div>

            <el-form :model="inputForm">
                <div id="form-div" style="display:inline-flex; width:100%;">
                    <el-form-item style="width:80%; margin-left:4.5%;" :error="errorMessage">
                        <el-input 
                        type="text" placeholder="..." maxlength="200" show-word-limit
                        v-model="inputForm.content"></el-input>
                    </el-form-item>
                    <el-form-item style="width:15%">
                        <el-button plain @click.prevent="addImp(inputForm.content)" native-type="submit">Submit</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </el-card>
        <el-card class="box-card" style="width: 80%; margin: auto;">
            <el-table :data="tableData" style="width: 100%; margin: auto;">
                <el-table-column prop="created_at" label="Date" align="center" :min-width="15">
                </el-table-column>
                <el-table-column prop="content" label="0.1% improvements" align="left" :min-width="50">
                </el-table-column>
                <el-table-column fixed="right" label="Operations" align="center" :min-width="30">
                    <template slot-scope="scope">

                        <el-popover width="100%" trigger="click" title="Edit contents">
                            <el-form :inline="true" :model="editForm">
                                <el-form-item :error="errorMessageEdit">
                                    <el-input type="text" placeholder="..." v-model="editForm.content"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button plain @click.prevent="editImp(scope.row.id, editForm.content)" native-type="submit">Edit</el-button>
                                </el-form-item>
                            </el-form>
                            <el-button icon="el-icon-edit" size="small" circle slot="reference"></el-button>
                        </el-popover>

                        <el-popover trigger="click" title="Can I delete this?">
                            <div class="btn-confirm">
                                <el-button type="danger" size="mini" @click="removeImp(scope.row.id)">Yes</el-button>
                            </div>
                            <el-button icon="el-icon-delete" size="small" circle slot="reference"></el-button>
                        </el-popover>

                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <el-row style="text-align:right; width: 90%; margin-top: 2%; margin-bottom:7%;">
            <sub-header/>
        </el-row>
    </div>
</template>

<script>
import axios from 'axios'
import Utils from '../utils/Utils'
import SubHeader from "../components/SubHeader";

import firebase from 'firebase'

export default {
    name: "Improvement",
    components: {SubHeader},
    methods: {
        
        setUrl: function() {
            this.getUserEmail()
            return Utils.URL + "/" + this.email
        },
        getImps: async function() {
            const URL_ONE_USER = this.setUrl()
            const res = await axios.get(URL_ONE_USER)
            this.tableData = res.data.slice().reverse()
        },
        addImp: async function(newContent) {
            const URL_ADD = this.setUrl()
            const inputContent = {content: newContent}
            this.errorMessage = ""
            if (newContent == "") {
                this.errorMessage = "Please input something"
                return;
            }
            await axios.post(URL_ADD, inputContent)
            this.inputForm.content = ''
            await this.getImps()
            this.$message({
                showClose: true,
                message: 'Nice Improvement!',
                type: 'message'
            })
        },
        editImp: async function(id, newContent) {
            const path = Utils.URL + '/' + id
            const modify = {content: newContent}
            this.errorMessageEdit = ""
            if (newContent == "") {
                this.errorMessageEdit = "Please input something"
                return;
            }
            await axios.put(path, modify)
            this.editForm.content = ''
            await this.getImps()
            this.$message({
                showClose: true,
                message: 'Nice modify!',
                type: 'message'
            })
        },
        removeImp: async function(id) {
            const path = Utils.URL + '/' + id
            await axios.delete(path)
            await this.getImps()
            this.$message({
                showClose: true,
                message: 'Nice delete!',
                type: 'message'
            })
        },
        checkIsLogin: async function() {
            var user = firebase.auth().currentUser;
            if (user != null) {
                this.isLogin = true
            }
        },
        getUserEmail: async function() {
            var user = firebase.auth().currentUser;
            if (user != null) {
                this.email = user.email
            }
        }
    },
    data() {
        return {
            isLogin: false,
            tableData: [],
            inputForm: {
                content: ''
            },
            editForm: {
                content: ''
            },
            errorMessage: '',
            errorMessageEdit: '',
            email: ''
        }
    },
    created() {
        this.getImps()
    }
}
</script>

<style lang="scss">
    @import "../styles/base";
</style>