<template>
    <div class="flex-center-row searchbar-wrapper">
        <div>
            <select v-model="viewPost">
                <option :value='10'>10개씩</option>
                <option :value='20'>20개씩</option>
            </select>
        </div>
        <div>
            <input type="date" v-model="startDate">
            ~
            <input type="date" v-model="endDate">
        </div>
        <div>
            <select v-model="selectedCategoryNo">
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
    const emits = defineEmits(['newTotalPage'])
    const { 
            categoryList,
            getCategoryList,
            startDate,
            endDate,
            selectedCategoryNo,
            searchKeyWord,
            getBoardList,
            viewPost,
            totalPage,
            getTotalPage
        } = useFunctionBoard('...');

    const search = async () => {
        await getTotalPage()
        await getBoardList()
        emits('newTotalPage', totalPage.value)
    }

    onMounted(() => {
        getCategoryList()
    })
</script>