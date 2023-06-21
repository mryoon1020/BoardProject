<template>
        <select name="boardCategory" id="boardCategory">
            <option 
            v-for="(item, index) in categoryList"
            v-bind:key="index"
            :value="item.boardCategoryNo"
            >
            {{item.boardCategoryName}}
            </option>
        </select>
</template>

<script>
import {ref} from 'vue'
import axios from 'axios'

export default {
    
    name: 'BoardCategory',
    data(){
        return {
            categoryList: ref([])
        }
    },
    mounted(){
        this.getBoardCategory()
    },
    methods: {
        async getBoardCategory(){
            try{
                const response = await axios.get('http://localhost:8000/board/category')
                console.log(response)
                this.categoryList = response.data
            } catch{
                console.log("error")
            }
        }
    }
}
</script>
