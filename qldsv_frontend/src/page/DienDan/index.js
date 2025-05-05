import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { api, endpoints } from "../../api";

const DienDan = () => {
    const user = useSelector(state => state.accountReducer);
    const [cauHois, setCauhois] = useState([]);
    const [reload, setReload] = useState(false);
    let nav = useNavigate();

    useEffect(() => {
        const loadCauHoi = async () => {
            try{
                let res = await api().get(endpoints["cauHois"]);
                setCauhois(res.data);
            }catch(e){
                console.error(e);
            }
        };
        loadCauHoi();
    }, [reload]); // Include deletionComplete in the dependency array

    async function handleDelete(id){
        try{
        const confirmed = window.confirm(
            "Are you sure you want to delete this question?"
        );
        if (confirmed) {
            let ch = endpoints["deleteCauHoi"];
            ch = `${ch}?idCauHoiDienDan=${id}`;
            await api().delete(ch);
            nav("/diendan");
            setReload(pre => !pre);
        } else {
            nav("/diendan");
        }
    }catch(e){
        console.error(e);
    }
    }

    const currentDate = new Date();
    const day = currentDate.getDate();
    const month = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0, cộng thêm 1 để hiển thị đúng tháng
    const year = currentDate.getFullYear();
    return (
        <div class="contend">
            <div class="content-diendan">
                <h3>Diễn Đàng Trao Đổi</h3>
                <p>Ngày hiện tại: {day}/{month}/{year}</p>
                <Link
                    to="/themcauhoi"
                    class="text-contend-link add-contend-question"
                    id="themcauhoi"
                >
                    Thêm Chủ Đề
                </Link>
                {cauHois.map((c) => {
                    let h = `/traloidiendan?cauhoiId=${c.idCauHoiDienDan}`;
                    let k = `/themcauhoi?cauhoiId=${c.idCauHoiDienDan}`;
                    return (
                        <div class="content-question" key={c.idCauHoiDienDan}>
                            <h5>
                                <i class="fa-solid fa-user icon-padding"></i>
                                {c?.taiKhoan?.username}
                                {c?.taiKhoan? Array.from(c.taiKhoan.chucVu).includes("GV") : false
                                ? " - Giảng Viên"
                                : ""}
                            </h5>
                            <p class="content-question-day">
                                <i class="fa-solid fa-calendar-days icon-padding"></i>
                                {c.ngayTao}
                            </p>
                            <p class="content-question-noidung">{c.noiDungCauHoi}</p>

                            <Link to={h} class="text-contend-link">
                                <i class="fa-solid fa-comment-dots icon-padding"></i>Xem Câu
                                Trả Lời
                            </Link>
                            {user.id === c?.taiKhoan?.id ? (
                                <Link to={k} class="text-contend-link update-text-diendan">
                                    <i class="fa-solid fa-pen-to-square icon-padding"></i>Chỉnh Sửa
                                </Link>
                            ) : null}
                            {user.id === c?.taiKhoan?.id ? (
                                <a style={{cursor: 'pointer'}} onClick={() => handleDelete(c.idCauHoiDienDan)} class="text-contend-link update-text-diendan">
                                    <i class="fa-solid fa-trash icon-padding"></i>Xóa
                                </a>
                            ) : null}
                            <Link to={h} class="text-contend-link update-text-diendan">
                                <i class="fa-solid fa-reply icon-padding"></i>Trả Lời
                            </Link>
                        </div>
                    );
                })}
            </div>
        </div>
    );
};

export default DienDan;
