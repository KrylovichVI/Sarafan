import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'
import 'api/resource'
import router from 'router/router'
import App from 'pages/App.vue'
import store from 'store/store'
import { connect } from './util/ws'
import 'vuetify/dist/vuetify.min.css'
import * as Sentry from '@sentry/browser'
import * as Integrations from '@sentry/integrations'

Sentry.init({
    dsn: 'https://fc8afdc55ec143acaef38de8666d602e@sentry.io/3054295',
    integrations: [new Integrations.Vue({Vue, attachProps: true})],
})

Sentry.configureScope(scope =>
    scope.setUser({
        id: profile && profile.id,
        username: profile && profile.name
    })
)

if (profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    router,
    render: a => a(App)
})

