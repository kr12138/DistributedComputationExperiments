<template>
    <b-container fluid="md">
        <b-row>
            <b-col cols="4" offset="4">
                <h4> 最新航班 </h4>
            </b-col>
            <b-col>
                <b-button variant="outline-info" @click=" search "> 点我搜索具体航班 </b-button>
                <br>
            </b-col>
        </b-row>
        <br>
        <b-row>
            <b-table striped hover :items="flights" :fields="fields">
<!--                     对应 fields 中 key: 'actions' 对象的下单按钮-->
                <template v-slot:cell(actions)="row">
                    <b-button variant="outline-primary" size="sm" @click="order( row.item.id )">
                        点我下单 {{ row.item.id }}
                    </b-button>
                </template>
            </b-table>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "flightView",

        mounted() {
            this.getData()
        },

        data() {
            return {
                id: '',
                departure: '',
                destination: '',
                time: '',

                flights: [],
                fields: [
                    { key: 'id', label: '航班号', sortable: true },
                    { key: 'departure', label: '出发地', sortable: true },
                    { key: 'destination', label: '目的地', sortable: true },
                    { key: 'time', label: '出发时间', sortable: true },
                    { key: 'price', label: '价格', sortable: true, formatter: number => { return number/100 + '.' + number%100 } },
                    { key: 'count', label: '剩余票量', sortable: true },
                    { key: 'actions', label: '下单' }
                ]
            }
        },

        methods: {
            getData() {
                this.$axios.post( 'flight/query', {
                    id: this.id,
                    departure: this.departure,
                    destination: this.destination,
                    time: this.time
                }).then( response => {
                    console.log(response)
                    if (response.status === 200) {
                        this.$toastr.s('成功得到航班数据！')
                        this.flights = response.data
                    } else
                        this.$toastr.e('无法得到航班数据！', '错误：')
                }).catch( error => {
                    this.$toastr.e('无法得到航班数据！', '错误：')
                    console.log(error)
                });
            },

            order(id) {
                this.$toastr.i('正在跳转下单：' + id)
                this.$router.push('/flight/order/' + id)
            },

            search() {
                this.$router.push('/flight/search')
            }
        }
    }
</script>