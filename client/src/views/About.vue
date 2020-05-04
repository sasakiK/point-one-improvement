<template>
  <div class="about">
    <h4>Success comes from a series of 0.1% improvements, which will prepare you to seize great opportunities.</h4>
      <p>Are you sure to delete this?</p>
      <el-table :data="data" style="width:100%" border>
        <el-table-column prop="id" label="id" ></el-table-column>
        <el-table-column prop="name" label="Name" ></el-table-column>
        <el-table-column prop="age" label="Age" ></el-table-column>
        <el-table-column label="Action">
          <template slot-scope="scope">
            <el-button type="primary" class="mr-20" @click="edit(scope)">Edit</el-button>
            
            <el-popover placement="top" trigger="click" title="Sure？">
              <div class="btn-confirm">
                <el-button type="text" size="mini" @click="otherClick">Cancel</el-button>
                <el-button type="primary" size="mini" @click="remove(scope)">Sure</el-button>
              </div>
              <el-button type="danger" slot="reference">Delete</el-button>
            </el-popover>
          </template>
        </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        showDialog: false,
        data:[{
          id:1,
          name: 'jak',
          age: 24
        },
        {
          id:2,
          name: 'irol',
          age: 34
        }
        ]
      };
    },
    methods:{
      edit(){},
      remove(){
        //DO THE REMOVE ACTION!
        this.showDialog=false;
      }
    }
  }
</script>