<template>
    <div> <br>
        <h4> 登录 </h4>
        <b-container fluid="sm"> <br>
            <b-row>
                <b-col md="8" offset-md="2" xl="6" offset-xl="3">
                    <b-input-group prepend="账号">
                        <b-form-input type="number" class="form-control"
                                      name="id" v-model=" user.id ">
                        </b-form-input>
                    </b-input-group>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col md="8" offset-md="2" xl="6" offset-xl="3">
                    <b-input-group>
                        <span class="input-group-prepend">
                            <span class="input-group-text"> 密码 </span>
                        </span>
                        <b-form-input type="password" class="form-control"
                               name="password" v-model=" user.password " >
                        </b-form-input>
                    </b-input-group> <br>
                </b-col>
            </b-row>
            <b-row>
                <b-col md="4" offset-md="4">
                    <b-button variant="info" @click=" login "> 登录 </b-button>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "login",

        data() {
            return {
                user: {
                    name: '',
                    id: undefined,
                    password: ''
                },
            }
        },

        methods: {
            login() {
                if (this.user.id !== 0 && !this.user.id) {
                    this.$toastr.i('请先输入账号', '提示：')
                    return
                } else if (this.user.password === '' || this.user.password == null) {
                    this.$toastr.i('请先输入密码', '提示：')
                    return
                }

                // this.$axios.post(
                //     'api/user/login', this.user
                // ).then ( response => {
                //     console.log(response)
                //     if (!response.data.flag || response.data.flag === 'false')
                //         this.$toastr.e('密码错误<br>或不存在该用户！')
                //     else {

                        let user = {
                            name: 'user1',
                            id: 1,
                            password: 'password',
                            token: 'user1token'
                        }
                        this.$toastr.s('欢迎！<br>' + user.name)
                        this.$store.commit('login', user)
                        // this.$toastr.i('localStorage: ' + localStorage['curUser'])
                        // this.$toastr.i('$store: ' + JSON.stringify(this.$store.state.user))
                        this.$router.push('/flight/view')
                //     }
                // }).catch ( error => {
                //     console.log('！！！登录失败异常：')
                //     console.log(error)
                // });
            },
        }

    }
</script>

<style scoped>

</style>