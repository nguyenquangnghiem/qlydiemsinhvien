import dayjs from 'dayjs';
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useApi } from "../../api";

const ThemSuaSinhVien = () => {

    const api = useApi();
    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const [classList, setClassList] = useState([]);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api.get(`/giaovu/sinhvien/${id}`);
            setStudent(response.data);
        }catch(e){
            console.error(e);
        }
    }

    async function getLopHocList(){
        try{
            const response = await api.get("/api/dslophoc/");
            setClassList(response.data.content);
        }catch(e){
            console.error(e);
        }
    }

    useEffect(() => {
        getSinhVien();
        getLopHocList();
    },[])

    useEffect(() => {
        if (student) {
            setFormData({
                idSinhVien: student.idSinhVien || "",
                idTaiKhoan: student?.idTaiKhoan || "",
                hoTen: student.hoTen || "",
                ngaySinh: dayjs(student.ngaySinh).format("YYYY-MM-DD") || "",
                diaChi: student.diaChi || "",
                gioiTinh: student.gioiTinh ?? "-1",
                maLop: student.maLop?.idLopHoc || "",
                soDienThoai: student.soDienThoai || "",
                email: student.email || ""
            });
        }
    }, [student]); // Chạy lại khi student thay đổi

    const [formData, setFormData] = useState({
        idSinhVien: student?.idSinhVien || "",
        idTaiKhoan: student?.idTaiKhoan || "",
        hoTen: student?.hoTen || "",
        ngaySinh: student?.ngaySinh || "",
        diaChi: student?.diaChi || "",
        gioiTinh: student?.gioiTinh ?? "-1",
        maLop: student?.maLop?.idLopHoc || "",
        soDienThoai: student?.soDienThoai || "",
        email: student?.email || ""
    });

    console.log(formData);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api.post('/giaovu/sinhvien/add', formData);
            navigate('/giaovu/sinhvien');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idSinhVien ? "Cập nhật sinh viên" : "Thêm sinh viên"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idSinhVien" value={formData.idSinhVien} />
                        <input type="hidden" name="idTaiKhoan" value={formData.idTaiKhoan} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="hoTen" id="name" placeholder="Họ và tên" value={formData.hoTen} onChange={handleChange} required />
                            <label htmlFor="name">Họ và tên</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="date" className="form-control" name="ngaySinh" id="dateofbirth" placeholder="Ngày sinh" value={formData.ngaySinh} onChange={handleChange} required />
                            <label htmlFor="dateofbirth">Ngày sinh</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="text" className="form-control" name="diaChi" id="address" placeholder="Địa chỉ" value={formData.diaChi} onChange={handleChange} required />
                            <label htmlFor="address">Địa chỉ</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="gioiTinh" id="sex" value={formData.gioiTinh} onChange={handleChange} required>
                                <option value="-1">Chọn giới tính</option>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                            <label htmlFor="sex">Giới tính</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="maLop" id="class" value={formData.maLop} onChange={handleChange} required>
                                <option value="">Chọn lớp học</option>
                                {classList.map((lh) => (
                                    <option key={lh.idLopHoc} value={lh.idLopHoc}>
                                        {lh.tenLopHoc}
                                    </option>
                                ))}
                            </select>
                            <label htmlFor="class">Danh sách lớp học</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="text" className="form-control" name="soDienThoai" id="phonenumber" placeholder="Số điện thoại" value={formData.soDienThoai} onChange={handleChange} required />
                            <label htmlFor="phonenumber">Số điện thoại</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="email" className="form-control" name="email" id="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
                            <label htmlFor="email">Email</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="password" className="form-control" name="matKhau" id="password" placeholder="Mật khẩu" value={formData?.matKhau} onChange={handleChange} />
                            <label htmlFor="password">Mật khẩu</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="password" className="form-control" name="xacNhanMk" id="confirmPassword" placeholder="Xác nhận mật khẩu" value={formData?.xacNhanMk} onChange={handleChange} />
                            <label htmlFor="confirmPassword">Xác nhận mật khẩu</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idSinhVien ? "Cập nhật sinh viên" : "Thêm sinh viên"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaSinhVien;