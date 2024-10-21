<template>
  <div class="flex-center-column">
    <h1>Home</h1>
    <div class="button-wrapper">
      <button @click="routePushTo('BoardListView')">리스트이동</button>
      <button @click="routePushTo('Boardtest')">테스트페이지 이동</button>
    </div>
  </div>
</template>

<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';
  const { routePushTo } = useFunctionBoard('...');
</script>
