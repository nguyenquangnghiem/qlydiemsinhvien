import axios from "axios";
import { useContext } from "react";
import { KeycloakContext } from "../component/Keycloak/keycloakProvider";

export const endpoints = {
    "cauHois": `/api/cauhoi/`,
    "cauTraLoi": `/api/traloi/`,
    "cauhoi": `/api/cauhoiid/`,
    "login": `/api/login/`,
    "current-user": `/api/current-user/`,
    "DSDiemTrungBinhHocKy":`/api/DSDiemSVHocKy/`,
    "DiemTrungBinhHe10":`/api/TrungBinhDiem/`,
    "DiemTrungBinhHe4":`/api/TrungBinhDiemHe4/`,
    "DSDiem":`/api/DanhSachDiem/`,
    "DSDiemDaHoc":`/api/DanhSachDiemDaHoc/`,
    "DSMonHocById":`/api/monhocsinhvien/`,
    "signup":`/api/users/`,
    "changePassword":`/api/change-password/`,
    "current-sinhvien":`/api/current-sinhvien/`,
    "current-giangvien":`/api/current-user-gv/`,
    "themCauHoi":`/api/add-cauhoi/`,
    "themTraLoi":`/api/add-traloi/`,
    "udateImage":`/api/change-avt/`,
    "deleteCauHoi":`/api/delete-cauhoi/`,
    "monHocIdTK":`/api/monhocgiangvien/`,
    "DSSinhVienByMonHoc":`/api/monhocsinhvien/`,
    "addDiem":`/api/add-diem/`,
    "addListDiem":`/api/add-List-diem/`,
    "getdiemById":`/api/diem-idDiem/`,
    "getSinhVienByIdOrTen":`/api/diemSV-idSV/`,
    "khoaDiem":`/api/KhoaDiem/`,
    "listMonHocDangKi":`/api/monhochocky/`,
    "listMonHocSVDangKy":`/api/monhocSVdangky/`,
    "dangKyMonHoc":`/api/dangkymonhoc/`,
    "huyDangKyMonHoc":`/api/huydangkymonhoc/`,
    "DSLopHoc":`/api/dslophoc/`,
    "sendCode":`/api/send-code/`,
    "DSMHChuaDay":`/api/monhocgiangvienchuaday/`,
    "DSMHDaDay":`/api/monhocgiangviendaday/`,
    "getSinhVienById":`/api/SinhVien-Id/`,
    "thanhToanHocPhi":`/api/create_payment/`,
    "xacnhanthanhtoan":`/api/queryTransactionStatus/`,
    "addCotDiem":`/api/addCotDiem_SV/`,
    "deleteCotDiem":`/api/deleteCotDiem_SV/`,
    "tinhDiemTB":`/api/TinhDiemTB_SV/`,
    "getMonHocById": `/api/monhoc/`,
    "editDiem": `/api/edit-list-diem/`,

}

export const useApi = () => {
  const keycloak = useContext(KeycloakContext);

  return axios.create({
    baseURL: "http://localhost:8080",
    headers: {
      Authorization: `Bearer ${keycloak?.token}`
    }
  });
};
export default axios.create({
    baseURL: "http://localhost:8080"
})