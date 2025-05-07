import { useContext, useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { Link, useNavigate, useSearchParams } from "react-router-dom";
import { endpoints, useApi } from "../../api";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";
const Themchude = () => {
    const api = useApi();
    const keycloak = useContext(KeycloakContext);
        const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];
    const [noiDung, setNoiDung] = useState();
    let nav = useNavigate();
    const [q] = useSearchParams();

    let cauhoiid = q.get("cauhoiId");
    useEffect(() => {

        const loadcauhoi = async () => {
            try{
            let ch = endpoints['cauhoi']
            let cauhoiid = q.get("cauhoiId");
            if (cauhoiid != null) {
                ch = `${ch}?cauhoiId=${cauhoiid}`;
                let res = await api.get(ch);
                setNoiDung(res.data[0]);
            }
        }catch(e){
            console.error(e);
        }
        }
        loadcauhoi();
    }, [q])

    const addCauHoi = (evt) => {
        evt.preventDefault();
        const currentDate = new Date();

        const formattedDate = `${currentDate.getFullYear()}-${(currentDate.getMonth() + 1).toString().padStart(2, '0')}-${currentDate.getDate().toString().padStart(2, '0')}`;
        const formattedTime = `${currentDate.getHours().toString().padStart(2, '0')}:${currentDate.getMinutes().toString().padStart(2, '0')}`;
        const process = async () => {
            try {
                let res = await api.post(endpoints['themCauHoi'], {
                    "noiDungCauHoi": noiDung,
                    "idTaiKhoan": keycloak?.tokenParsed?.sub,
                    "ngayTao": `${formattedDate} ${formattedTime}`,
                    "idCauHoiDienDan": cauhoiid,
                })
                nav("/diendan");
            } catch (ex) {
                console.error(ex);
            }
        }
        process();
    };

    return (
        <div class="traloi-diendan-none" id="container2">
            <Form className="mt-5 mb-5" onSubmit={addCauHoi}>
                <div class="form-themcauhoi-diendan">
                    <Link to="/diendan" className="close-traloi-diendan ">Đóng</Link>
                    <h5><i class="fa-solid fa-user icon-padding mb-3"></i>Thêm Câu Hỏi Diễn Đàn</h5>
                    <p class="content-question-day"><i class="fa-solid fa-calendar-days icon-padding"></i>Diễn đàn trao đổi của sinh viên và giảng viên </p>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Nhập nội dung câu hỏi</Form.Label>
                        <Form.Control className="form-control me-2" type="text" value={noiDung}
                            onChange={(e) => setNoiDung(e.target.value)} placeholder="Câu hỏi....." required />
                    </Form.Group>

                    {cauhoiid === null ? <Button className="btn-traloi-diendan mt-3" type="submit"><i class="fa-solid fa-reply icon-padding"></i>Thêm Câu Hỏi</Button> : <Button className="btn-traloi-diendan mt-3" type="submit"><i class="fa-solid fa-reply icon-padding"></i>Chỉnh Sửa</Button>}
                </div>
            </Form>
        </div>
    )
}
export default Themchude;