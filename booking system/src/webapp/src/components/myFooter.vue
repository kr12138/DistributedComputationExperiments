<template>
    <div class="myText">
        <!-- 用户名：{{sessionStorage.getItem('name')}} -->
        <br> <br>
        <a href="//github.com/kr12138">
            My Github: github.com/kr12138
        </a><br>
        Powered By Vue.JS 2.6.11
        <br> <br> <br> <br>
    </div>
</template>

<script>
    export default {
        name: "myFooter"
    }
</script>

<style scoped>
    .myText { font-family: Consolas, Inconsolata, "微软雅黑", serif }
    a { text-decoration: none }
    a:hover { text-decoration: none }
</style>