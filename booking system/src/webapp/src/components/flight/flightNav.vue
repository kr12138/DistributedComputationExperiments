<template>
    <b-container>
        flight:
        <router-link to="/flight/view"> view </router-link> |
        <router-link to="/flight/order"> order </router-link>
        <br>
        <router-view> </router-view>
    </b-container>
</template>

<script>
    export default {
        name: "flightNav"
    }
</script>

<style scoped>

</style>