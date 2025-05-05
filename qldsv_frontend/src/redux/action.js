import cookie from "react-cookies";

export const accountReducer = (currentState = null, action) => {
    switch (action.type) {
        case "login":
            return action.payLoad;
        case "logout":
            {
                cookie.remove('token');
                cookie.remove('refresh_token');
                cookie.remove('user');
                return null;
            }
        default:
            return currentState;
    }
}

export const giangVienReducer = (currentState = null, action) => {
    switch (action.type) {
        case "login_gv":         
            return action.payLoad;
        case "logout_gv":         
        {
            cookie.remove('giangvien');
            return null;
        }
        default: 
            return currentState;
    }
}

export const sinhVienReducer = (currentState = null, action) => {
    switch (action.type) {
        case "login_sv":
            return action.payLoad;
        case "logout_sv":
            {
                cookie.remove('sinhvien');
                return null;
            }
        default:
            return currentState;
    }
}

export const giaoVuReducer = (currentState = null, action) => {
    switch (action.type) {
        case "login_gvu":
            return action.payLoad;
        case "logout_gvu":
            {
                cookie.remove('giaovu');
                return null;
            }
        default:
            return currentState;
    }
}