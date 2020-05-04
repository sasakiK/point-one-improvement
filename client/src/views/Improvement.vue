<template>
    <el-card class="box-card" style="width: 80%; margin: auto;">
        <el-table :data="tableData" style="width: 100%; margin: auto;">
            <el-table-column prop="created_at" label="Date" align="center" :min-width="30">
            </el-table-column>
            <el-table-column prop="content" label="0.1%の改善" align="left" :min-width="30">
            </el-table-column>
            <el-table-column fixed="right" label="Operations" align="center" :min-width="30">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" size="small" circle @click="editImp(scope)"></el-button>
                    <el-popover trigger="click" title="Sure？">
                        <div class="btn-confirm">
                            <el-button size="mini">Cancel</el-button>
                            <el-button type="danger" size="mini" @click="removeImp(scope.row.id)">Sure</el-button>
                        </div>
                        <el-button type="danger" icon="el-icon-delete" size="small" circle slot="reference"></el-button>
                    </el-popover>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
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
}
button.el-button.el-button--danger.el-button--small.is-circle.el-popover__reference {
    margin-left: 2%;
}
</style>