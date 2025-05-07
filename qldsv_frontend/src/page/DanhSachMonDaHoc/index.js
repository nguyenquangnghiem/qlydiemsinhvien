import { useContext, useEffect, useState } from "react";
import { endpoints, useApi } from "../../api";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";


const DanhSachMonDaHoc = () => {
    const api = useApi();

    const keycloak = useContext(KeycloakContext);
          const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];

    const [DSDiemDaHoc, setDSDiemDaHoc] = useState([], null);

    useEffect(() => {
        const loadloadDSDiem = async () => {
            try {

                let d = endpoints['DSDiemDaHoc'];


                d = `${d}?SinhVienId=${keycloak?.tokenParsed?.jti}`;

                let res5 = await api.get(d);

                setDSDiemDaHoc(res5.data);

            } catch (ex) {
                console.error(ex);
            }

        }
        loadloadDSDiem();
    }, []);
    return (
        <div class="contend">
            <div class="point mt-5">
                <h2>Các Môn Đã Hoàn Thành</h2>
                <table class="table table-striped mt-5">
                    <thead>
                        <tr>
                            <th>Học Kỳ</th>
                            <th>Tên Môn Học</th>
                            <th>Lớp/Nhóm</th>
                            <th>Điểm Trung Bình</th>
                            <th>Xếp Loại</th>
                        </tr>
                    </thead>
                    <tbody>
                    {DSDiemDaHoc.map(c => {
                                    return (<tr key={c[5]}>
                                        <td>{c.hocKy}</td>
                                        <td>{c.tenMonHoc}</td>
                                        <td>{c.lopHoc}</td>
                                        <td>{c.diemTB}</td>
                                        <td>{c.xepLoai}</td>
                                    </tr>
                                    )
                                }


                            )
                            }
                            
                        </tbody>
                </table>
            </div>
        </div>
    )
}

export default DanhSachMonDaHoc;