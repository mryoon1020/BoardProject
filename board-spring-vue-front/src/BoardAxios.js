import axios from 'axios'

export default {
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
  }
}