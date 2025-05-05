import { Button, Checkbox, FormControl, MenuItem, Select, TextField } from "@mui/material";
import dayjs from "dayjs"; // Thư viện giúp xử lý ngày dễ dàng
import React, { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { api } from "../../api";
import "./DangKyMonHocAdmin.css";

const DangKiMonHoc = () => {
  const { handleSubmit, control, watch, setValue } = useForm();
  const [checkedSubjects, setCheckedSubjects] = useState({});
  const [hocki, setHocKi] = useState();
  const [monhoc, setMonhoc] = useState([]);
  const [phonghoc, setPhonghoc] = useState([]);
  const [disabledSubjects, setDisabledSubjects] = useState({});
  const [giangvien, setGiangvien] = useState([]);
  const [startDate, setStartDate] = useState("");
  const [mondachon, setMondachon] = useState([]);
  const { id } = useParams('id');

  async function fetchHocKy(){
    try{
        const response = await api().get(`/giaovu/hocky/${id}`);
        setHocKi(response.data);
    } catch (e) {
        console.error(e);
    }
  }

  async function fetchMonHoc(){
    try{
        const response = await api().get(`/giaovu/monhoc`);
        setMonhoc(response.data.content);
    } catch (e) {
        console.error(e);
    }
  }

  async function fetchPhongHoc(){
    try{
        const response = await api().get(`/giaovu/phonghoc`);
        setPhonghoc(response.data);
    } catch (e) {
        console.error(e);
    }
  }

  async function fetchGiangVien(){
    try{
        const response = await api().get(`/giaovu/giangvien`);
        setGiangvien(response.data);
    } catch (e) {
        console.error(e);
    }
  }

  async function fetchMonDaChon(){
    try{
        const response = await api().get(`/giaovu/monhoc/hocky/${id}`);
        console.log(response);
        setMondachon(response.data);
    } catch (e) {
        console.error(e);
    }
  }

  useEffect(() => {
    fetchHocKy();
    fetchMonHoc();
    fetchPhongHoc();
    fetchGiangVien();
    fetchMonDaChon();
  }, [])
  useEffect(() => {
    const initialCheckedState = {};
    const initialDisabledState = {};
    const today = dayjs(); // Lấy ngày hiện tại
  
    mondachon.forEach((item) => {
      const startDate = dayjs(item.ngayBatDau);
      const endDate = dayjs(item.ngayKetThuc);
      const isWithinDateRange = today.isAfter(startDate);
  
      initialCheckedState[item.idMonHoc.idMonHoc] = true;
      initialDisabledState[item.idMonHoc.idMonHoc] = isWithinDateRange;
  
      // Gán giá trị mặc định cho các field nếu môn học đã chọn
      setValue(`soLuong_${item.idMonHoc.idMonHoc}`, item.soLuong || "");
      setValue(`ngayBatDau_${item.idMonHoc.idMonHoc}`, startDate.format("YYYY-MM-DD"));
      setValue(`ngayKetThuc_${item.idMonHoc.idMonHoc}`, endDate.format("YYYY-MM-DD"));
      setValue(`phongHoc_${item.idMonHoc.idMonHoc}`, item.phongHoc?.idPhongHoc || "");
      setValue(`giangVien_${item.idMonHoc.idMonHoc}`, item.idGiangVien?.idTaiKhoan || "");
    });
  
    setCheckedSubjects(initialCheckedState);
    setDisabledSubjects(initialDisabledState);
  }, [mondachon, setValue]);

  const handleCheckboxChange = (id) => {
    if (disabledSubjects[id]) return;
  
    setCheckedSubjects((prev) => {
      const newCheckedState = { ...prev, [id]: !prev[id] };
  
      // Nếu checkbox bị bỏ chọn thì reset giá trị của input
      if (!newCheckedState[id]) {
        setValue(`soLuong_${id}`, "");
        setValue(`ngayBatDau_${id}`, "");
        setValue(`ngayKetThuc_${id}`, "");
        setValue(`phongHoc_${id}`, "");
        setValue(`giangVien_${id}`, "");
      }
  
      return newCheckedState;
    });
  };
  
  async function onSubmit(data){
    const formattedData = Object.keys(data).reduce((acc, key) => {
      const [field, id] = key.split("_"); // Tách field và id môn học
  
      if (disabledSubjects[id]) {
        return acc; // Bỏ qua các môn học bị disable
      }
  
      if (!acc[id]) acc[id] = { idMonHoc: id };
      acc[id][field] = data[key];
      return acc;
    }, {});
  
    // Chuyển từ object về array
    let payload = Object.values(formattedData);
    console.log(payload);
  
    try{
      await api().post(`/giaovu/dangkymonhoc/${id}`, payload);
      window.alert('Cập nhật thành công!!!')
    } catch (e){
      console.error(e);
    }
  }

  const today = new Date().toISOString().split("T")[0];

  return (
    <form className="container--form__dkmh" onSubmit={handleSubmit(onSubmit)}>
      <div className="container--title">
        <p>{hocki?.tenHocKy?.tenHocKy}</p>
        <p><b>Lớp học:</b> {hocki?.idLop.tenLopHoc}</p>
        <p><b>Khóa học:</b> {hocki?.idLop.idKhoaHoc.tenKhoa}</p>
        <p><b>Ngành đào tạo:</b> {hocki?.idLop.idNganh.tenNganhDaoTao}</p>
      </div>
      <Button sx={{ marginLeft: "auto", display: "block", marginRight: '120px', marginTop: '50px' }} type="submit" variant="contained">Lưu</Button>

      <table>
        <thead>
          <tr>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số lượng</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Phòng học</th>
            <th>Giảng viên</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {monhoc.map((mh) => (
            <tr key={mh.idMonHoc}>
              <Controller
                name={`idMonHocHocKy_${mh.idMonHoc}`}
                control={control}
                defaultValue={mondachon?.find(item => {
                  return item.idMonHoc.idMonHoc === mh.idMonHoc
                })?.idMonHocHocKy || ""}
                render={({ field }) => <input type="hidden" {...field} />}
              />
              <td>{mh.idMonHoc}</td>
              <td>{mh.tenMonHoc}</td>
              <td>
                <Controller
                  name={`soLuong_${mh.idMonHoc}`}
                  control={control}
                  defaultValue=""
                  render={({ field }) => <TextField {...field} type="number" required={checkedSubjects[mh.idMonHoc] || false} // Required nếu checked
                  disabled={!checkedSubjects[mh.idMonHoc] || disabledSubjects[mh.idMonHoc]} />}
                />
              </td>
              <td>
                <Controller
                  name={`ngayBatDau_${mh.idMonHoc}`}
                  control={control}
                  defaultValue=""
                  render={({ field }) => <TextField {...field} inputProps={{ min: today }} onChange={(e) => {
                    const selectedDate = e.target.value;
                    setStartDate(selectedDate);
                    field.onChange(selectedDate);
                  }} type="date" required={checkedSubjects[mh.idMonHoc] || false} // Required nếu checked
                  disabled={!checkedSubjects[mh.idMonHoc] || disabledSubjects[mh.idMonHoc]} />}
                />
              </td>
          
              <td>
                <Controller
                  name={`ngayKetThuc_${mh.idMonHoc}`}
                  control={control}
                  defaultValue=""
                  render={({ field }) => <TextField {...field} 
                  inputProps={{
                    min: startDate ? new Date(new Date(startDate).setMonth(new Date(startDate).getMonth() + 2)).toISOString().split("T")[0] : today,
                  }} type="date" required={checkedSubjects[mh.idMonHoc] || false} // Required nếu checked
                  disabled={!checkedSubjects[mh.idMonHoc] || disabledSubjects[mh.idMonHoc]} />}
                />
              </td>
              <td>
                <FormControl fullWidth>
                  <Controller
                    name={`phongHoc_${mh.idMonHoc}`}
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                      <Select {...field} required={checkedSubjects[mh.idMonHoc] || false} // Required nếu checked
                      disabled={!checkedSubjects[mh.idMonHoc] || disabledSubjects[mh.idMonHoc]}>
                        {phonghoc.map((ph) => (
                          <MenuItem key={ph.idPhongHoc} value={ph.idPhongHoc}>{ph.tenPhongHoc}</MenuItem>
                        ))}
                      </Select>
                    )}
                  />
                </FormControl>
              </td>
              <td>
                <FormControl fullWidth>
                  <Controller
                    name={`giangVien_${mh.idMonHoc}`}
                    control={control}
                    defaultValue=""
                    render={({ field }) => (
                      <Select {...field} required={checkedSubjects[mh.idMonHoc] || false} // Required nếu checked
                      disabled={!checkedSubjects[mh.idMonHoc] || disabledSubjects[mh.idMonHoc]}>
                        {giangvien?.map((gv) => (
                          <MenuItem key={gv.idGiangVien} value={gv.idTaiKhoan}>{gv.hoTen}</MenuItem>
                        ))}
                      </Select>
                    )}
                  />
                </FormControl>
              </td>
              <td>
              <Checkbox
                checked={checkedSubjects[mh.idMonHoc] || false}
                onChange={() => handleCheckboxChange(mh.idMonHoc)}
                disabled={disabledSubjects[mh.idMonHoc] || false} // Disable nếu trong khoảng ngày
              />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      
    </form>
  );
};

export default DangKiMonHoc;
