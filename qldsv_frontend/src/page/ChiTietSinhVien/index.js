import CloseIcon from '@mui/icons-material/Close';
import EditIcon from '@mui/icons-material/Edit';
import SuccessIcon from '@mui/icons-material/Send';
import IconButton from '@mui/material/IconButton';
import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import { api, endpoints } from "../../api";

const ChiTietSinhVien = () => {
    
    const [DSDiem, setDSDiem] = useState([], null);
    const [sinhVien, setSinhVien] =useState([]);
    const [q] = useSearchParams();
    const [allowEdit, setAllowEdit] = useState(false);
    const [DSDiemDaHoc, setDSDiemDaHoc] = useState([], null);
    const [editDiemForm, setEditDiemForm] = useState([]);
    useEffect(() => {
        const loadloadDSDiem = async () => {
            let idSinhVien = q.get("idSinhVien");
            
            try {
                let e = endpoints['DSDiem'];
                let d = endpoints['DSDiemDaHoc'];
                let c = endpoints['getSinhVienById'];
                d = `${d}?SinhVienId=${idSinhVien}`;
                e = `${e}?SinhVienId=${idSinhVien}`;
                c = `${c}?idSinhVien=${idSinhVien}`
                let res5 = await api().get(d);
                let res1 = await api().get(e);
                let res2 = await api().get(c);
                setDSDiemDaHoc(res5.data);
                setDSDiem(res1.data);
                setSinhVien(res2.data);
            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, [allowEdit]);

    console.log(sinhVien)

    function handChange(e){
        const { name, value } = e.target;
        const words = name.split(" ");
        const obj = editDiemForm.filter(item => item.idMonHocDangKy === Array.from(words).at(1));
        if(Array.from(obj).length > 0){
            const monhoc = obj.at(0);
            monhoc[`${Array.from(words).at(0)}`] = value;
        } else {
            editDiemForm.push({
                idMonHocDangKy: Array.from(words).at(1),
                [`${Array.from(words).at(0)}`]: value
            })
        }
        setEditDiemForm(prev => [...prev]);
    }

    async function handEdit(e){
        e.preventDefault();
        try{
            await api().put(endpoints["editDiem"], editDiemForm);
            setAllowEdit(false);
        } catch (e){
            console.error(e);
        }
    }

    return (

        <div class="contend">
            <div class=" point-ky">
                <h4 class="text-header-tong-1">Thông tin Sinh viên</h4>
                <div class="container-info-stundent">

                    <div class="student-info-ad ">
                        <h5>Họ và tên: {sinhVien[0]}</h5>
                        <p>Mã sinh viên: {sinhVien[1]}</p>
                        <p>Lớp Học: {sinhVien[3]}</p>
                        <p>Ngành Học: {sinhVien[4]}</p>
                        <p>Địa chỉ: {sinhVien[5]} </p>
                        <p>Email: {sinhVien[6]}</p>
                    </div>
                    <div class="container mt-3">
                        <form onSubmit={handEdit}>
                        <div class="text-header-tong-1">
                            Tổng Hợp Điểm Của Từng Môn Đang Học
                            {allowEdit === false? <IconButton aria-label="delete" size="large" onClick={() => setAllowEdit(true)}>
                                <EditIcon />
                            </IconButton> :
                            <div className='container-btn'>
                            <IconButton aria-label="delete" size="large" onClick={() => setAllowEdit(false)}>
                                <CloseIcon />
                            </IconButton>
                            <IconButton aria-label="delete" size="large" type="submit">
                                <SuccessIcon />
                            </IconButton>
                            </div>}
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Học Kỳ</th>
                                    <th>Môn Học</th>
                                    <th>Lớp/Nhóm</th>
                                    <th>Điểm KT1</th>
                                    <th>Điểm KT2</th>
                                    <th>Điểm KT3</th>
                                    <th>Điểm Giữa Kì</th>
                                    <th>Điểm Cuối Kỳ</th>
                                    <th>Điểm Trung Bình</th>
                                    <th>Tình Trạng</th>
                                </tr>
                            </thead>
                            <tbody>
                                {DSDiem.map(c => {
                                    if (c.khoaMon === 0) {
                                        return (<tr key={c[5]}>
                                            <td>{c.hocKy}</td>
                                            <td>{c.tenMonHoc}</td>
                                            <td>{c.lopHoc}</td>
                                            {c.diemKT1 !== -1 && c.diemKT1 !== null ? <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT1 ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemKT1).toFixed(2)}/> : parseFloat(c.diemKT1).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT1 ${c.idMonHocDangKy}`}/> : "-"}</td>}
                                            {c.diemKT2 !== -1 && c.diemKT2 !== null? <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT2 ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemKT2).toFixed(2)}/> : parseFloat(c.diemKT2).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT2 ${c.idMonHocDangKy}`}/> : "-"}</td>}
                                            {c.diemKT3 !== -1 && c.diemKT3 !== null ? <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT3 ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemKT3).toFixed(2)}/> : parseFloat(c.diemKT3).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemKT3 ${c.idMonHocDangKy}`}/> : "-"}</td>}
                                            {c.diemGK !== -1 && c.diemGK !== null? <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemGK ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemGK).toFixed(2)}/> : parseFloat(c.diemGK).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemGK ${c.idMonHocDangKy}`}/> : "-"}</td>}
                                            {c.diemCK !== -1 && c.diemCK !== null ? <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemCK ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemCK).toFixed(2)}/> : parseFloat(c.diemCK).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" type='number' onChange={handChange} name={`diemCK ${c.idMonHocDangKy}`}/> : "-"}</td>}
                                            
                                            {c.diemTB !== null && c.diemTB !==-1 ? <td>{allowEdit? <input step="0.01" onChange={handChange} name={`diemTB ${c.idMonHocDangKy}`} defaultValue={parseFloat(c.diemTB).toFixed(2)}/> : parseFloat(c.diemTB).toFixed(2)}</td> : <td>{allowEdit? <input step="0.01" onChange={handChange} name={`diemTB ${c.idMonHocDangKy}`}/> : "Chưa có"}</td>}
                                            <td>{c.trangThai === 0? "Đang học" : "Hoàn thành"}</td>
                                        </tr>
                                        )
                                    }
                                })
                                }

                            </tbody>
                        </table>
                        </form>
                    </div>
                    <div class="container mt-5">
                    <p class="text-header-tong-1">Tổng Hợp Điểm Các Môn Đã Hoàn Thành</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Học Kỳ</th>
                                <th>Môn Học</th>
                                <th>Lớp/Nhóm</th>
                                
                                <th>Điểm Trung Bình</th>
                                <th>Tình Trạng</th>
                            </tr>
                        </thead>
                        <tbody>
                            {DSDiemDaHoc.map(c => {

                                return (<tr key={c[5]}>
                                    <td>{c.hocKy}</td>
                                    <td>{c.tenMonHoc}</td>
                                    <td>{c.lopHoc}</td>
                                    
                                    {c.diemTB !== null ? <td>{parseFloat(c.diemTB).toFixed(2)}</td> : <td>chưa có</td>}

                                    <td>{c.trangThai === 1 ? 'Đậu' : 'Rớt'}</td>
                                </tr>
                                )
                            })
                            }


                        </tbody>
                    </table>
                </div>
                </div>
            </div>
        </div>
    )

};
export default ChiTietSinhVien;
