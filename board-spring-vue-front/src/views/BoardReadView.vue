<template>
	<div class="flex-center-column">
    	<h1>ReadView</h1>
    	<div class="flex-center-column content-flex-start contents-gap-fifteen">
			<div class="flex-center-row contents-gap-fifteen">
				<div class="bold">작성자</div>
				<div>{{ post.boardWriter }}</div>
			</div>

			<div class="flex-center-row contents-gap-fifteen container-flex-between">
				<div class="bold">등록일시</div>
				<div>{{ post.boardWriteDate }}</div>
				<div v-if="post.boardUpdateDate">|</div>
				<div class="bold" v-if="post.boardUpdateDate">수정일시</div>
				<div>{{ post.boardUpdateDate }}</div>
			</div>
			<div class="flex-center-row contents-gap-fifteen">
				<div class="bold">카테고리</div>
				<div>{{ post.boardCategoryName }}</div>
				<div class="bold">조회수</div>
				<div>{{ post.view }}</div>
			</div>
			<div class="flex-center-row contents-gap-fifteen">
				<div class="bold">제목</div>
				<div>{{ post.boardTitle }}</div>
			</div>

			<div class="flex-center-column content-flex-start contents-gap-fifteen">
				<div class="bold">내용</div>
				<div class="board-contents">{{ post.boardContent }}</div>
				<div>첨부파일 이미지 + 첨부파일</div>
			</div>

			<div>

			<h4>댓글</h4>

				<!-- <c:forEach var="replyList" items="${replyList}">
					<p>
						${replyList.boardReplyDate}<br>
						${replyList.boardReplyContent}
					</p>
					<hr>
				</c:forEach> -->

				<h4>댓글쓰기</h4>
				<form action="/replyWrite" method="post" id="replyForm">
					<div>
						<!-- <input type="hidden" name="boardNo" value="${boardVO.boardNo}"> -->
						<input type="text" name="boardReplyContent" id="boardReplyContent" placeholder="댓글을 입력해주세요">
						<button onclick="writeReply()">등록</button>
					</div>
				</form>
			</div>
			<div>
				<button @click="routePushTo('BoardListView')">목록</button>
				<button @click="routePushTo('BoardUpdateView')">수정</button>
				<button type="button" id="modal_open_btn" onclick="modalOpen()">삭제</button>
			</div>
		</div>
		<div id="modal">
			<div class="modal_content">
				<form method="post" action="/delete" id="deleteForm">
				<h2>비밀번호 확인</h2>
				<div>비밀번호</div>
				<!-- <div><input type="hidden" name="boardNo"value="${boardVO.boardNo}" /></div> -->
				<div><input type="password" placeholder="비밀번호" name="boardPassword" id="deletePassword"></div>
				<div>
				<button onclick="exDelete()">삭제</button>
				<button type="button" id="modal_close_btn" onclick="modalClose()">취소</button>
				</div>
				</form>
			</div>

			<div class="modal_layer"></div>
		</div>
	</div>
</template>
<style scoped>
		#modal {
			display: none;
			position:relative;
			width:100%;
			height:100%;
			z-index:1;
		}

		#modal h2 {
			margin:0;
		}

		#modal button {
			display:inline-block;
			width:100px;
			margin-left:calc(100% - 100px - 10px);
		}

		#modal .modal_content {
			width:300px;
			margin:100px auto;
			padding:20px 10px;
			background:#fff;
			border:2px solid #666;
		}

		#modal .modal_layer {
			position:fixed;
			top:0;
			left:0;
			width:100%;
			height:100%;
			background:rgba(0, 0, 0, 0.5);
			z-index:-1;
		}
	</style>
<script setup>
import { useFunctionBoard } from '@/composables/useFunctionBoard';
import { onMounted } from 'vue';

const { getPost, post, routePushTo } = useFunctionBoard('...');

onMounted(() => {
	getPost();
})
</script>
	
// <script>
// import axios from 'axios'
// import {ref} from 'vue'

// export default {
//   name: 'BoardReadView',
//   data(){
//     return {
//       result: ref([])
//     }
//   },
//   mounted(){
//     this.getBoardPost()
//   },
//   methods: {

//     async getBoardPost(){

// 		try{
// 			console.log(this.$route.params.boardNo)
// 			const response = await axios.get('http://localhost:8000/board/read',{
// 				params: {
// 					boardNo : this.$route.params.boardNo
// 				}
				
// 				})
// 			console.log(response)
// 			this.result = response.data
// 		}catch(error){
// 			console.log(error)
// 		}
//     },

// 	updatePost (boardNo){
// 		this.$router.push({
// 			name: "BoardUpdateView",
// 			params:{
// 				boardNo : boardNo
// 			}
// 		})
// 	}

//   }
// }
// </script>