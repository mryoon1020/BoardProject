import axios from 'axios';
import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export function useFunctionBoard () {
    //axios 기본 url설정
    axios.defaults.baseURL = 'http://localhost:8000/api';

    //api End-Point
    const apiEndPoint = {
        'category':'/board/category',
        'list':'/board/list',
        'totalPost':'/board/countTotalPost',
        'read': () => `/board/read/${boardNo.value}`,
        'write': '/board/write'
    };

    // 공통 사용
    const router = useRouter();
    const route = useRoute();
    
    const routePushTo = (routerName) => {
        router.push({ name: routerName });
    }

    const getData = async (endPoint, searchCondition) => {
        try {
            const res = await axios.get(endPoint,{
                params: searchCondition
            });
            return res.data;
        } catch (error) {
            console.log(error);
        }
    }

    const postData = async (endPoint, inputData) => {
        try {
            const res = await axios.post(endPoint, inputData);
            return res.data;
        } catch (error) {
            console.log(error)
            return false;
        }
        
    }

    const updateSearchQuery = () => {
        router.push({ query: 
            searchCondition.value
        })
    }

    const updatePageQuery = async() => {
        await router.push({ query: {
            ...route.query,
            currentPage : currentPage.value,
            viewPost: viewPost.value,
            startPost: startPost.value,
        }})
    }

    const startDate = ref('');
    const endDate = ref('');
    const selectedCategoryNo = ref(0);
    const searchKeyWord = ref('');
    const viewPost = ref(10);

    const searchCondition = computed(() => {
        const condition = {};

        condition.viewPost = viewPost.value;
        startDate.value ? condition.startDate = startDate.value : null;
        endDate.value ? condition.endDate = endDate.value : null;
        selectedCategoryNo.value !== 0 ? condition.boardCategoryNo = selectedCategoryNo.value : null;
        searchKeyWord.value ? condition.searchKeyWord = searchKeyWord.value : null;
        // currentPage.value !== 1 ? condition.currentPage = currentPage.value :null;
        // startPage.value !== 1 ? condition.startPage = startPage.value : null;
        // endPage.value !== 1 ? condition.endPage = endPage.value : null;
        currentPage.value !== 1 ? condition.startPost = (currentPage.value - 1) * viewPost.value : null;
        return condition;
    });

    // composable test 코드 1. BoardTest.vue 사용
    const count = ref(0);
    const test = () => {
        count.value++;
    }

    // composable test 코드 2. BoardTest.vue 사용
    const countTwo = ref(0);
    const testTwo = () => {
        countTwo.value++;
    }
    
    // BoardListView.vue
    const boardList = ref(null);
    const getBoardList = async () => {
        try {
            boardList.value = await getData(apiEndPoint.list, searchCondition.value);
            return true;
        } catch {
            return false;
        }
    }

    // BoardSearchbar.vue
    const categoryList = ref(null);
    const getCategoryList = async () => {
        categoryList.value = await getData(apiEndPoint.category);
    }

    // BoardPagenation.vue
    const totalPage = ref(0);
    const startPage = ref(1);
    const endPage = ref(0);
    const viewPageNo = ref([])
    const currentPage = ref(1);
    const startPost = ref(0);
    const headed = ref(0);
    const getTotalPage = async () => {
        const totalPost = await getData(apiEndPoint.totalPost);
        totalPage.value = Math.max(Math.ceil(totalPost / viewPost.value));
    }

    const getStartPage = () => {
        
        // startPage.value = 1;
        
        // if(currentPage.value % 5 !== 0 || currentPage.value == totalPage.value) {
        //     return;
        // } else {
        //     startPage.value = currentPage.value-1;
        // }

        // if(currentPage.value % 5 == 0){
        //     startPage.value = currentPage.value - 1;
        // } else if (currentPage.value == totalPage.value) {
        //     for(let i = currentPage.value; i > 0; i--){

        //         if(i % 5 == 0) {
        //             startPage.value = i - 1;
        //             break;
        //         }
        //     }
        // }

        // 현재 페이지 보다 작은 5의 배수를 찾아 startPage를 계산합니다.
        // if(currentPage.value%5 === 0){
        //     startPage.value = currentPage.value - 1;
        // } else {
            startPage.value = Math.max(1, Math.floor(currentPage.value/5) * 5 - 1);
        // }
    }

    const getEndPage = () => {
        
        // endPage.value = 0;
        
        // if(endPage.value > totalPage.value || startPage.value + 5 > totalPage.value) {
        if(startPage.value + 5 > totalPage.value) {
            endPage.value = totalPage.value;
        } else {
            endPage.value = startPage.value + 5;
        }
    }
    /**
     * 컨셉
     * 1 2 3 4 5
     * 4 5 6 7 8 9 10
     * 9 10 11 12 13 14 15
     * 필요 항목: 
     * 1. 페이지 배열을 생성 해줘야함
     * 2. 현재 페이지가 1 ~ 4 or 5 ~9 or 10 ~14 확인하는 로직 필요
     * 3. 현재 페이지 변경시 페이지 배열 필요.
     * 
     * 결론.
     * 
     * 함수는 총 3개 필요(배열생성 함수, 현재 페이지 확인 함수, 현재 페이지 확인함수)
     */

    /**
     * 페이지 배열생성 함수
     * viewPages에 원소를 추가합니다.
     * @param pages 페이지 갯수
     * */
    const setViewPageNo = (pages) => {
        // 기존의 배열에 원소가 있을 수 있으므로 배열을 초기화 합니다.

        // viewPageNo.value.length = 0; 안전하지 않음
        const newViewPageNo = [];
        for(let i = 0; i < pages; i++) {
            

            const pageNo = startPage.value + i;
            
            // newPageNo.value.push(pageNo)

            // if(viewPageNo.value[i] > totalPage.value){
            if(pageNo > totalPage.value){
                // viewPageNo.value.splice(i)
                break;
            }

            newViewPageNo.push(pageNo)
        }

        viewPageNo.value = newViewPageNo;
    }

    /**
     * 현재 페이지번호 확인 함수
     * 현재 페이지 번호에 대한 정보는 route.query.currentPage에서 확인합니다.
     * 결과는 currentPage 에 다시 저장합니다.
     */
    const getCurrentPageNo = () => {
        currentPage.value = route.query.currentPage || 1;
    }
    
    /**
     * 현재 페이지번호를 바탕으로 viewPageNo의 변경여부를 판단합니다.
     * @return boolean 변경: true, 변경안함: false
     */
    const isChangeViewPageNo = () => {

        // 페이지 배열이 비어있는 경우
        if(viewPageNo.value.length == 0) {
            return true;
        }

        // 잘못된 페이지 번호를 처리
        if(currentPage.value > totalPage.value){

            return true;
        }

        // 현재 페이지가 5의 배수일경우
        if(currentPage.value%5 == 0) {

            return true;
        
        } 
        
        // 현재 페이지가 4 또는 9로 끝나는 경우
        if(currentPage.value%5 == 4) {
            // 현재 페이지 번호가 4 또는 9 로 끝날때,
            // 페이지 배열에서 첫번째 순서일경우 배열을 변경합니다.
            // 그밖의 경우에는 배열을 변경하지 않습니다.
            if(currentPage.value == viewPageNo.value[0]) {

                return true;
            }

            return false;

        }
        
        // 현재 페이지가 첫 페이지거나 마지막 페이지인 경우
        if(currentPage.value == 1 || currentPage.value == totalPage.value) {

            return true;
        }

        return false;

    }

    /**
     * 표시할 페이지 번호 배열에 대하여 컨트롤합니다.
     * @todo 있음, pages가 원인모를 오류로  제대로 반영안됨
     * getTotalPage는 어디서 처리해야할까?????
     * 문제상황, handleViewPageNo()안에서 실행 => viewPost 변화를 다시 초기화값으로 덮어씀
     * 문제상황2, pagenation.vue onMounted() 에서 실행, props로 제대로 전달 받았지만, handleViewPageNo()안에서
     * 반영할 수 없음
     */
    const handleViewPageNo = async () => {
        getCurrentPageNo();

        if(isChangeViewPageNo()) {
            await getTotalPage();
            getStartPage();
            getEndPage();

            // 페이지 번호가 1~5 일경우 5개만 표시
            // 페이지 번호가 4또는 9로 시작할 때 7개 씩 표시
            const pages = startPage.value == 1 ? 5 : 7;
            setViewPageNo(pages);
        }
    }

    //old code
    // const setViewPages = (totalPages) => {

    //     getStartPage();
    //     console.log("currentPage", currentPage.value)
    //     if(totalPages <= 5){

    //         for(let i = 0; i < totalPages; i++) {
    //             viewPages.value[i] = i + 1
    //         }

    //         return;

    //     } else {
    //         const arrayLength = currentPage.value != 1 ? 7 : 5
            
    //         for(let i = 0; i < arrayLength; i++){
                
    //             viewPages.value[i] = startPage.value + i + 1;

    //             if(viewPages.value[i] > totalPage.value) {
    //                 viewPages.value.splice(i);
    //                 break;
    //             }
    //         }
    //     }
    // }


    /**
     * 페이지 이동을 처리합니다.
     * prev, next, 각 페이지버튼 클릭시 호출됩니다.
     * @param : prev, next, 페이지번호
    */
    const moveAction = async (action) => {
        switch(action){
            case 'prev':
                headed.value = currentPage.value <= 1?  alert('첫 번째 페이지입니다.') : currentPage.value-1;
                break;
            case 'next':
                headed.value = currentPage.value >= totalPage.value? alert('마지막 페이지입니다.') : currentPage.value+1;
                break;
            default:
                headed.value = Number(action);
                break;
        }

        currentPage.value = headed.value;

        await updatePageQuery();
    }

    // BoardRead
    const post = ref({});
    const boardNo = ref(0);
    const getPost = async () => {
        boardNo.value = route.params.boardNo;
        post.value = await getData(apiEndPoint.read());
    }

    // BoardWrite
    const boardCategoryNo = ref(0);
    const boardWriter = ref('');
    const boardPassword = ref('');
    const boardPasswordCheck = ref('');
    const boardTitle = ref('');
    const boardContents = ref('');

    // 서버로 넘어갈 데이터 집합입니다.
    const inputData = computed(() => {

        return {
            'boardCategoryNo': boardCategoryNo.value,
            'boardWriter': boardWriter.value,
            'boardPassword': boardPassword.value,
            'boardTitle' : boardTitle.value,
            'boardContent' : boardContents.value
        }
    })

    /**
     * 게시글 작성함수 입니다.
     * 비밀번호 일치 여부를 확인한 후 api를 호출합니다.
     * 서버쪽에서 추가로 유효성 검사를 시행하며, 결과에 따라 메시지를 반환합니다.
     */
    const boardWrite = async () => {
        console.log(inputData.value)
        if(!boardPassword.value){
            alert("비밀번호를 입력해주세요");
            return;

        } else if(boardPassword.value != boardPasswordCheck.value){
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        const result = await postData(apiEndPoint.write, inputData.value);

        if(!result){
            return alert("내용을 확인해주세요.")
        }

        return alert("글 등록에 성공하였습니다.")
    }

    onMounted(() => {
        if(route.query.viewPost) {
            viewPost.value = route.query.viewPost;
        }

        if(route.query.startDate) {
            startDate.value = route.query.startDate;
        }

        if(route.query.endDate) {
            endDate.value = route.query.endDate;
        }

        if(route.query.searchKeyWord) {
            searchKeyWord.value = route.query.searchKeyWord;
        }

        if(route.query.currentPage) {
            currentPage.value = route.query.currentPage;
        }

        if(route.query.startPage) {
            startPage.value = route.query.startPage;
        }

        if(route.query.endPage) {
            endPage.value = route.query.endPage;
        }
    })
    
    return {
        count,
        test,
        countTwo,
        testTwo,
        routePushTo,
        getBoardList,
        boardList,
        startDate,
        endDate,
        selectedCategoryNo,
        searchKeyWord,
        searchCondition,
        getCategoryList,
        categoryList,
        getTotalPage,
        totalPage,
        currentPage,
        moveAction,
        viewPost,
        updateSearchQuery,
        getStartPage,
        getEndPage,
        startPage,
        endPage,
        handleViewPageNo,
        viewPageNo,
        updatePageQuery,
        startPost,
        getPost,
        post,
        boardWrite,
        boardCategoryNo,
        boardWriter,
        boardPassword,
        boardPasswordCheck,
        boardTitle,
        boardContents,

    }
}