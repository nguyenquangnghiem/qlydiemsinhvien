import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { api, endpoints } from "../../api";

const HomeGV = () => {
  const user = useSelector((state) => state.accountReducer);
  const [monHoc, setMonHoc] = useState([]);

  useEffect(() => {
    const loadMonHoc = async () => {
      try {
        let e = endpoints["monHocIdTK"];
        e = `${e}?taiKhoanId=${user === null ? "" : user.id}`;
        let res = await api().get(e);
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
            let h = `/nhapdiem?monHocId=${mh.idMonHocHocKy}`;
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
export default HomeGV;
