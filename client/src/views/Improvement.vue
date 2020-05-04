<template>
    <div>
        <el-card style="width: 80%; margin: auto; margin-bottom: 3%;">
            <h5 style="text-align:left;">Please add today's improvement.</h5>
        </el-card>
        <el-card class="box-card" style="width: 80%; margin: auto;">
            <el-table :data="tableData" style="width: 100%; margin: auto;">
                <el-table-column prop="created_at" label="Date" align="center" :min-width="15">
                </el-table-column>
                <el-table-column prop="content" label="0.1%の改善" align="left" :min-width="50">
                </el-table-column>
                <el-table-column fixed="right" label="Operations" align="center" :min-width="30">
                    <template slot-scope="scope">

                        <el-popover width="100%" trigger="click" title="Edit contents">
                            <el-form :inline="true" :model="form">
                                <el-form-item>
                                    <el-input type="text" placeholder="..." v-model="form.content"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button plain @click="editImp(scope.row.id, form.content)" native-type="submit">Edit</el-button>
                                </el-form-item>
                            </el-form>
                            <el-button type="primary" icon="el-icon-edit" size="small" circle slot="reference"></el-button>
                        </el-popover>

                        <el-popover trigger="click" title="Can I delete this?">
                            <div class="btn-confirm">
                                <el-button type="danger" size="mini" @click="removeImp(scope.row.id)">Yes</el-button>
                            </div>
                            <el-button type="danger" icon="el-icon-delete" size="small" circle slot="reference"></el-button>
                        </el-popover>

                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios'
import Utils from '../utils/Utils'

export default {
    name: "Improvement",
    methods: {
        handleClick() {
            console.log('click');
        },
        getImps() {
            this.tableData = this.getImpsFromBackend()
        },
        getImpsFromBackend () {
            axios.get(Utils.URL)
                .then(response => {
                    this.tableData = response.data.slice().reverse();
                })
                .catch(error => {
                    console.log(error)
                })
        },
        editImp(id, newContent) {
            const path = Utils.URL + '/' + id
            const modify = {content: newContent}
            axios.put(path, modify)
                .then(response => {
                    console.log(response)
                    this.getImps()
                })
                .catch(error => {
                    console.log(error)
                })
        },
        removeImp(id) {
            const path = Utils.URL + '/' + id
            axios.delete(path)
                .then(response => {
                    console.log(response)
                    this.getImps()
                })
                .catch(error => {
                    console.log(error)
                })
        }
    },
    data() {
        return {
            tableData: [],
            form: {
                content: ''
            }
        }
    },
    created() {
        this.getImps()
    }
}
</script>

<style>
.el-popover__title {
    font-family: Arial, Helvetica, sans-serif;
    text-align: center;
}
.el-popover {
    font-family: Arial, Helvetica, sans-serif;
}

div.btn-confirm {
    text-align: center;
}

button.el-button.el-button--danger.el-button--small.is-circle.el-popover__reference {
    margin-left: 2%;
}
</style>