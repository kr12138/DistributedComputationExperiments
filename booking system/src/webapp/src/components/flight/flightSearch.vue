<template>
    <b-container fluid="md">
        <h4> 搜索航班 </h4>
        <br>
        <b-row>
            <b-col cols="2">
                <b-button variant="info" disabled> 输入航班号： </b-button>
            </b-col>
            <b-col cols="2">
                <b-button variant="info" disabled> 输入出发地： </b-button>
            </b-col>
            <b-col cols="2">
                <b-button variant="info" disabled> 输入目的地： </b-button>
            </b-col>
            <b-col cols="3">
                <b-button variant="info" disabled> 选择出发日期： </b-button>
            </b-col>
            <b-col>
                <b-button variant="info" disabled> 搜索： </b-button>
            </b-col>
        </b-row>
        <br>
        <b-row>
            <b-col cols="2">
                <b-input placeholder="航班号" v-model=" searchParams.id "/>
            </b-col>
            <b-col cols="2">
                <b-input placeholder="出发地" v-model=" searchParams.departure "/>
            </b-col>
            <b-col cols="2">
                <b-input placeholder="目的地" v-model=" searchParams.destination "/>
            </b-col>
            <b-col cols="3" v-if=" ! need.time ">
                <b-button variant="info" @click=" showCalendar "> 指定出发日期 </b-button>
            </b-col>
            <b-col cols="3" v-else>
                <b-button variant="info" @click=" showCalendar "> 选完了！ </b-button>
            </b-col>
            <b-col cols="3"> <b-button variant="info" @click=" getData "> <b-icon-search/> </b-button> </b-col>
        </b-row>
        <br>
        <b-row>
            <b-col>
                <b-calendar v-show=" need.time " v-model=" searchParams.time "/>
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
        name: "flightSearch",

        mounted() {
            this.getData()
        },

        data() {
            return {
                need: {
                    id: false,
                    departure: false,
                    destination: false,
                    time: false,
                },
                searchParams: {
                    id: '',
                    departure: '',
                    destination: '',
                    time: '',
                },

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
            showCalendar() {
                this.need.time = ! this.need.time
            },

            getData() {
                this.$axios.post( 'flight/query', {
                    id: this.searchParams.id,
                    departure: this.searchParams.departure,
                    destination: this.searchParams.destination,
                    time: this.searchParams.time.substr(2),
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
            }
        }
    }
</script>