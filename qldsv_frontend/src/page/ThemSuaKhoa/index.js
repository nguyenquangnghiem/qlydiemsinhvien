import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { api } from "../../api";

const ThemSuaKhoa = () => {

    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api().get(`/giaovu/khoa/${id}`);
            setStudent(response.data);
        }catch(e){
            console.error(e);
        }
    }


    useEffect(() => {
        getSinhVien();
    },[])

    useEffect(() => {
        if (student) {
            setFormData({
                idKhoa: student?.idkhoa || "",
                tenKhoa: student?.tenKhoa || "",
            });
        }
    }, [student]); // Chạy lại khi student thay đổi

    const [formData, setFormData] = useState({
        idKhoa: student?.idkhoa || "",
        tenKhoa: student?.tenKhoa || "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api().post('/giaovu/khoa/add', formData);
            navigate('/giaovu/khoahoc');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idKhoa ? "Cập nhật khóa học" : "Thêm khóa học"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idKhoa" value={formData.idKhoa} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenKhoa" id="name" placeholder="Tên khóa học" value={formData.tenKhoa} onChange={handleChange} required />
                            <label htmlFor="name">Tên khóa học</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idKhoa ? "Cập nhật khóa học" : "Thêm khóa học"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaKhoa;