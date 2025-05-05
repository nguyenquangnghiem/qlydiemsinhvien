import { useSelector } from "react-redux";
import { Navigate, useRoutes } from "react-router-dom";
import LayoutAdmin from "../layout/LayoutAdmin";
import LayoutUser from "../layout/LayoutUser";
import CapTaiKhoanAdmin from "../page/CapTaiKhoanAdmin";
import ChiTietSinhVien from "../page/ChiTietSinhVien";
import ChooseOptionLogin from "../page/ChooseOptionLogin";
import DangKyMonHoc from "../page/DangKyMonHoc";
import DangKiMonHoc from "../page/DangKyMonHocAdmin";
import DanhSachMonChuaDay from "../page/DanhSachMonChuaDay";
import DanhSachMonDaHoc from "../page/DanhSachMonDaHoc";
import DanhSachMonDaKhoa from "../page/DanhSachMonHocDaKhoa";
import DanhSachSinhVien from "../page/DanhSachSinhVien";
import DanhSachSinhVienLop from "../page/DanhSachSinhVienLop";
import DanhSachTaiKhoanAdmin from "../page/DanhSachTaiKhoanAdmin";
import GiangVienAdmin from "../page/GiangVienAdmin";
import HeDaoTaoAdmin from "../page/HeDaoTaoAdmin";
import HocKyAdmin from "../page/HocKyAdmin";
import Home from "../page/Home";
import HomeAdmin from "../page/HomeAdmin";
import KhoaDaoTaoAdmin from "../page/KhoaDaoTaoAdmin";
import KhoaHocAdmin from "../page/KhoaHocAdmin";
import LoginAdmin from "../page/LoginAdmin";
import Login from "../page/LoginUser";
import LopHocAdmin from "../page/LopHocAdmin";
import MonHocAdmin from "../page/MonHocAdmin";
import NhapDiem from "../page/NhapDiem";
import NhapDiemSinhVien from "../page/NhapDiemSinhVien";
import PhongHocAdmin from "../page/PhongHocAdmin";
import SignUp from "../page/SignUp";
import SinhVienAdmin from "../page/SinhVienAdmin";
import ThayDoiMatKhau from "../page/ThayDoiMatKhau";
import ThayDoiMatKhauAdmin from "../page/ThayDoiMatKhauAdmin";
import ThemSuaGiangVien from "../page/ThemSuaGiangVien";
import ThemSuaHeDaoTao from "../page/ThemSuaHeDaoTao";
import ThemSuaHocKy from "../page/ThemSuaHocKy";
import ThemSuaKhoa from "../page/ThemSuaKhoa";
import ThemSuaKhoaDaoTao from "../page/ThemSuaKhoaDaoTao";
import ThemSuaLopHoc from "../page/ThemSuaLopHoc";
import ThemSuaMonHoc from "../page/ThemSuaMonHoc";
import ThemSuaPhongHoc from "../page/ThemSuaPhongHoc";
import ThemSuaSinhVien from "../page/ThemSuaSinhVien";
import Thongtintaikhoan from "../page/ThongTinTaiKhoan";
import ThongTinTaiKhoanAdmin from "../page/ThongTinTaiKhoanAdmin";
import TimSinhVien from "../page/TimSinhVien";
import XemDiem from "../page/XemDiem";

