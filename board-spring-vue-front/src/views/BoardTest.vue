<template>
    <h1>TestView</h1>
    
    <button @click="test">클릭</button>
    {{ count }}
    
    <button @click="testTwo">한번더</button>
    {{ countTwo }}
</template>
<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';

    const { count, test, countTwo, testTwo } = useFunctionBoard('...');

</script>
