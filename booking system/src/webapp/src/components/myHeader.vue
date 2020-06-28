<template>
    <b-container fluid="lg" class="myContainer">
        <br>
<!--        <router-link to="/"> home </router-link> |-->
<!--        <router-link to="/flight"> flight </router-link>-->
        <b-row>
            <router-link class="myText col-sm-8 col-md-6 col-lg-4 offset-lg-1"
                         to="/">
                <h3 style="text-shadow: #2c3e50 5px 3px 3px;">
                    订票宝
                </h3>
            </router-link>
            <b-dropdown v-if=" this.$store.getters.logged "
                        :text=" this.$store.state.user.name " variant="info"
                        class="col-sm-3 col-md-2 offset-md-3 offset-lg-4 col-xl-2 offset-xl-4">
                <b-dropdown-item> 用户设定 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item> 查看订单 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item to="/home/logout"> 用户登出 </b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-else
                        text="登录" variant="info"
                        class="col-sm-3 col-md-2 offset-md-3 offset-lg-4 col-xl-2 offset-xl-4">
                <b-dropdown-item to="/home/login"> 登录 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item> 注册 </b-dropdown-item>
            </b-dropdown>
        </b-row>
    </b-container>
</template>

<script>
    // import loginModal from '@/loginModal.vue'
    // import logoutModal from '@/logoutModal.vue'
    // import registerModal from '@/registerModal.vue'
    // import settings from '@/settings.vue'

    export default {
        name: "myHeader",

        components: {
        // loginModal,
        // logoutModal,
        // registerModal,
        // settings,
        },

        data() {
            return {
            }
        },
    }
</script>

<style scoped>
    a { text-decoration: none; }
    a:hover { text-decoration: none; }
    .myText {
        font-family: Consolas, Inconsolata, "微软雅黑", serif;
        font-size: 30px;
        color: #ddeeee;
    }
    button {
        background-color: #4a7eaf;
        border-color: #4a7eaf;
    }
    button:hover {
        background-color: #528db6;
        border-color: #4a7eaf;
    }
    .myContainer {
        height: 100px;
        background-color: #1c5f9c;
        /* Permalink - use to edit and share this gradient: https://colorzilla.com/gradient-editor/#447199+0,1c5f9c+100 */
        background: rgb(68,113,153); /* Old browsers */
        background: -moz-linear-gradient(45deg, rgba(68,113,153,1) 0%, rgba(28,95,156,1) 100%); /* FF3.6-15 */
        background: -webkit-linear-gradient(45deg, rgba(68,113,153,1) 0%, rgba(28,95,156,1) 100%); /* Chrome10-25,Safari5.1-6 */
        background: linear-gradient(45deg, rgba(68,113,153,1) 0%, rgba(28,95,156,1) 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#447199', endColorstr='#1c5f9c', GradientType=1 ); /* IE6-9 fallback on horizontal gradient */

        margin: 0 auto;
    }
</style>