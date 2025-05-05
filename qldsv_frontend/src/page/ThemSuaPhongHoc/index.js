import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { api } from "../../api";

const ThemSuaPhongHoc = () => {

    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api().get(`/giaovu/phonghoc/${id}`);
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
                idPhongHoc: student?.idPhongHoc || "",
                tenPhongHoc: student?.tenPhongHoc || "",
            });
        }
    }, [student]); // Chạy lại khi student thay đổi

    const [formData, setFormData] = useState({
        idPhongHoc: student?.idPhongHoc || "",
        tenPhongHoc: student?.tenPhongHoc || "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api().post('/giaovu/phonghoc/add', formData);
            navigate('/giaovu/phonghoc');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idPhongHoc ? "Cập nhật phòng học" : "Thêm phòng học"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idPhongHoc" value={formData.idPhongHoc} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenPhongHoc" id="name" placeholder="Tên phòng học" value={formData.tenPhongHoc} onChange={handleChange} required />
                            <label htmlFor="name">Tên phòng học</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idPhongHoc ? "Cập nhật phòng học" : "Thêm phòng học"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaPhongHoc;