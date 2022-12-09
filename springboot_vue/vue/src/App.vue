<template>
  <div>
<!--    頭部-->
    <Header />
<!--主體-->

    <div style="display: flex">
<!--      側邊欄-->
      <Aside />
<!--      內容區域-->
      <router-view style="flex: 1"/>
    </div>


  </div>
</template>

<style>

</style>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

export default {
  name : "Layout",
  components: {
    Header,Aside
  }
}
</script>
