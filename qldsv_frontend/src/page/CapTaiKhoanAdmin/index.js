import { useEffect, useState } from "react";
import { useApi } from "../../api";

const CapTaiKhoanAdmin = () => {

    const api = useApi();
    const [classList, setClassList] = useState([]);

    async function getLopHocList(){
        try{
            const response = await api.get("/giaovu/giangvienchuacotaikhoan");
            setClassList(response.data);
        }catch(e){
            console.error(e);
        }
    }

    useEffect(() => {
        getLopHocList();
    },[])

    const [formData, setFormData] = useState({
        tenTaiKhoan: "",
        matKhau:"",
        xacNhanMk: "",
        idGiangVien: "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api.post('/giaovu/taikhoan/dangki', formData);
            window.alert("Cấp tài khoản thành công!!!")
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    Cấp tài khoản cho giáo viên
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenTaiKhoan" id="name" placeholder="Tên tài khoản" value={formData.tenTaiKhoan} onChange={handleChange} required />
                            <label htmlFor="name">Tên tài khoản</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="password" className="form-control" name="matKhau" id="address" placeholder="Mật khẩu" value={formData.matKhau} onChange={handleChange} required />
                            <label htmlFor="address">Mật khẩu</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="password" className="form-control" name="xacNhanMk" id="address" placeholder="Xác nhận mật khẩu" value={formData.xacNhanMk} onChange={handleChange} required />
                            <label htmlFor="address">Xác nhận mật khẩu</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="idGiangVien" id="class" value={formData.idGiangVien} onChange={handleChange} required>
                                <option value="">Chọn giảng viên</option>
                                {classList.map((lh) => (
                                    <option key={lh.idGiangVien} value={lh.idGiangVien}>
                                        {lh.hoTen}
                                    </option>
                                ))}
                            </select>
                            <label htmlFor="class">Danh sách giảng viên</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                Cấp tài khoản
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default CapTaiKhoanAdmin;