const Routes = () => {
    const user = useSelector(state => state.accountReducer);
    console.log(user)
    const routes = useRoutes([
        {
            path: '/',
            element: <LayoutUser/>,
            children: [
                {
                    index: true,
                    element: user? (Array.from(user.chucVu).includes('GVU')? <Navigate to="/giaovu/home"/> : <Navigate to="/home"/>) : <Navigate to="/auth-option" replace />
                },
                {
                    path: '/home',
                    element: <Home/>
                },
                {
                    path: '/dangkymonhoc',
                    element: <DangKyMonHoc/>
                },
                {
                    path: '/danhsachmon',
                    element: <DanhSachMonDaHoc/>
                },
                {
                    path: '/xemdiem',
                    element: <XemDiem/>
                },
                // {
                //     path: '/diendan',
                //     element: <DienDan/>
                // },
                {
                    path: '/thaydoimatkhau',
                    element: <ThayDoiMatKhau/> 
                },
                // {
                //     path: '/themcauhoi',
                //     element: <Themchude/>
                // },
                {
                    path: '/thongtin',
                    element: <Thongtintaikhoan/>
                },
                // {
                //     path: '/traloidiendan',
                //     element: <TraLoiDienDan/>
                // },
                {
                    path: '/danhsachmonchuanbi',
                    element: <DanhSachMonChuaDay/>
                },
                {
                    path: '/danhsachmonhocdakhoa',
                    element: <DanhSachMonDaKhoa/>
                },
                {
                    path: '/danhsachsinhvien',
                    element: <DanhSachSinhVien/>
                },
                {
                    path: '/nhapdiem',
                    element: <NhapDiem/>
                },
                {
                    path: '/nhapdiemsinhvien',
                    element: <NhapDiemSinhVien/>
                },
                {
                    path: '/timsinhvien',
                    element: <TimSinhVien/>
                },
                {
                    path: '/chitietsinhvien',
                    element: <ChiTietSinhVien/>
                }
            ]
        },
        {
            path: '/signup-user',
            element: <SignUp/>
        },
        {
            path: '/auth-option',
            element: <ChooseOptionLogin/>
        },
        {
            path: '/login-user',
            element: <Login/>
        },
        {
            path: '/login-admin',
            element: <LoginAdmin/>
        },
        {
            path: '/giaovu',
            element: user? (Array.from(user.chucVu).includes('GVU') ? <LayoutAdmin/> : <Navigate to="/home" replace />) : <Navigate to="/auth-option" replace />,
            children: [
                {
                    path: '/giaovu/home',
                    element: <HomeAdmin/>
                },
                {
                    path: '/giaovu/sinhvien',
                    element: <SinhVienAdmin/>
                },
                {
                    path: '/giaovu/giangvien',
                    element: <GiangVienAdmin/>
                },
                {
                    path: '/giaovu/monhoc',
                    element: <MonHocAdmin/>
                },
                {
                    path: '/giaovu/lophoc',
                    element: <LopHocAdmin/>
                },
                {
                    path: '/giaovu/hocky',
                    element: <HocKyAdmin/>
                },
                {
                    path: '/giaovu/khoadaotao',
                    element: <KhoaDaoTaoAdmin/>
                },
                {
                    path: '/giaovu/phonghoc',
                    element: <PhongHocAdmin/>
                },
                {
                    path: '/giaovu/khoahoc',
                    element: <KhoaHocAdmin/>
                },
                // {
                //     path: '/giaovu/loaitaikhoan',
                //     element: <LoaiTaiKhoanAdmin/>
                // },
                {
                    path: '/giaovu/hedaotao',
                    element: <HeDaoTaoAdmin/>
                },
                {
                    path: '/giaovu/danhsachtaikhoan',
                    element: <DanhSachTaiKhoanAdmin/>
                },
                {
                    path: '/giaovu/captaikhoan',
                    element: <CapTaiKhoanAdmin/>
                },
                {
                    path: '/giaovu/thongtintaikhoan',
                    element: <ThongTinTaiKhoanAdmin/>
                },
                {
                    path: '/giaovu/thaydoimatkhau',
                    element: <ThayDoiMatKhauAdmin/>
                },
                {
                    path: '/giaovu/them-suasinhvien/:id',
                    element: <ThemSuaSinhVien/>
                },
                {
                    path: '/giaovu/them-suasinhvien',
                    element: <ThemSuaSinhVien/>
                },
                {
                    path: '/giaovu/them-suagiangvien',
                    element: <ThemSuaGiangVien/>
                },
                {
                    path: '/giaovu/them-suagiangvien/:id',
                    element: <ThemSuaGiangVien/>
                },
                {
                    path: '/giaovu/them-suamonhoc',
                    element: <ThemSuaMonHoc/>
                },
                {
                    path: '/giaovu/them-suamonhoc/:id',
                    element: <ThemSuaMonHoc/>
                },
                {
                    path: '/giaovu/them-sualophoc',
                    element: <ThemSuaLopHoc/>
                },
                {
                    path: '/giaovu/them-sualophoc/:id',
                    element: <ThemSuaLopHoc/>
                },
                {
                    path: '/giaovu/them-suahocky',
                    element: <ThemSuaHocKy/>
                },
                {
                    path: '/giaovu/them-suahocky/:id',
                    element: <ThemSuaHocKy/>
                },
                {
                    path: '/giaovu/them-suakhoadaotao',
                    element: <ThemSuaKhoaDaoTao/>
                },
                {
                    path: '/giaovu/them-suakhoadaotao/:id',
                    element: <ThemSuaKhoaDaoTao/>
                },
                {
                    path: '/giaovu/them-suaphonghoc',
                    element: <ThemSuaPhongHoc/>
                },
                {
                    path: '/giaovu/them-suaphonghoc/:id',
                    element: <ThemSuaPhongHoc/>
                },
                {
                    path: '/giaovu/them-suakhoa',
                    element: <ThemSuaKhoa/>
                },
                {
                    path: '/giaovu/them-suakhoa/:id',
                    element: <ThemSuaKhoa/>
                },
                {
                    path: '/giaovu/them-suahedaotao',
                    element: <ThemSuaHeDaoTao/>
                },
                {
                    path: '/giaovu/them-suahedaotao/:id',
                    element: <ThemSuaHeDaoTao/>
                },
                {
                    path: '/giaovu/them-suahedaotao',
                    element: <ThemSuaHeDaoTao/>
                },
                {
                    path: '/giaovu/them-suahedaotao/:id',
                    element: <ThemSuaHeDaoTao/>
                },
                {
                    path: '/giaovu/danhsachsinhvienlop/:id',
                    element: <DanhSachSinhVienLop/>
                },
                {
                    path: '/giaovu/dangkymonhoc/:id',
                    element: <DangKiMonHoc/>
                },
            ]
        }
    ])
    return routes;
}

export default Routes;