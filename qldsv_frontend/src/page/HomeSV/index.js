import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { api, endpoints } from "../../api";


const HomeSV = () => {
    const sinhvien = useSelector(state => state.sinhVienReducer);
    const [DSDiem, setDSDiem] = useState([]);
    const [Diem, setDiem] = useState([]);
    const [DiemHe4, setDiemHe4] = useState([]);
    useEffect(() => {
        const loadloadDSDiem = async () => {
            try {
                let e = endpoints['DSDiemTrungBinhHocKy'];
                let a = endpoints['DiemTrungBinhHe10'];
                let b = endpoints['DiemTrungBinhHe4'];
                e = `${e}?SinhVienId=${sinhvien.idSinhVien}`;
                a = `${a}?SinhVienId=${sinhvien.idSinhVien}`;
                b = `${b}?SinhVienId=${sinhvien.idSinhVien}`;
                let res1 = await api().get(e);
                let res2 = await api().get(a);
                let res3 = await api().get(b);
                setDSDiem(res1.data);
                setDiem(res2.data);
                setDiemHe4(res3.data);
            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, [])
    return (
        <div class="contend">
            <div class="point">
                <h4 >Tổng quan</h4>
                <h6 class="text-header-tong ">Tổng Hợp Nhanh Các Thông Tin </h6>
                <div class="container mt-3">
                    
                    <p class="text-header-tong-1">Tổng Hợp Điểm & Xếp Loại Học Tập</p>
                    <table class="table">
                        <thead>
                            <tr>

                                <th>Điểm Hệ 10</th>
                                <th>Điểm Hệ 4</th>
                                <th>Xếp Loại</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr >
                                <td>{!isNaN(Diem)? parseFloat(Diem).toFixed(2) : "_"}</td>
                                <td>{!isNaN(DiemHe4)? parseFloat(DiemHe4).toFixed(2) : "_"}</td>
                                <td>
                                    {(() => {
                                        switch (true) {
                                            case Diem >= 8.5:
                                                return 'Giỏi';
                                            case Diem >= 7:
                                                return 'Khá';
                                            case Diem >= 5:
                                                return 'Trung bình';
                                            case Diem >= 3.5:
                                                return 'Yếu';
                                                default: 
                                                return "Chưa";
                                        }
                                    })()}
                                </td>
                            </tr>


                        </tbody>
                    </table>
                </div>
            </div>
            <div class=" point-ky">
                <div class="container mt-3">
                    <p class="text-header-tong-1">Tổng Hợp Điểm Của Từng Học Kỳ</p>
                    <table class="table">
                        <thead>
                            <tr>
                                <th >Học Kỳ</th>
                                <th>Điểm Hệ 10</th>
                                <th>Điểm hệ 4</th>
                            </tr>
                        </thead>
                        <tbody >
                            {DSDiem.map(c => <tr key={c[2]}>
                                <td>{c[0]}</td>
                                <td>{isNaN(c[1])? "_" : parseFloat(c[1]).toFixed(2)}</td>
                                <td>{isNaN(c[2])? "_" : parseFloat(c[2]).toFixed(2)}</td>
                            </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default HomeSV;