<template>
  <div style="padding: 10px">
<!--    功能區域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">導入</el-button>
      <el-button type="primary">導出</el-button>
    </div>
<!--    搜索區域-->
    <div style="margin: 10px">
      <el-input v-model="search" placeholder="請輸入關鍵字" style="width: 30%" clearable/>
      <el-button type="primary" style="margin-left: 10px" @click="load"

      >搜尋</el-button>

    </div>
<!--    資料區域-->

    <el-table
        :data="tableData"
        style="width: 100%"
        stripe
    >
      <el-table-column
          prop="id"
          label="ID"
          sortable  />
      <el-table-column
          prop="username"
          label="帳號"
          sortable  />
      <el-table-column
          prop="password"
          label="密碼"
          sortable />
      <el-table-column
          prop="address"
          label="地址"
          sortable />
      <el-table-column
          prop="nickName"
          label="暱稱"
          sortable />
      <el-table-column
          prop="age"
          label="年齡"
          sortable />
      <el-table-column
        prop="sex"
        label="性別"
        sortable />

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)"
          >編輯</el-button
          >
          <el-popconfirm title="確認刪除嗎?" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button
                  size="small"
                  type="danger"
                  @click=""
              >刪除</el-button
              >
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
<!--    分頁區-->
    <div style="margin: 10px 0">
      <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog
          v-model="dialogVisible"
          title="確認"
          width="30%"

      >
        <el-form :model="form" label-width="120px">
          <el-form-item label="帳號" prop="username">
            <el-input v-model="form.username" style="width:  80%;"/>
          </el-form-item>
          <el-form-item label="密碼" prop="password">
            <el-input v-model="form.password" style="width:  80%;"/>
          </el-form-item>
          <el-form-item label="暱稱" prop="nickname">
            <el-input v-model="form.nickName" style="width:  80%;"/>
          </el-form-item>
          <el-form-item label="年齡" prop="age">
            <el-input v-model="form.age" style="width:  80%;"/>
          </el-form-item>
          <el-form-item label="性別" prop="sex">
            <el-radio-group v-model="form.sex" class="ml-4">
              <el-radio  label="男" size="large">男</el-radio>
              <el-radio  label="女" size="large">女</el-radio>
            </el-radio-group>

          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input type="textarea" v-model="form.address" style="width:  80%;"/>
          </el-form-item>

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">
          確定
        </el-button>
      </span>
        </template>
      </el-dialog>
    </div>

  </div>
</template>

<script>
// @ is an alias to /src


import request from "@/utils/request";

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return {
      form: {},
      search: "",
      currentPage:1,
      pageSize:10,
      total: 0 ,
      tableData: [],
      dialogVisible:false,

    }


    },
  created() {
    this.load()



  },
  methods:{
    // search(){
    //
    // },
    load(){
      request.get("/api/user",{
        params:{pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search}

      }).then((res)=>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    add(){
      this.dialogVisible = true;
      this.form = {}
      this.load()
    },
    handleEdit(row){
      // console.log(row)
      // console.log(this.form)
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
      // this.save()

    },
    handleDelete(id){
      console.log(id)
      request.delete("api/user/" + id).then((res)=>{
        console.log(res)
        if(res.code == 200){
          this.$message({
            type:"success",
            message: "更新成功"
          })
        }else{
          this.$message({
            type:"error",
            message: res.msg
          })
        }
      })
      this.load()  //刪除之後重新讀取表格資料
    },
    handleCurrentChange(pageSize){ //改變目前的頁數觸發
      // this.pageSize= pageSize
      this.load()
    },
    handleSizeChange(pageNum){ //改變每頁的個數觸發
      this.currentPage = pageNum
      this.load()
    },
    save(){
      if(this.form.id){ //更新
        request.put("/api/user", this.form).then((res)=>{
          console.log(res)
          if(res.code == 200){
            this.$message({
              type:"success",
              message: "更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false


        })
      }else{ //新增
        request.post("/api/user", this.form).then((res)=>{
          console.log(res)
          if(res.code == 200){
            this.$message({
              type:"success",
              message: "新增成功"
            })
          }else{
            this.$message({
              type:"error",
              message: res.msg
            })
          }
        })
      }



      this.dialogVisible = false
    }

  }


}
</script>
