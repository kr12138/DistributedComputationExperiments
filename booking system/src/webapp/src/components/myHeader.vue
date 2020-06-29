<template>
    <b-container fluid class="myContainer">
        <br>
        <b-row>
            <b-col sm="8" md="6" lg="4" offset-lg="1">
                <span class="myText" style="text-shadow: #2c3e50 5px 3px 3px;"
                          v-b-toggle.sidebar-no-header> 订票宝 </span>
                <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header backdrop>
                    <template v-slot:default="{ hide }">
                        <div class="p-3">
                            <h4 id="sidebar-no-header-title"> 功能导航 </h4>
                            <nav class="mb-3">
                                <b-nav vertical>
                                    <b-nav-item to="/home"> <b-icon-house-door/> 网站首页 </b-nav-item>
                                    <b-nav-item to="/flight/search"> <b-icon-search/> 航班搜索 </b-nav-item>
                                    <b-nav-item to="/flight"> <b-icon-cart/> 航班浏览 </b-nav-item>
                                    <b-nav-item to="/pay/settings"> <b-icon-gear/> 用户设置 </b-nav-item>
                                    <b-nav-item to="/pay"> <b-icon-credit-card/> 账户余额 </b-nav-item>
                                </b-nav>
                            </nav>
                            <b-button variant="light" block @click="hide"> <b-icon-x-circle/> 取消 </b-button>
                        </div>
                    </template>
                </b-sidebar>
            </b-col>

            <b-dropdown v-if=" this.$store.getters.logged "
                        :text=" this.$store.state.user.name " size="sm" variant="outline-light"
                        class="col-sm-3 col-md-2 offset-md-3 offset-lg-4 col-xl-2 offset-xl-4">
                <b-dropdown-item to="/pay/user"> <b-icon-credit-card/> 账户余额 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item to="/pay/history"> <b-icon-list-check/> 查看订单 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item to="/pay/settings"> <b-icon-gear/> 用户设置 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item to="/home/logout"> <b-icon-arrow-bar-left/> 用户登出 </b-dropdown-item>
            </b-dropdown>
            <b-dropdown v-else
                        text="登录" size="sm" variant="outline-light"
                        class="col-sm-3 col-md-2 offset-md-3 offset-lg-4 col-xl-2 offset-xl-4">
                <template v-slot:button-content>
                    <b-icon-person-circle> </b-icon-person-circle>
                </template>
                <b-dropdown-item to="/home/login"> <b-icon-arrow-bar-up/> 登录 </b-dropdown-item>
                <b-dropdown-divider> </b-dropdown-divider>
                <b-dropdown-item to="/home/register"> <b-icon-person-plus-fill/> 注册 </b-dropdown-item>
            </b-dropdown>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "myHeader",

        components: {

        },

        data() {
            return {

            }
        },
    }
</script>

<style scoped>
    a {
        color: #ddeeff;
        text-decoration: none;
    }
    a:hover {
        color: #ffffff;
        text-decoration: none;
    }
    .myText {
        font-family: Consolas, Inconsolata, "微软雅黑", serif;
        font-size: 26px;
        color: #ddeeee;
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