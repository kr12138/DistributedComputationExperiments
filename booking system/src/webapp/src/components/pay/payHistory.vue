<template>
    <b-container>
        <br>
        <h4> {{this.$store.state.user.name }} 的历史订单 </h4>
        <br>
        <b-table striped hover :items="flights" :fields="fields"> </b-table>
    </b-container>
</template>

<script>
    export default {
        name: "payHistory",

        data() {
            return {
                flights: [],
                fields: [
                    // { key: 'id', label: '订单号', sortable: true },
                    // { key: 'fid', label: '航班号', sortable: true },
                    // { key: 'time', label: '购买时间', sortable: true },
                    { key: 'id', label: '航班号', sortable: true },
                    { key: 'departure', label: '出发地', sortable: true },
                    { key: 'destination', label: '目的地', sortable: true },
                    { key: 'time', label: '出发时间', sortable: true },
                    { key: 'price', label: '价格', sortable: true, formatter: number => { return number/100 + '.' + number%100 } },
                ]
            }
        },

        mounted() {
            this.getFlights()
        },

        methods: {
            getFlights() {
                this.$axios.get( '/flight/bought/' + this.$store.state.user.id
                ).then( response => {
                    if (response.status === 200)
                        this.flights = response.data
                    else
                        this.$toastr.e('无法获取历史订单信息！', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法获取历史订单信息！', '错误：')
                    console.log(error)
                });
            }
        }
    }
</script>

<style scoped>

</style>