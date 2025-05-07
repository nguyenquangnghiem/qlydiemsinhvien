import { useContext, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { KeycloakContext } from "../Keycloak/keycloakProvider";


const Header = () => {
    let nav = useNavigate();
    const [kw, setKw] = useState("");
    const keycloak = useContext(KeycloakContext);
    const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];

    return (
        <>
       <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <div class="container-fluid">
                <a class="navbar-brand nav-item nav-item-1" href="#">University</a>
            </div>
        </nav>
        {keycloak !== null? 
        <nav class="navbar navbar-1 navbar-expand-sm navbar-dark nav-menu">
            <div class="container-fluid">
                <a class="navbar-brand dark-color header-logo " href="#"><i class="fa-solid fa-bell icon-padding"></i></a>
                <div class="collapse navbar-collapse" id="collapsibleNavbar">
                    <ul class="navbar-nav">
                        {roles.includes('GV')?
                        <li class="nav-item gv-Search-student">
                            <Form className="d-flex align-items-center Search-student"> 
                                <Form.Control
                                    type="text"
                                    value={kw}
                                    onChange={e => setKw(e.target.value)}
                                    placeholder="Nhập tên sinh viên"
                                    name="kw"
                                    className="mr-2" 
                                />
                                <Button type="submit" className="btn-search-student">Tìm</Button>
                            </Form>
                        </li> : <></>
                        }
                        <li class={"nav-item" + (roles.includes('GV')? " gv-user-name-img" : " user-name-img")}>
                        {keycloak === null || keycloak.image === null ? <a class="nav-link dark-color" href="#"><i class="fa-solid fa-user icon-padding" ></i></a> : <div class="info-user-image-3" ><img class="img-user-avatar-header" src={keycloak.image} alt="Ảnh đại diện" /></div>}
                        </li>
                       
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle dark-color" href="#" role="button" data-bs-toggle="dropdown">Chào,
                                {keycloak === null ? "" : keycloak.tokenParsed?.ho_ten}</a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item dark-color " href="#"><i class="fa-solid fa-user icon-padding"></i>Thông Tin Tài Khoản</a></li>
                                <li><a class="dropdown-item dark-color" href="#"><i class="fa-solid fa-key icon-padding"></i>Thay Đổi Mật Khẩu</a></li>
                                <li><a class="dropdown-item dark-color" href="#"><i class="fa-solid fa-right-to-bracket icon-padding"></i>Đăng Xuất</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
         : <></>}
        </>
    )
}
export default Header;

