import axiosInstance from "../utils/axios.js";
import {createAsyncThunk} from "@reduxjs/toolkit";

export const registerUser = createAsyncThunk(
    "user/registerUser",
    async (body, thunkAPI) => {
        try {
            const response = await axiosInstance.post(
                `/users/register`,
                body
            )

            return response.data;
        } catch (error) {
            console.log(error);
            return thunkAPI.rejectWithValue(error.response.data || error.message);
        }
    }
)
export const loginUser = createAsyncThunk(
    "user/loginUser",
    async (body, thunkAPI) => {
        try {
            const response = await axiosInstance.post(
                `/users/login`,
                body
            )

            return response.data;
        } catch (error) {
            console.log(error);
            return thunkAPI.rejectWithValue(error.response.data || error.message);
        }
    }
)

export const authUser = createAsyncThunk(
    "user/authUser",
    async (_, thunkAPI) => {
        try {
            const response = await axiosInstance.get(
                `/auth`
            )

            return response.data;
        } catch (error) {
            console.log(error);
            return thunkAPI.rejectWithValue(error.response.data || error.message);
        }
    }
)

export const logoutUser = createAsyncThunk(
    "user/logoutUser",
    async (_, thunkAPI) => {
        try {
            const response = await axiosInstance.get(
                `/users/logout`
            )
            return response.data;
        } catch (error) {
            console.log(error);
            return thunkAPI.rejectWithValue(error.response.data || error.message);
        }
    }
)