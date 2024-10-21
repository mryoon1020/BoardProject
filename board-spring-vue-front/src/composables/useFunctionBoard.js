import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export function useFunctionBoard () {
    //axios 기본 url설정
    axios.defaults.baseURL = 'http://localhost:8000/api';

    //api End-Point
    const apiEndPoint = {
        'category':'/board/category',
        'list':'/board/list',
    };

    // 공통 사용
    const router = useRouter();
    
    const routePushTo = (routerName) => {
        router.push({ name: routerName });
    }

    const getList = async (endPoint, searchCondition) => {
        try {
            const res = await axios.get(endPoint,{
                params: searchCondition
            });
            return res.data;
        } catch (error) {
            console.log(error);
        }
    }

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
    const getBoardList = async (searchCondition) => {
        boardList.value = await getList(apiEndPoint.list, searchCondition);
    }

    // BoardSearchbar.vue
    const startDate = ref('');
    const endDate = ref('');
    const boardCategoryNo = ref(0);
    const searchKeyWord = ref('');
    const categoryList = ref(null);
    const getCategoryList = async () => {
        categoryList.value = await getList(apiEndPoint.category);
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
        boardCategoryNo,
        searchKeyWord,
        getCategoryList,
        categoryList,

    }
}