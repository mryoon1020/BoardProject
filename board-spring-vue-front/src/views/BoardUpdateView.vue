<template>
    <h1>updateView</h1>
    <form id="updateForm" v-on:submit="submitForm">
<div>
	<div>
		<input type="hidden" name="boardNo" value="${boardVO.boardNo}">
		<div>카테고리</div>
		<div>{{result.boardCategoryName}}</div>
	</div>
	<div>
		<div>등록일시</div>
		<div>{{result.boardWriteDate}}</div>
	</div>
	<div>
		<div>수정일시</div>
		<div>{{result.boardUpdateDate}}</div>
	</div>
	<div>
		<div>작성자</div>
		<div>{{result.boardWriter}}</div>
	</div>
	<div>
		<div>비밀번호</div>
		<div><input type="password" placeholder="비밀번호" name="boardPassword" v-model="result.boardPassword"></div>
	</div>
	<div>
		<div>제목</div>
		<div><input type="text" name="boardTitle" v-model="result.boardTitle"></div>
	</div>
	<div>
		<div>내용</div>
		<div><input type="textarea" rows="5" cols="13" name="boardContent" v-model="result.boardContent" /></div>
	</div>
	<div>
		<div>파일첨부</div>

		<div><input type="file"></div>
	</div>
</div>
	<button type="button" v-on:click="location.href='/borad/list'">취소</button>
	<button type="submit" >저장</button>
</form>
</template>
<script>
import axios from 'axios'
import {ref} from 'vue'

export default {
	name: 'BoardUpdateView',
	components: {
	},

	data(){
		return {
			result: ref({})
		}
	},

	mounted (){
		this.getBoardPost()
	},
	methods:{
		async getBoardPost(){

		try{
			console.log(this.$route.params.boardNo)
			const response = await axios.get('http://localhost:8000/board/read',{
				params: {
					boardNo : this.$route.params.boardNo
					}
				
				})
				console.log(response)
				this.result = response.data

			}catch(error){
				console.log(error)
			}
    	},

		submitForm: async function boardUpdate(){

			try{
				const response = await axios.post('http://localhost:8000/board/update',this.result)
				response.data? alert("수정성공") : alert("수정실패");
			
			} catch(error){
				console.log(error)
			}
		}

	}
}
</script>