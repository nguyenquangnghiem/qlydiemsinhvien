import { useContext } from "react";
import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { keycloakService } from "../Keycloak";
import { KeycloakContext } from "../Keycloak/keycloakProvider";

const SideBar = () => {
  const dispatch = useDispatch();
  const keycloak = useContext(KeycloakContext);
      const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];
  async function logout() {
    try{
      keycloakService.logout();
    } catch(e){
      console.error(e);
    }
  }

  return (
    <div class={(roles.includes('GV')? "gv-" : "") + "vertical-menu"}>
      <div class={"active" + (roles.includes('GV')? "gv-" : "") +  + "logo-menu"}>
        <Link to="/">
          <i class="fa-solid fa-book-open icon-padding "></i>Trường Đại Học Mở
        </Link>
      </div>
      <div class="menu-items">
        <p class="text-menu ">Dịch Vụ, Tiện Ích</p>
        <Link to="/home" class="menu-item">
          <i class="fa-solid fa-house icon-padding"></i>Tổng Quan
        </Link>
        {/* <Link class="menu-item" to="/diendan">
          <i class="fa-solid fa-message icon-padding"></i>Diễn Đàn
        </Link> */}
        {roles.includes('GV')? 
        <>
            <Link class="menu-item" to="/danhsachmonchuanbi">
            <i class="fa-solid fa-gift gv-icon-padding"></i>Môn Sắp Giảng Dạy
            </Link>
            <Link class="menu-item" to="/danhsachmonhocdakhoa">
                <i class="fa-solid fa-gift gv-icon-padding"></i>Môn Đã Qua
            </Link>
        </> : <>
            <Link class="menu-item" to="/xemdiem">
            <i class="fa-solid fa-gift icon-padding"></i>Xem Điểm
            </Link>
            <Link class="menu-item" to="/danhsachmon">
            <i class="fa-solid fa-check icon-padding"></i>Danh Sách Môn
            </Link>
            <Link class="menu-item" to="/dangkymonhoc">
            <i class="fa-solid fa-graduation-cap icon-padding"></i>Dăng Ký Môn Học
            </Link>
        </>}
      </div>
      <div class="menu-items">
        <p class="text-menu">Thông Tin tài Khoản</p>
        <Link class="menu-item" to="/thongtin">
          <i class="fa-solid fa-user icon-padding"></i>Thông Tin Tài Khoản
        </Link>
        <Link class="menu-item" to="/thaydoimatkhau">
          <i class="fa-solid fa-key icon-padding"></i>Thay Đổi Mật Khẩu
        </Link>
        <Link class="menu-item" onClick={logout} to="/auth-option">
          <i class="fa-solid fa-right-to-bracket icon-padding"></i>Thoát Quyền
          Sử Dụng
        </Link>
      </div>
    </div>
  );
};

export default SideBar;
