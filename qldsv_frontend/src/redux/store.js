import cookie from "react-cookies";
import { combineReducers, createStore } from "redux";
import { accountReducer, giangVienReducer, giaoVuReducer, sinhVienReducer } from "./action";


const allReducers = combineReducers({
    accountReducer,
    giangVienReducer,
    sinhVienReducer,
    giaoVuReducer
})
// hàm lưu người dùng lên localstorage
function saveUserData(state) {
    try {
        if (state.accountReducer) {
            cookie.save("user", state.accountReducer);
        }
        if (state.sinhVienReducer) {
            cookie.save("sinhvien", state.sinhVienReducer);
        }
        if (state.giangVienReducer) {
            cookie.save("giangvien", state.giangVienReducer);
        }
        if (state.giaoVuReducer) {
            cookie.save("giaovu", state.giaoVuReducer);
        }
    } catch (e) {
        console.error(e);
    }
}

// hàm tải người dùng về từ localStorage
function loadUserData() {
    try {
        const userData = cookie.load("user") ? cookie.load("user") : undefined;
        const giangvien = cookie.load("giangvien") ? cookie.load("giangvien") : undefined;
        const sinhvien = cookie.load("sinhvien") ? cookie.load("sinhvien") : undefined;
        const giaovu = cookie.load("giaovu") ? cookie.load("giaovu") : undefined;

        if (userData || giangvien || sinhvien || giaovu) {
            return { accountReducer: userData, sinhVienReducer: sinhvien, giangVienReducer: giangvien, giaoVuReducer: giaovu };
        }
        return undefined;
    } catch (e) {
        console.log(e);
        return undefined;
    }
}


const prevState = loadUserData();

const store = createStore(allReducers, prevState);
store.subscribe(() => saveUserData(store.getState()))
export default store;