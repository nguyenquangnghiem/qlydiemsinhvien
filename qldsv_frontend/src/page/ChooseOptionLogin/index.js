import { PiStudentFill } from "react-icons/pi";
import { RiAdminLine } from "react-icons/ri";
import './ChooseOptionLogin.css';
import { useNavigate } from "react-router-dom";

const ChooseOptionLogin = () => {

    const navigate = useNavigate();

    return(
        <>
            <div className="container--option">
                <div className="box--option" onClick={() => navigate('/login-user')}>
                    <PiStudentFill size={48} />
                    <p>Đăng nhập cho giảng viên/sinh viên</p>
                </div>
                <div className="box--option" onClick={() => navigate('/login-admin')}>
                    <RiAdminLine size={48} />
                    <p>Đăng nhập cho quản trị viên</p>
                </div>
            </div>
        </>
    );
}

export default ChooseOptionLogin;