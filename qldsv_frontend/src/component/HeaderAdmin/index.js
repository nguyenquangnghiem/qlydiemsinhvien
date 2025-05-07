import Box from "@mui/material/Box";
import Tab from "@mui/material/Tab";
import Tabs from "@mui/material/Tabs";
import PropTypes from "prop-types";
import * as React from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { keycloakService } from "../Keycloak";

function CustomTabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
    </div>
  );
}

CustomTabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

const HeaderAdmin = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  async function handleLogout(){
    try{
        keycloakService.logout();
    }catch(e){
        console.error(e);
    }
  }

  const navList = [
    {
      path: "/giaovu/sinhvien",
      name: "Sinh viên",
    },
    {
      path: "/giaovu/giangvien",
      name: "Giảng viên",
    },
    {
      path: "/giaovu/monhoc",
      name: "Môn học",
    },
    {
      path: "/giaovu/lophoc",
      name: "Lớp học",
    },
    {
      path: "/giaovu/hocky",
      name: "Học kỳ",
    },
    {
      path: "/giaovu/khoadaotao",
      name: "Khoa đào tạo",
    },
    {
      path: "/giaovu/phonghoc",
      name: "Phòng học",
    },
    {
      path: "/giaovu/khoahoc",
      name: "Khóa học",
    },
    {
      path: "/giaovu/hedaotao",
      name: "Hệ đào tạo",
    },
    // {
    //   path: "/giaovu/loaitaikhoan",
    //   name: "Loại tài khoản",
    // },
    {
      path: "/giaovu/danhsachtaikhoan",
      name: "Danh sách tài khoản",
    },
    // {
    //   path: "/giaovu/captaikhoan",
    //   name: "Cấp tài khoản",
    // },
    {
      path: "/giaovu/thongtintaikhoan",
      name: "Thông tin tài khoản",
    },
    {
      path: "/giaovu/thaydoimatkhau",
      name: "Thay đổi mật khẩu",
    },
  ];
  return (
    <>
      <div className="container--header__admin">
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="basic tabs example"
              sx={{
                 backgroundColor: '#05386B',
                 height: 70,
                 paddingTop: 1,
                 "& .MuiTab-root": { color: "#fffa", textTransform: 'none', }, // Màu chữ mặc định của Tab
                "& .Mui-selected": { color: "#fff" }, // Màu chữ khi Tab được chọn
              }}
            >
                <Tab label={"Admin"} onClick={() => navigate('/giaovu/home')}></Tab>
                {navList.map((nav) => {
                    return <Tab onClick={() => navigate(nav.path)} label={nav.name} />
            })}
            <Tab label={"Đăng xuất"} onClick={handleLogout}></Tab>
            </Tabs>
      </div>
    </>
  );
};

export default HeaderAdmin;
