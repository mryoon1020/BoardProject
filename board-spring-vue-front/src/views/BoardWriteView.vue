<template>
	<div class="flex-center-column">
    	<h1>WriteView</h1>
		<div class="flex-center-column contents-gap-fifteen">
			<div class="flex-center-column content-flex-start contents-gap-fifteen">
				<div class="flex-center-row contents-gap-fifteen">
					<div class="bold">카테고리</div>
					<select v-model="boardCategoryNo">
						<option value=0>선택</option>
						<option :value= "category.boardCategoryNo" v-for="category in categoryList" :key="category.boardCategoryNo">
							{{ category.boardCategoryName }}
						</option>
					</select>
					<div class="bold">작성자</div>
					<input type="text" v-model="boardWriter">
				</div>
				<div class="flex-center-row contents-gap-fifteen">
					<div class="bold">비밀번호</div>
					<input type="password" placeholder="비밀번호" v-model="boardPassword">
					<div class="bold">비밀번호 확인</div>
					<input type="password" placeholder="비밀번호 확인" v-model="boardPasswordCheck">
				</div>
				<div class="flex-center-row contents-gap-fifteen">
					<div class="bold">제목</div>
					<input type="text" v-model="boardTitle">
				</div>
				<div class="flex-center-column content-flex-start contents-gap-fifteen">
					<div class="bold">내용</div>
					<textarea class="board-textarea" v-model="boardContents" />
				</div>
				<div>
					<div class="bold">파일첨부</div>
					<input type="file">
					<input type="file">
					<input type="file">
				</div>
				<div class="flex-center-row">
					<button @click="boardWrite">저장</button>
					<button type="button" onclick="history.back()">취소</button>
				</div>
			</div>
		</div>
	</div>
</template>
<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';
import { onMounted } from 'vue';

	const {
		getCategoryList,
        categoryList,
		boardWrite,
		boardCategoryNo,
        boardWriter,
        boardPassword,
        boardPasswordCheck,
        boardTitle,
        boardContents,
	} = useFunctionBoard('...');

	onMounted(() => {
		getCategoryList()
	})
</script>
<style scoped>
</style>