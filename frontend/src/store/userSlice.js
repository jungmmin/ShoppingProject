import {createSlice} from "@reduxjs/toolkit";
import {authUser, loginUser, logoutUser, registerUser} from "./thunkFunctions.js";
import {toast} from "react-toastify";

const initialState = {
    userData:{
        id: '',
        email: '',
        name: '',
        role: 0,
        image: '',
    },
    isAuth: false,
    isLoading: false,
    error: ''

}

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {},
    extraReducers: (builder) => {
        builder
            // 회원가입
            .addCase(registerUser.pending, (state) => {
                state.isLoading = true;
            })
            //회원가입 성공
            .addCase(registerUser.fulfilled, (state) => {
                state.isLoading = false;
                toast.info('회원가입을 성공했습니다.');
            })
            // 회원가입 실패
            .addCase(registerUser.rejected, (state, action) => {
                state.isLoading = false;
                state.error = action.payload;
                toast.error(action.payload);
            })
            // 로그인
            .addCase(loginUser.pending, (state) => {
                state.isLoading = true;
            })
            // 로그인 성공
            .addCase(loginUser.fulfilled, (state, action) => {
                state.isLoading = false;
                state.userData = action.payload;
                state.isAuth = true;
                localStorage.setItem('accessToken', action.payload.accessToken);
            })
            // 로그인 실패
            .addCase(loginUser.rejected, (state, action) => {
                state.isLoading = false;
                state.error = action.payload;
                toast.error(action.payload);
            })

            // 토큰 검증
            .addCase(authUser.pending, (state) => {
                state.isLoading = true;
            })
            // 토큰 검증 성공
            .addCase(authUser.fulfilled, (state, action) => {
                state.isLoading = false;
                state.userData = action.payload;
                state.isAuth = true;
            })
            // 토큰 검증 실패
            .addCase(authUser.rejected, (state, action) => {
                state.isLoading = false;
                state.error = action.payload;
                state.isAuth = false
                localStorage.removeItem('accessToken');
            })

            // 로그아웃
            .addCase(logoutUser.pending, (state) => {
                state.isLoading = true;
            })
            // 로그아웃 성공
            .addCase(logoutUser.fulfilled, (state, action) => {
                state.isLoading = false;
                state.userData = initialState.userData;
                state.isAuth = false;
                localStorage.removeItem('accessToken')
            })
            // 로그아웃 실패
            .addCase(logoutUser.rejected, (state, action) => {
                state.isLoading = false;
                state.error = action.payload;
                toast.error(action.payload);
            })

    }
})
export default userSlice.reducer;