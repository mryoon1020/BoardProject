<template>
    <div class="flex-center-row pagenation-box">
        <button @click="handlemoveAction('prev')">prev</button>
        <div v-if="totalPage > 5 && currentPage > 4">
            <p class="pagenation-page" @click="handlemoveAction(1)">{{ "1......" }}</p>
        </div>
        <div class="pagenation-wrapper" v-for="page in viewPageNo" :key="page">
            <p class="pagenation-page" @click="handlemoveAction(page)">{{ page }}</p>
        </div>
        <div v-if="totalPage > 5 && currentPage != totalPage && viewPageNo[viewPageNo.length - 1] != totalPage ">
            <p class="pagenation-page" @click="handlemoveAction(totalPage)">{{ "......" + totalPage }}</p>
        </div>
        <button @click="handlemoveAction('next')">next</button>
        {{ currentPage > 5 }}
        {{ currentPage != 1 }}
        {{ props.totalPage > 5 }}
        {{ viewPageNo }}
        {{ `totalPage ${totalPage}` }}
        {{ `currentPage ${currentPage}` }}
    </div>
    <!-- <a href="getTotalPost(pageNo)" v-for="i in endPage" :key="i">&nbsp;{{i}}&nbsp;</a> -->
</template>
<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';
import { nextTick, onMounted, watch } from 'vue';

    const props = defineProps({ 
        viewPost :  Number
    })

    const emit = defineEmits(['prevPage', 'nextPage'])
    const { moveAction, 
            totalPage,
            getStartPage,
            getEndPage,
            startPage,
            endPage,
            currentPage,
            handleViewPageNo,
            viewPageNo,
            viewPost,
            getTotalPage
        } = useFunctionBoard('...');

    const handlemoveAction = async (action) => {
        await moveAction(action)
        await getTotalPage();
        handleViewPageNo();

    }

    // useFunctionBoard에 totalPage 정보 변화 감지.
    watch(() => props.viewPost, async (newViewPost) => {
        viewPost.value = newViewPost;
        await getTotalPage();
        handleViewPageNo();
    })

    onMounted(async () => {
        await getTotalPage();
        handleViewPageNo();
    })

</script>

// <script>
// import {ref} from 'vue'
// import axios from 'axios'

// export default {
    
//     name: 'BoardPage',
//     data(){
//         return {
//             endPage: ref([])
//         }
//     },
//     mounted(){
//         getTotalPost()
//     },
//     methods: {
//         async getTotalPost(pageNo){
//                 let currentPage = 0;
//                 let pageIndex = pageNo;
//                 let viewPost = 10;
//             try{
//                 const response = await axios.get('http://localhost:8000/board/countTotalPost',{
//                     params:{
//                         boardCategoryNo : '',
//                         startDate : '',
//                         endDate : '',
//                         searchKeyWord : '',
//                         pageIndex : pageIndex,
//                         viewPost : viewPost,
//                     }
//                 })
//                 console.log(response)
//                 this.endPage = Math.floor((response.data)/viewPost);
//             } catch{
//                 console.log(error)
//             }
//         },
//     }
// }
// </script>
