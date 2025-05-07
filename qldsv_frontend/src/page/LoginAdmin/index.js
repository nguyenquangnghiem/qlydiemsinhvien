import { useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import cookie from "react-cookies";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import Apis, { useApi, endpoints } from "../../api";
import Footer from "../../component/Footer";
import Header from "../../component/Header";

const LoginAdmin = () => {
    const api = useApi();
    const dispatch = useDispatch();
  const [username, setusername] = useState();
  const [password, setpassword] = useState();
  const [success, setSuccess] = useState(false);
  let nav = useNavigate();

  const login = (evt) => {
    evt.preventDefault();
    setSuccess(false);

    const process = async () => {
      try {
        let res = await Apis.post(endpoints["login"], {
          tenTaiKhoan: username,
          matKhau: password,
        });
        cookie.save("token", res.data.access_token);
        cookie.save("refresh_token", res.data.refresh_token);
        let { data } = await api.get(endpoints["current-user"]);
        cookie.save("user", data);
        if (Array.from(data.chucVu).includes("GVU")) {
          let sinhvien = await api.get("/giaovu/current-giaovu");
          cookie.save("giaovu", sinhvien.data);
          dispatch({
            type: "login_gvu",
            payLoad: sinhvien.data,
          });
          dispatch({
            type: "login",
            payLoad: data,
          });
          nav("/giaovu/home");
        } else {
            setSuccess(true);
        }
      } catch (ex) {
        console.error(ex);
        setSuccess(true);
      }
    };
    process();
  };

  return (
    <>
      <Header />
      <div className="container">
        <div className=" form-login  shadow-lg ">
          <div className="text-center ">
            <h4 className="text-login">Đăng Nhập</h4>
          </div>
          <Form className="mt-5 mb-5" onSubmit={login}>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Tài Khoản</Form.Label>
              <Form.Control
                type="text"
                value={username}
                onChange={(e) => setusername(e.target.value)}
                placeholder="Tài Khoản"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Mật Khẩu</Form.Label>
              <Form.Control
                type="password"
                value={password}
                onChange={(e) => setpassword(e.target.value)}
                placeholder="Mật Khẩu"
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Button id="submit" type="submit">
                Đăng Nhập
              </Button>
            </Form.Group>
            {success ? (
              <Alert variant="danger">Sai tên tài khoản hoặc mật khẩu</Alert>
            ) : (
              <div></div>
            )}
          </Form>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default LoginAdmin;