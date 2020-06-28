<template>
    <b-container>
        <b-row>
            <b-col cols="12" md="10" offset-md="1" xl="8" offset-xl="2">
                <b-card header="说明：" align="left">
                    <b-card header="本订票系统：" align="left">
                        <b-list-group>
                            <b-list-group-item>
                                <b-row>
                                    <b-col align="left" cols="3"> 前端基于： </b-col>
                                    <b-col align="left"> BootstrapVue 2.15.0 </b-col>
                                    <b-col align="left"> 和 Vue.JS 2.6.11 </b-col>
                                </b-row>
                            </b-list-group-item>
                            <b-list-group-item>
                                <b-row>
                                    <b-col align="left" cols="3"> 后端基于： </b-col>
                                    <b-col align="left"> Java 1.8.1 </b-col>
                                    <b-col align="left"> 和 Spring 2.3.0 </b-col>
                                </b-row>
                            </b-list-group-item>
                            <b-list-group-item>
                                <b-row>
                                    <b-col align="left" cols="3"> 数据库： </b-col>
                                    <b-col align="left"> MySQL 8.0.11 </b-col>
                                    <b-col align="left"> 和 Redis 6.0.2 </b-col>
                                </b-row>
                            </b-list-group-item>
                        </b-list-group>
                    </b-card>
                </b-card>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "info"
    }
</script>

<style scoped>

</style>