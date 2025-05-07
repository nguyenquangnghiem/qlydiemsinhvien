import { useContext, useEffect, useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import cookie from "react-cookies";
import { useDispatch } from "react-redux";
import { endpoints, useApi } from "../../api";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";
import MySpinner from "../../component/MySpinner";


const Thongtintaikhoan = () => {

    const api = useApi();
    const dispatch = useDispatch();
    const keycloak = useContext(KeycloakContext);
    console.log(keycloak);
    const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];
    const [sinhvien, setSinhvien] = useState(null);
    const [loading, setLoading] = useState(false);
    const [pass, setPass] = useState(true);
    const [success, setSuccess] = useState(false);
    const avatar = useRef();


    const [taiKhoan, setTaiKhoan] = useState({
        "idTaiKhoan": keycloak?.tokenParsed?.jti || null,
        "tenTaiKhoan": keycloak?.tokenParsed?.preferred_username || null,
        "matKhau": null,
        "chucVu": roles[0],
    });

    useEffect(() => {
        async function getInformationSinhVien(){
            try{
                let url;
                if(roles.includes('SV')){
                    url = endpoints['current-sinhvien']
                } else {
                    url = endpoints['current-giangvien'];
                }
                const response = await api.get(url);
                setSinhvien(response.data);
            } catch(e){
                console.error(e);
            }

        } 
        getInformationSinhVien();
    }, [])

    const updateImage = (evt) => {
        evt.preventDefault();
        setPass(true);
        setSuccess(false);

        const process = async () => {
            let form = new FormData();
            if (avatar.current.files[0].type.startsWith('image/')) {
                for (let field in taiKhoan)

                    form.append(field, taiKhoan[field]);

                form.append("avatar", avatar.current.files[0]);

                setLoading(true)
                let res = await api.post(endpoints['udateImage'], form);

                let { data } = await api.get(endpoints['current-user']);
                cookie.remove("user");
                cookie.save("user", data);
                dispatch({
                    "type": "login",
                    "payLoad": data
                })

            } else {
                // Tệp không phải là tệp ảnh
                alert("Đây không phải là tệp ảnh.");
            }
        }
        if (avatar.current.files.length > 0) {
            process();
            setLoading(false);
            setSuccess(true);
        }
        else {
            setPass(false);
        }
    }

    return (

        <div class="contend">
            <div class="info-user">
                <div class="info-title-user">
                    {keycloak === null || keycloak?.id?.image === null ? <p class="info-user-image"><i class="fa-solid fa-user icon-padding"></i></p> : <div class="info-user-image-2" ><img class="img-user-avatar" src={keycloak?.idTaiKhoan?.image} alt="Ảnh đại diện" /></div>}

                    <Form onSubmit={updateImage}>
                        <Form.Group className="mb-3">
                            <Form.Label>Ảnh đại diện</Form.Label>
                            <Form.Control type="file" ref={avatar} />
                        </Form.Group>
                        <div class="form-group">

                            {loading === true ? <MySpinner /> : <Button class="btn btn-danger mt-2" type="submit">Thêm Ảnh </Button>}
                        </div>
                        {pass === false ? <Alert variant="secondary">Vui lòng chọn ảnh</Alert> : <div></div>}
                        {success === true ? <Alert variant="secondary">Cập nhập ảnh thành công !!!</Alert> : <div></div>}
                    </Form>
                    <p class="info-private">Thông tin Cá Nhân</p>
                </div>
                <div class="info-user-text-reply">
                    <div class="info-user-texts">
                        <span class="info-user-text">Họ Tên:</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.hoTen : ""}</span>
                    </div >
                    <div class="info-user-texts">
                        <span class="info-user-text">Email</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.email : ""}</span>
                    </div>
                    <div class="info-user-texts">
                        <span class="info-user-text">Địa Chỉ</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.diaChi : ""}</span>
                    </div>
                    <div class="info-user-texts">
                        <span class="info-user-text">Số Điện Thoại</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.soDienThoai : ""}</span>
                    </div>
                    {roles.includes('SV')?
                    <>
                    <div class="info-user-texts">
                        <span class="info-user-text">Khóa Học</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.maLop.idKhoaHoc.tenKhoa : ""}</span>
                    </div>
                    <div class="info-user-texts">
                        <span class="info-user-text">Chuyên Ngành</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.maLop.idNganh.tenNganhDaoTao : ""}</span>
                    </div>
                    <div class="info-user-texts">
                        <span class="info-user-text">Lớp Học</span>
                        <span class="info-user-text2">{sinhvien? sinhvien.maLop.tenLopHoc : ""}</span>
                    </div>
                    </> : <></>
                    }
                </div>

            </div>
        </div>
    )
}
export default Thongtintaikhoan;