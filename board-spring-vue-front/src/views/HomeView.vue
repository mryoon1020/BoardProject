<template>
  <div class="home">
    <a href="/board/list">리스트이동</a>
    <button @click="getData()">가즈아</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HomeView',
  methods:{
    getData(){ 
      axios
      .get('http://localhost:8000/list')
      .then(response => {
        console.log(response)
    })
      .catch((error)=>{
        console.log(error)
    })
    }
  }
}
</script>
