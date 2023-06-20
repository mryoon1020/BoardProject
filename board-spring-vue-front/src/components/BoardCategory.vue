<template>
    <div>
        <select name="boardCategory" id="boardCategory">
            <option 
            v-for="row in categoryList" 
            v-bind:key="row.no"
            >
            {{row.boardCategoryName}}
            </option>
        </select>
    </div>
</template>

<script>
import { defineComponent } from '@vue/composition-api'
import axios from 'axios'

export default {
    
    name: 'BoardCategory',
    boardCategory(){
        return {
            categoryList: ref([])
        }
    },
    methods: {
        async getBoardCategory(){
            try{
                const response = await axios.get('http://localhost:8000/board/categorylist')
                console.log(response)
                this.categoryList = response.data
            } catch{
                console.log("error")
            }
        }
    }
}
</script>
