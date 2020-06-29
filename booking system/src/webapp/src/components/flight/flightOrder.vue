<template>
    <b-container>
        <b-table striped hover :items="flight" :fields="fields"> </b-table>
        <h4> {{ '确认要下单 ' + $route.params.id + ' 吗？' }} </h4>
        <h5> 您的当前账户余额：{{ this.user.balance }} </h5>
        <br>
        <b-row>
            <b-col cols="4" offset="2" sm="3" offset-sm="3">
                <b-button @click=" order " variant="info"> 确定 </b-button>
            </b-col>
            <b-col cols="4" sm="3">
                <b-button @click=" cancel " > 取消 </b-button>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "flightOrder",

        data() {
            return {
                user: {},
                flight: [],
                fields: [
                    { key: 'id', label: '航班号', sortable: true },
                    { key: 'departure', label: '出发地' },
                    { key: 'destination', label: '目的地' },
                    { key: 'time', label: '出发时间', sortable: true },
                    { key: 'price', label: '价格', sortable: true, formatter: number => { return number/100 + '.' + number%100 } },
                    { key: 'count', label: '剩余票量' },
                ]
            }
        },

        mounted() {
            this.getFlight(this.$route.params.id)
            this.getUser(this.$store.state.user.id)
        },

        methods: {
            getFlight(fid) {
                this.$axios.post( '/flight/query', {
                    id: this.$route.params.id
                }).then( response => {
                    if (response.status === 200) {
                        this.flight = response.data
                    } else
                        this.$toastr.e('无法获取航班信息', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法获取航班信息', '错误：')
                    console.log(error)
                });
            },

            getUser(uid) {
                this.$axios.get( '/pay/user/' + this.$store.state.user.id
                ).then( response => {
                    if (response.status === 200) {
                        let user = response.data
                        user.balance = user.balance/100 + '.' + user.balance%100
                        this.user = user
                    } else
                        this.$toastr.e('无法获取用户信息！', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法获取用户信息！', '错误：')
                    console.log(error)
                });
            },

            order() {
                this.$axios.post( '/flight/order', {
                        uid: this.$store.state.user.id,
                        fid: this.$route.params.id
                }).then( response => {
                    if (response.status === 200) {
                        this.$toastr.s('下单成功！')
                        this.$router.push('/pay/history')
                    } else
                        this.$toastr.e('无法下单！错误信息：' + response.data.data, '错误：')
                }).catch( error => {
                    this.$toastr.e('无法下单！错误信息：' + error, '错误：')
                    console.log(error)
                });
            },

            cancel() {
                this.$router.push('/flight')
            }
        }
    }
</script>

<style scoped>

</style>