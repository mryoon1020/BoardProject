<template>
    <div class="flex-center-row searchbar-wrapper">
        <div>
            <input type="date" v-model="startDate">
            ~
            <input type="date" v-model="endDate">
        </div>
        <div>
            <select v-model="boardCategoryNo">
                <option :value='0' selected>선택</option>
                <option 
                    v-for="(category, index) in categoryList"
                    :key="index"
                    :value="category.boardCategoryNo"
                >
                    {{category.boardCategoryName}}
                </option>
            </select>
        </div>
        <div class="flex-center-row searchbar-wrapper">
            <input type="text" v-model="searchKeyWord">
            <button @click="search">검색</button>
        </div>
    </div>
</template>

<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';
import { onMounted, ref } from 'vue';
    const { 
            categoryList,
            getCategoryList,
            startDate,
            endDate,
            boardCategoryNo,
            searchKeyWord
        } = useFunctionBoard('...');

    const emit = defineEmits(['searchCondition']);

    const search = () => {
        emit('searchCondition',{
            startDate: startDate.value,
            endDate: endDate.value,
            boardCategoryNo: boardCategoryNo.value,
            searchKeyWord: searchKeyWord.value
        })
    }

    onMounted(() => {
        getCategoryList()
    })
</script>