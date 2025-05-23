import { useEffect, useState } from "react";
import { Alert } from "react-bootstrap";
import { Link, useSearchParams } from "react-router-dom";
import { useApi, endpoints } from "../../api";

const TimSinhVien = () => {
    const api = useApi();
    const [DSSinhVien, setDSSinhVien] = useState([]);
    const [q] = useSearchParams();
    useEffect(() => {
        const loadSv = async () => {
            try {
                let e = endpoints['getSinhVienByIdOrTen'];
                let idGiangVien = q.get("idGiangVien");
                let tenSinhVien = q.get("tenSinhVien");
                if (idGiangVien !== null) {
                    if (tenSinhVien !== null) {
                        e = `${e}?idGiangVien=${idGiangVien}&tenSinhVien=${tenSinhVien}`;
                        let res = await api.post(e);
                        setDSSinhVien(res.data);
                    }
                    else {
                        e = `${e}?idGiangVien=${idGiangVien}`;
                        let res = await api.post(e);
                        setDSSinhVien(res.data);
                    }
                }
            } catch (ex) {
                console.error(ex);
            }

        }
        loadSv();
    }, [q]);

    

return (
    <>
        <div class="contend">

            <div class=" point-ky">
                <div class="container mt-3 " >
                    <p class="text-header-tong-1">Danh Sách Sinh Viên Của Bạn</p>
                    <div >
                        <table class="table" >
                            <thead>
                                <tr>
                                    <th>Mã sinh viên</th>
                                    <th>Họ và tên</th>
                                    <th>Môn Học</th>
                                    <th>Lớp/Nhóm</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {DSSinhVien.map(sv => {
                                    let h =`/chitietsinhvien/?idSinhVien=${sv.idSinhVien.idSinhVien}`;
                                    return (
                                        <tr key={sv.idMonHocDangKy}>
                                            <td>{sv.idSinhVien.idSinhVien}</td>
                                            <td>{sv.idSinhVien.hoTen}</td>
                                            <td>{sv.idMonHoc.idMonHoc.tenMonHoc}</td>
                                            <td>{sv.idMonHoc.idHocky.idLop.tenLopHoc}</td>
                                            
                                            <td><Link to={h}><button type="button" class="btn btn-info" >Chi Tiết</button></Link></td>
                                        </tr>
                                    )
                                })}

                            </tbody>
                        </table>
                        {DSSinhVien.length === 0 ? <Alert variant="secondary" className="mt-3">Không có sinh viên</Alert> : <></>}
                    </div>


                </div>
            </div>
        </div>
    </>
);
};
export default TimSinhVien;
