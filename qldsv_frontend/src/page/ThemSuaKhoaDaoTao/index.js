import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useApi } from "../../api";

const ThemSuaKhoaDaoTao = () => {

    const api = useApi();
    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api.get(`/giaovu/khoadaotao/${id}`);
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
                idKhoaDaoTao: student?.idKhoaDaoTao || "",
                tenKhoaDaoTao: student?.tenKhoaDaoTao || "",
            });
        }
    }, [student]); // Chạy lại khi student thay đổi

    const [formData, setFormData] = useState({
        idKhoaDaoTao: student?.idKhoaDaoTao || "",
        tenKhoaDaoTao: student?.tenKhoaDaoTao || "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api.post('/giaovu/khoadaotao/add', formData);
            navigate('/giaovu/khoadaotao');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idKhoaDaoTao ? "Cập nhật khoa đào tạo" : "Thêm khoa đào tạo"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idKhoaDaoTao" value={formData.idKhoaDaoTao} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenKhoaDaoTao" id="name" placeholder="Tên khoa đào tạo" value={formData.tenKhoaDaoTao} onChange={handleChange} required />
                            <label htmlFor="name">Tên khoa đào tạo</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idKhoaDaoTao ? "Cập nhật khoa đào tạo" : "Thêm khoa đào tạo"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaKhoaDaoTao;