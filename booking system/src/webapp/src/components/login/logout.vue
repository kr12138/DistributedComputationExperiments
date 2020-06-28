<template>
    <div>
        <b-container fluid="sm">
            <br>
            <b-row>
                <b-col>
                    <h4> 确定要登出吗？ </h4>
                </b-col>
            </b-row>
            <br>
            <b-row>
                <b-col md="2" offset-md="4">
                    <b-button variant="info" @click=" logout "> 确定 </b-button>
                </b-col>
                <b-col md="2">
                    <b-button variant="info" @click=" cancel "> 取消 </b-button>
                </b-col>
            </b-row>
        </b-container>
        <br>
    </div>
</template>

<script>
    export default {
        name: "logout",

        methods: {
            logout() {
                this.$toastr.s('再见！<br>' + this.$store.state.user.name)
                this.$store.commit('logout', this.$store.state.user)
                this.$router.push('/home')
            },

            cancel() {
                this.$router.push('/home')
            }
        }
    }
</script>

<style scoped>

</style>