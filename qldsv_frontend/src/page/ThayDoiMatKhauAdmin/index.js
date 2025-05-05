import { useForm } from "react-hook-form";
import { useSelector } from "react-redux";
import { api } from "../../api";

const ThayDoiMatKhauAdmin = () => {

    const taikhoanAD = useSelector(state => state.accountReducer);

    const {
        register,
        handleSubmit,
        formState: { errors },
      } = useForm();
    
      const onSubmit = async (data) => {
        console.log("Dữ liệu gửi đi:", data);
        try {
            const response = await api().post(`/giaovu/thaydoimatkhau`, data);
            if(response.status === 200){
                window.alert('Đổi mật khẩu thành công!!!');
            }
        } catch (e){
          window.alert('Mật khẩu cũ không đúng!!!');
            console.error(e);
        }
        
      };
    
      return (
        <div className="change-password">
          <p className="change-password-title">Thay Đổi Mật Khẩu</p>
    
          <form onSubmit={handleSubmit(onSubmit)} className="change-password-form">
            <input
              type="hidden"
              {...register("idTaiKhoan")}
              value={taikhoanAD?.idTaiKhoan || ""}
            />
    
            <div className="mb-3 mt-3">
              <label htmlFor="oldpw" className="form-label">
                Nhập Mật Khẩu Cũ:
              </label>
              <input
                type="password"
                className="form-control"
                id="oldpw"
                placeholder="Nhập Mật Khẩu Cũ"
                {...register("matKhau", { required: "Vui lòng nhập mật khẩu cũ" })}
              />
              {errors.matKhau && (
                <span className="text-danger">{errors.matKhau.message}</span>
              )}
            </div>
    
            <div className="mb-3">
              <label htmlFor="pwd" className="form-label">
                Nhập Mật Khẩu Mới:
              </label>
              <input
                type="password"
                className="form-control"
                id="pwd"
                placeholder="Nhập Mật Khẩu Mới"
                {...register("mkMoi", { required: "Vui lòng nhập mật khẩu mới" })}
              />
              {errors.mkMoi && (
                <span className="text-danger">{errors.mkMoi.message}</span>
              )}
            </div>
    
            <div className="mb-3">
              <label htmlFor="cfpwd" className="form-label">
                Nhập Lại Mật Khẩu:
              </label>
              <input
                type="password"
                className="form-control"
                id="cfpwd"
                placeholder="Nhập Lại Mật Khẩu"
                {...register("xacNhanMk", {
                  required: "Vui lòng nhập lại mật khẩu",
                })}
              />
              {errors.xacNhanMk && (
                <span className="text-danger">{errors.xacNhanMk.message}</span>
              )}
            </div>
    
            <div className="btn-change-password-div">
              <button type="submit" className="btn btn-primary btn-change-password">
                Thay Đổi
              </button>
            </div>
          </form>
        </div>
      );
}

export default ThayDoiMatKhauAdmin;