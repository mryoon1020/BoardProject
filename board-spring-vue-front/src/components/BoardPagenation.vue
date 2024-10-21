<template>

    <a href="getTotalPost(pageNo)" v-for="i in endPage" :key="i">&nbsp;{{i}}&nbsp;</a>

</template>

<script>
import {ref} from 'vue'
import axios from 'axios'

export default {
    
    name: 'BoardPage',
    data(){
        return {
            endPage: ref([])
        }
    },
    mounted(){
        getTotalPost()
    },
    methods: {
        async getTotalPost(pageNo){
                let currentPage = 0;
                let pageIndex = pageNo;
                let viewPost = 10;
            try{
                const response = await axios.get('http://localhost:8000/board/countTotalPost',{
                    params:{
                        boardCategoryNo : '',
                        startDate : '',
                        endDate : '',
                        searchKeyWord : '',
                        pageIndex : pageIndex,
                        viewPost : viewPost,
                    }
                })
                console.log(response)
                this.endPage = Math.floor((response.data)/viewPost);
            } catch{
                console.log(error)
            }
        },
    }
}
</script>
