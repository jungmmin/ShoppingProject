import React, {useEffect, useState} from 'react';
import SearchInput from "./Sections/SearchInput.jsx";
import CardItem from "./Sections/CardItem.jsx";
import axiosInstance from "../../utils/axios.js";

const LandingPage = () => {
    const limit = 4;
    const [searchTerm, setSearchTerm] = useState('');
    const [products, setProducts] = useState([]);
    const [skip, setSkip] = useState(0);
    const [hasMore, setHasMore] = useState(false);
    const [filters, setFilters] = useState({
        continents: [],
        price:[]
    });

    useEffect(() => {
        fetchProducts({skip, limit});
    }, [])

    /**
     * 상품 정보 가져오기
     */
    const fetchProducts = async ({ skip, limit, loadMore = false, filters = {}, searchTerm = "" }) => {
        const params = {
            skip,
            limit,
            filters,
            searchTerm
        }

        try {
            // get 요청 products
            const response = await axiosInstance.get('/products', { params })


            if (loadMore) {
                setProducts([...products, ...response.data.products])
            } else {
                setProducts(response.data.products);
            }
            // 만약 hasmore에 따라 더보기 버튼 유무
            setHasMore(response.data.hasMore);


        } catch (error) {
            console.error(error);
        }
    }

    /**
     * 더보기 버튼 클릭시 동작
     * skip의 값을 늘려서 다음 상품들 조회
     */
    const handleLoadMore = () => {
        const body = {
            skip: skip + limit,
            limit,
            loadMore: true,
            filters
        }
        fetchProducts(body);
        setSkip(skip + limit);
    }

    const handleSearchTerm = (event) => {
        const body = {
            skip: 0,
            limit,
            filters,
            searchTerm: event.target.value
        }
        setSkip(0);
        setSearchTerm(event.target.value);
        fetchProducts(body);
    }

    return (
        <section>
            <div className={'text-center m-7'}>
                <h2 className={'text-2xl'}>여행 상품 사이트</h2>
            </div>
            {/*Filter*/}
            <div>
            </div>

            {/*Search*/}
            <div className={'flex justify-end'}>
                <SearchInput searchTerm={searchTerm} onSearch={handleSearchTerm} />
            </div>
            {/*Card*/}
            <div className='grid grid-cols-2 gap-4 sm:grid-cols-4'>
                {products.map(product =>
                    <CardItem product={product} key={product._id}/>
                )}
            </div>


            {/*LoadMore*/}
            {/*hasMore이 true 일때만 적용*/}
            {hasMore &&

                <div className={'flex justify-center mt-5'}>
                    <button onClick={handleLoadMore} className={'px-4 py-2 mt-5 text-white bg-black rounded-md hover:bg-gray-500'}>
                        더 보기
                    </button>
                </div>
            }
        </section>
    );
};

export default LandingPage;