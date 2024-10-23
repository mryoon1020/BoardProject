import axios from 'axios';
import { computed, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

export function useFunctionBoard () {
    //axios 기본 url설정
    axios.defaults.baseURL = 'http://localhost:8000/api';

    //api End-Point
    const apiEndPoint = {
        'category':'/board/category',
        'list':'/board/list',
        'totalPost':'/board/countTotalPost',
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

    const updateQuery = () => {
        router.push({ query: {
            ...route.query,
            ...searchCondition.value,
            currentPage : currentPage.value,
            viewPost : viewPost.value
        }})
    }

    const startDate = ref('');
    const endDate = ref('');
    const selectedCategoryNo = ref(0);
    const searchKeyWord = ref('');
    const viewPost = ref(10);

    const searchCondition = computed(() => {
        const condition = {};
        viewPost.value !== 10? condition.viewPost = viewPost.value : 10;
        startDate.value ? condition.startDate = startDate.value : null;
        endDate.value ? condition.endDate = endDate.value : null;
        selectedCategoryNo.value !== 0 ? condition.boardCategoryNo = selectedCategoryNo.value : null;
        searchKeyWord.value ? condition.searchKeyWord = searchKeyWord.value : null;
        // currentPage.value !== 1 ? condition.currentPage = currentPage.value :null;

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
        updateQuery();
        getTotalPage();
        boardList.value = await getData(apiEndPoint.list, searchCondition.value);
    }

    // BoardSearchbar.vue
    const categoryList = ref(null);
    const getCategoryList = async () => {
        categoryList.value = await getData(apiEndPoint.category);
    }

    // BoardPagenation.vue
    const totalPage = ref(0);
    const startPage = ref(0);
    const endPage = ref(0);
    const currentPage = ref(1);
    const headed = ref(0);
    const getTotalPage = async () => {
        const totalPost = await getData(apiEndPoint.totalPost);
        totalPage.value = Math.ceil(totalPost / viewPost.value);
    }
    const getStartPage = (pageNo) => {
        if(pageNo % 5 === 1) {
            startPage.value = pageNo-1;
        } 
    }

    const getEndPage = () => {
        endPage.value = startPage + 5;
    }

    const moveAction = (action) => {
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
        getBoardList()
    }

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
        viewPost

    }
}