import { useContext } from "react";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";

const ThongTinTaiKhoanAdmin = () => {

    const keycloak = useContext(KeycloakContext);
        const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];

    return (
        <>
            <div className="info-user">
      <div className="info-title-user">
        <p className="info-private">Thông tin Cá Nhân</p>
      </div>
      <div className="info-user-text-reply">
        <div className="info-user-texts">
          <span className="info-user-text">Họ Tên:</span>
          <span className="info-user-text2">{keycloak?.tokenParsed?.ho_ten}</span>
        </div>
        <div className="info-user-texts">
          <span className="info-user-text">Giới Tính:</span>
          <span className="info-user-text2">
            {keycloak?.tokenParsed?.gioi_tinh}
          </span>
        </div>
        <div className="info-user-texts">
          <span className="info-user-text">Số Điện Thoại:</span>
          <span className="info-user-text2">{keycloak?.tokenParsed?.so_dien_thoai}</span>
        </div>
      </div>
    </div>
        </>
    );
}

export default ThongTinTaiKhoanAdmin;