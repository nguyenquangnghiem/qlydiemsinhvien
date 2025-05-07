import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useApi } from "../../api";

const ThemSuaLopHoc = () => {

    const api = useApi();
    const { id } = useParams('id');
    const [student, setStudent] = useState(null);
    const [khoaList, setKhoaList] = useState([]);
    const [nganhDaoTaoList, setNganhDaoTaoList] = useState([]);
    const [heDaoTaoList, setHeDaoTaoList] = useState([]);
    const navigate = useNavigate();

    async function getSinhVien(){
        try{
            const response = await api.get(`/giaovu/lophoc/${id}`);
            setStudent(response.data);
            setFormData(prev => ({
                ...prev,
                idLopHoc: response.data.idLopHoc || "",
                tenLopHoc: response.data.tenLopHoc || "",
                idKhoaHoc: response.data.idKhoaHoc.idkhoa || "",
                idHeDaoTao: response.data.idHeDaoTao?.idhedaotao || "", // Chỉ lấy id
                idNganh: response.data.idNganh?.idNganhDaoTao || "", // Chỉ lấy id
            }));
        }catch(e){
            console.error(e);
        }
    }

    async function getKhoaHocList(){
        try{
            const response = await api.get("/giaovu/khoahoc");
            setKhoaList(response.data.content);
        } catch(e) {
            console.error(e);
        }
    }

    async function getHeDaoTaoList(){
        try{
            const response = await api.get("/giaovu/hedaotao");
            console.log(response.data);
            setHeDaoTaoList(response.data.content);
        } catch(e) {
            console.error(e);
        }
    }

    async function getNganhDaoTaoList(){
        try{
            const response = await api.get("/giaovu/nganhdaotao");
            setNganhDaoTaoList(response.data.content);
        } catch(e) {
            console.error(e);
        }
    }

    useEffect(() => {
        getSinhVien();
        getKhoaHocList();
        getHeDaoTaoList();
        getNganhDaoTaoList();
    },[])

    const [formData, setFormData] = useState({
        idLopHoc: student?.idLopHoc || "",
        tenLopHoc: student?.tenLopHoc || "",
        idKhoaHoc: student?.idKhoaHoc?.idkhoa || "",
        idHeDaoTao: student?.idHeDaoTao?.idhedaotao || "", // Chỉ lấy id
        idNganh: student?.idNganh?.idNganhDaoTao || "", 
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            await api.post('/giaovu/lophoc/add', formData);
            navigate('/giaovu/lophoc');
        }catch(e){
            console.error(e);
        }
    };

    return(
        <>
            <div className="row">
            <div className="margin-auto form-login form-addSV">
                <h1 className="text-center">
                    {formData.idLopHoc ? "Cập nhật lớp học" : "Thêm lớp học"}
                </h1>
                <div className="text-form-addSV">
                    <form onSubmit={handleSubmit}>
                        <input type="hidden" name="idLopHoc" value={formData.idLopHoc} />
                        <div className="form-floating mb-3 mt-3">
                            <input type="text" className="form-control" name="tenLopHoc" id="name" placeholder="Tên lớp học" value={formData.tenLopHoc} onChange={handleChange} required />
                            <label htmlFor="name">Tên lớp học</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="idKhoaHoc" id="class" value={formData.idKhoaHoc} onChange={handleChange} required>
                                <option value="">Chọn khóa học</option>
                                {khoaList.map((lh) => (
                                    <option key={lh.idkhoa} value={lh.idkhoa}>
                                        {lh.tenKhoa}
                                    </option>
                                ))}
                            </select>
                            <label htmlFor="class">Danh sách khóa học</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="idHeDaoTao" id="class" value={formData.idHeDaoTao} onChange={handleChange} required>
                                <option value="">Chọn hệ đào tạo</option>
                                {heDaoTaoList.map((lh) => (
                                    <option key={lh.idhedaotao} value={lh.idhedaotao}>
                                        {lh.tenHeDaoTao}
                                    </option>
                                ))}
                            </select>
                            <label htmlFor="class">Danh sách hệ đào tạo</label>
                        </div>
                        <div className="form-floating mt-3 mb-3">
                            <select className="form-select" name="idNganh" id="class" value={formData.idNganh} onChange={handleChange} required>
                                <option value="">Chọn ngành đào tạo</option>
                                {nganhDaoTaoList.map((lh) => (
                                    <option key={lh.idNganhDaoTao} value={lh.idNganhDaoTao}>
                                        {lh.tenNganhDaoTao}
                                    </option>
                                ))}
                            </select>
                            <label htmlFor="class">Danh sách ngành đào tạo</label>
                        </div>
                        <div className="btn-form-addsv">
                            <button className="btn input-form-addsv" style={{background: 'green', color: 'white'}} type="submit">
                                {formData.idLopHoc ? "Cập nhật lớp học" : "Thêm lớp học"}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
    );
}

export default ThemSuaLopHoc;