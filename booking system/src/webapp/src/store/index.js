import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage['curToken'] ? localStorage['curToken'] : undefined,
    user: localStorage['curUser'] ? JSON.parse(localStorage['curUser']) : {},
  },
  getters: {
    logged: state => { return state.token !== undefined }
  },
  mutations: {
    login(state, data) {
      state.token = data.token
      delete data.token
      state.user = data
      localStorage.setItem('curToken', state.token)
      localStorage.setItem('curUser', JSON.stringify(state.user))
    },
    logout(state){
      state.token = undefined
      state.user = {}
      localStorage.removeItem('curToken')
      localStorage.removeItem('curUser')
    },
  },
  actions: {
  },
  modules: {
  }
})
