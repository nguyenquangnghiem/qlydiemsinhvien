import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { endpoints, useApi } from "../../api";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";

const DanhSachMonChuaDay = () => {
    const api = useApi();
  const keycloak = useContext(KeycloakContext);
      const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];
  const [monHoc, setMonHoc] = useState([]);

  useEffect(() => {
    const loadMonHoc = async () => {
      try {
        let e = endpoints["DSMHChuaDay"];
        e = `${e}?taiKhoanId=${keycloak === null ? "" : keycloak?.tokenParsed?.jti}`;
        let res = await api.get(e);
        console.log(res.data);
        setMonHoc(res.data);
      } catch (e) {
        console.error(e);
      }
    };
    loadMonHoc();
  }, []);
  return (
    <>
      <div class="contend">
        <div class="gv-monhoc-giaovien">
          {monHoc.map((mh) => {
            let h = `/danhsachsinhvien?monHocId=${mh.idMonHocHocKy}`;
            return (
              <div class="gv-items-monhoc-giaovien" key={mh.idMonHocHocKy}>
                <Link to={h}>
                  <div class="gv-item-monhoc-giaovien">
                    <img
                      src="https://res.cloudinary.com/dhcvsbuew/image/upload/v1693123925/mon_bk4slc.png"
                      alt="Image description"
                      class="gv-image-monhoc-giaovien"
                    />
                  </div>
                  <div class="gv-item-monhoc-giaovien-text">
                    {mh.idMonHoc.tenMonHoc} - {mh.idHocky.idLop.tenLopHoc}
                  </div>
                </Link>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
};
export default DanhSachMonChuaDay;
