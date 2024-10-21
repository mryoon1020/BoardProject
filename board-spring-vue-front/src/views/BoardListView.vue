<template>
    <h1>ListView</h1>
    <div class="container">
  <div class="row">
    <form method="post" action="/list">
      <div>
        <div>등록일</div>
        <input type="date" name="startDate"> ~
        <input type="date" name="endDate">
      </div>
      <div>
        <BoardCategory />
        <!-- <select name="boardCategory" id="boardCategory">
            <c:forEach var="cateList" items="${cateList}">
                <option value="${cateList.boardCategoryNo}">${cateList.boardCategoryName}</option>
            </c:forEach>
        </select> -->
      </div>
      <div>
        <input type="text" name="keyWord">
        <button type="submit">검색</button>
      </div>
    </form>
  </div>
  <div class="row">
    <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
      <thead>
      <tr>
        <th style="background-color:#eeeeee; text-align:center;">카테고리</th>
        <th style="background-color:#eeeeee; text-align:center;">제목</th>
        <th style="background-color:#eeeeee; text-align:center;">작성자</th>
        <th style="background-color:#eeeeee; text-align:center;">조회수</th>
        <th style="background-color:#eeeeee; text-align:center;">등록일시</th>
        <th style="background-color:#eeeeee; text-align:center;">수정일시</th>
      </tr>
      </thead>
      <tbody>
            <tr 
            v-for="row in result" 
            v-bind:key="row.no"
            >
              <td>{{row.boardCategoryName}}</td>
              <td><a v-on:click="readPost(`${row.boardNo}`)">{{row.boardTitle}}</a></td>
              <td>{{row.boardWriter}}</td>
              <td>{{row.boardView}}</td>
              <td>{{row.boardWriteDate}}</td>
              <td>{{row.boardUpdateDate}}</td>
            </tr>

      </tbody>
    </table>
    <!-- 페이징처리부분 -->
    <a v-on:click=getBoardList(i) v-for="i in endPage" :key="i">&nbsp;{{i}}&nbsp;</a>
    <br>
    <!-- 글쓰기 버튼 -->
    <a href="/board/write" class="btn btn-primary pull-right">글쓰기</a>
  </div>
    <br>
  <div style="width:1000px; text-align: center; margin-top: 10px;">


      <!-- <c:forEach var="cnt" begin="1" end="${lastPage}">

          <a href="/list?currentPage=${cnt}">${cnt}</a>&nbsp;&nbsp;

      </c:forEach> -->


      <!-- <a href='/board/main?currentPage='></a> -->


  </div>
</div>
</template>
<script>
import BoardCategory from '@/components/BoardCategory.vue'

import {ref} from 'vue'
import axios from 'axios'
import BoardAxios from "@/BoardAxios";

export default {
  name: 'BoardListView',
    inject[BoardAxios],
  components: {

    BoardCategory,

  },//component end

  data(){
    return {

      endPage: ref([]),
      result: ref([])

    }//return end

  },//data() end
  mounted(){
    this.getBoardList()
        .thenI() {

      }
  },//mounted() end

  methods: {

    async getBoardList(pageNo){

      try{

        console.log("pageno:  "+pageNo)
        let viewPost = 10;
        let pageIndex = pageNo ? (pageNo-1)*viewPost : 1;
        console.log(pageIndex)

        const responsePage = await axios.get('http://localhost:8000/board/countTotalPost',{
                    params:{
                        boardCategoryNo : '',
                        startDate : '',
                        endDate : '',
                        searchKeyWord : '',
                        pageIndex : pageIndex,
                        viewPost : viewPost,
                    }
                })
        
        this.endPage = Math.ceil((responsePage.data)/viewPost);

        const response = await axios.get('http://localhost:8000/board/list',{
          params: {
            boardCategoryNo : '',
            startDate : '',
            endDate : '',
            searchKeyWord : '',
            pageIndex : pageIndex,
            viewPost : viewPost,
            }
        }
        
        );

        console.log(response)
        this.result = response.data

      } catch(error) {

          console.log(error)  

      }
    },
    readPost(boardNo){
      this.$router.push({
        name: "BoardReadView",
        params:{
          boardNo : boardNo,
        }
      })
    }
  }//method end
}
</script>