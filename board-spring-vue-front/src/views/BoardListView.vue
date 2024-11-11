<template>
  <div class="flex-center-column">
    <h1>ListView</h1>
    <div class="flex-center-column contents-wrapper">
      <!-- 검색창 -->
      <BoardSearchBar @newViewPost = "handleNewViewPost" @newBoardList = "handleNewBoardList" />
      <!-- 게시글 목록 -->
      <div>
        <table>
          <thead>
          <tr>
            <th>글번호</th>
            <th>카테고리</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>등록일시</th>
            <th>수정일시</th>
          </tr>
          </thead>
          <tbody>
                <tr 
                  v-for="(data, index) in boardList"
                  :key="index"
                >
                  <td>{{data.boardNo}}</td>
                  <td>{{data.boardCategoryName}}</td>
                  <td><router-link :to="{ name: 'BoardReadView', params: { boardNo: data.boardNo } }">{{data.boardTitle}}</router-link></td>
                  <td>{{data.boardWriter}}</td>
                  <td>{{data.boardView}}</td>
                  <td>{{data.boardWriteDate}}</td>
                  <td>{{data.boardUpdateDate}}</td>
                </tr>

          </tbody>
        </table>

        <!-- 페이징처리부분 -->
        
        <BoardPagenation :viewPost = "Number(viewPost)" @newPageBoardList = "handleNewPageBoardList" />

        <!-- 글쓰기 버튼 -->
        <a href="/board/write" class="btn btn-primary pull-right">글쓰기</a>
      </div>
    
      <div style="width:1000px; text-align: center; margin-top: 10px;">


        <!-- <c:forEach var="cnt" begin="1" end="${lastPage}">

            <a href="/list?currentPage=${cnt}">${cnt}</a>&nbsp;&nbsp;

        </c:forEach> -->


        <!-- <a href='/board/main?currentPage='></a> -->
      </div>

    </div>
  </div>
</template>
<script setup>
import { onMounted, ref } from 'vue';
import { useFunctionBoard } from '@/composables/useFunctionBoard';
import BoardSearchBar from '@/components/BoardSearchBar.vue';
import BoardPagenation from '@/components/BoardPagenation.vue';

  const { getBoardList, 
          boardList,
          viewPost
        } = useFunctionBoard('...');
  
  const handleNewViewPost = (newViewPost) => {
    viewPost.value = newViewPost;
  }

  const handleNewBoardList = (newBoardList) => {
    boardList.value = newBoardList;
  }

  const handleNewPageBoardList = (newBoardList) => {
    boardList.value = newBoardList;
  }

  onMounted(() => {
    getBoardList()
  })

</script> 