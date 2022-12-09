<template>
  <div>
    <el-menu
        style="width: 200px; height: calc( 100vh - 50px );"
        default-active="2"
        class="el-menu-vertical-demo"
        :default-openeds="user"

    >
      <el-sub-menu index="1">
        <template #title>
          <el-icon><location />
          </el-icon><span>系統管理</span>
        </template>
        <el-menu-item index="user">
          <el-icon><icon-menu /></el-icon>
          <span>使用者管理</span>
        </el-menu-item>
      </el-sub-menu>


    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>