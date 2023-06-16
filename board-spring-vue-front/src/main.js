import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// import axios from 'axios'

// axios.defaults.baseURL='http://localhost:8000';
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
// axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

createApp(App).use(router).mount('#app')
