import { useEffect, useState } from "react";
import { api } from "../../api";
import { useNavigate, useParams } from "react-router-dom";

const ThemSuaMonHoc = () => {

    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api().get(`/giaovu/monhoc/${id}`);
            console.log(response.data);
            setStudent(response.data);
            setFormData(prev => ({
                ...prev,
                ...response.data
            }))
        }catch(e){
            console.error(e);
        }
    }

    useEffect(() => {
        getSinhVien();
    },[])

    const [formData, setFormData] = useState({
        idMonHoc: student?.idMonHoc || "",
        tenMonHoc: student?.tenMonHoc || "",
        soTinChi: student?.soTinChi || "",
    });

    console.log(formData);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api().post('/giaovu/monhoc/add', formData);
            navigate('/giaovu/monhoc');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idMonHoc ? "Cập nhật môn học" : "Thêm môn học"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idMonHoc" value={formData.idMonHoc} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenMonHoc" id="name" placeholder="Tên môn học" value={formData.tenMonHoc} onChange={handleChange} required />
                            <label htmlFor="name">Tên môn học</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <input type="number" className="form-control" name="soTinChi" id="dateofbirth" placeholder="Số tính chỉ" value={formData.soTinChi} onChange={handleChange} required />
                            <label htmlFor="dateofbirth">Số tín chỉ</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idMonHoc ? "Cập nhật môn học" : "Thêm môn học"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaMonHoc;