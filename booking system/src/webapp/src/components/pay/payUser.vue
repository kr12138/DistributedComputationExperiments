<template>
    <b-container>
        <br>
        <h4> 用户余额 </h4>
        <br>
        <b-row>
            <b-col cols="4" offset="2" sm="3" offset-sm="3"> 用户账号： </b-col>
            <b-col cols="4" sm="3"> {{ user.id }} </b-col>
        </b-row>
        <br>
        <b-row>
            <b-col cols="4" offset="2" sm="3" offset-sm="3"> 用户昵称： </b-col>
            <b-col cols="4" sm="3"> {{ user.name }} </b-col>
        </b-row>
        <br>
        <b-row>
            <b-col cols="4" offset="2" sm="3" offset-sm="3"> 用户余额： </b-col>
            <b-col cols="4" sm="3"> {{ user.balance/100 + '.' + user.balance%100}} </b-col>
        </b-row>
        <br>
        <b-row>
            <b-col cols="4" offset="2" sm="3" offset-sm="3">
                <b-button @click=" save " variant="info"> 点我充值 </b-button>
            </b-col>
            <b-col cols="4" sm="3">
                <b-input v-model=" cash " placeholder="输入金额"> </b-input>
            </b-col>
        </b-row>
        <br>
    </b-container>
</template>

<script>
    export default {
        name: "payUser",

        mounted() {
            this.getData()
        },

        data() {
            return {
                user: {
                    name: '',
                    id: undefined,
                    password: '',
                    balance: undefined
                },
                cash: undefined
            }
        },

        methods: {
            getData() {
                this.$axios.get(
                    '/pay/user/' + this.$store.state.user.id
                ).then( response => {
                    if (response.status === 200)
                        this.user = response.data
                    else
                        this.$toastr.e('无法得到用户数据！', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法得到用户数据！', '错误：')
                    console.log(error)
                });
            },

            save() {
                let _cash = Math.floor(this.cash * 100)
                this.$axios.get(
                    '/pay/save/' + this.$store.state.user.id + '/' + _cash
                ).then( response => {
                    if (response.status === 200) {
                        this.$toastr.s('最新余额为：' + response.data.data, '存款成功！')
                        this.getData()
                    } else
                        this.$toastr.e('无法存款！', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法存款！', '错误：')
                    console.log(error)
                });
            }
        }
    }
</script>

<style scoped>

</style>