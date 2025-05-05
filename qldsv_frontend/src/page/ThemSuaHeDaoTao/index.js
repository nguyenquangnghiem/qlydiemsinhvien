import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { api } from "../../api";

const ThemSuaHeDaoTao = () => {

    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api().get(`/giaovu/hedaotao/${id}`);
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
                idHeDaoTao: student?.idhedaotao || "",
                tenHeDaoTao: student?.tenHeDaoTao || "",
            });
        }
    }, [student]); // Chạy lại khi student thay đổi

    const [formData, setFormData] = useState({
        idHeDaoTao: student?.idhedaotao || "",
        tenHeDaoTao: student?.tenHeDaoTao || "",
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api().post('/giaovu/hedaotao/add', formData);
            navigate('/giaovu/hedaotao');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idHeDaoTao ? "Cập nhật hệ đào tạo" : "Thêm hệ đào tạo"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idHeDaoTao" value={formData.idHeDaoTao} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenHeDaoTao" id="name" placeholder="Tên hệ đào tạo" value={formData.tenHeDaoTao} onChange={handleChange} required />
                            <label htmlFor="name">Tên hệ đào tạo</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idHeDaoTao ? "Cập nhật hệ đào tạo" : "Thêm hệ đào tạo"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaHeDaoTao;