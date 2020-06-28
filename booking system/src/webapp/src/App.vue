<template>
    <div id="app">
        <myHeader></myHeader>
        <br>
        <router-view></router-view>
        <myFooter></myFooter>
    </div>
</template>

<script>
    import myHeader from '@/components/myHeader.vue'
    import myFooter from '@/components/myFooter.vue'

    export default {
        name: 'app',

        components: {
            myHeader,
            myFooter,
        },
    }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
