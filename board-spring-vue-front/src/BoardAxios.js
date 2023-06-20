import axios from 'axios'

export default {
    getData(){
      axios
      .get('http://localhost:8000/list')
      .then(response => {
        console.log(response)
        this.result = response.data
      })
      .catch((error)=>{
        console.log(error)
      })
    },
    href(boardNo){
      location.href='/board/read?boardNo='+boardNo
    }
}