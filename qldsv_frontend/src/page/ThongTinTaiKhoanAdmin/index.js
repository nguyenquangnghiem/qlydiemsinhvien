import { useSelector } from "react-redux";

const ThongTinTaiKhoanAdmin = () => {

    const giaovu = useSelector(state => state.giaoVuReducer);

    return (
        <>
            <div className="info-user">
      <div className="info-title-user">
        <p className="info-private">Thông tin Cá Nhân</p>
      </div>
      <div className="info-user-text-reply">
        <div className="info-user-texts">
          <span className="info-user-text">Họ Tên:</span>
          <span className="info-user-text2">{giaovu?.tenGiaoVu}</span>
        </div>
        <div className="info-user-texts">
          <span className="info-user-text">Giới Tính:</span>
          <span className="info-user-text2">
            {giaovu?.gioiTinh === 1 ? "Nam" : "Nữ"}
          </span>
        </div>
        <div className="info-user-texts">
          <span className="info-user-text">Số Điện Thoại:</span>
          <span className="info-user-text2">{giaovu?.soDienThoai}</span>
        </div>
      </div>
    </div>
        </>
    );
}

export default ThongTinTaiKhoanAdmin;