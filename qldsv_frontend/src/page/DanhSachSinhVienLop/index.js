import {
    Pagination, Paper,
    styled,
    Table,
    TableBody,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TableRow
} from "@mui/material";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { FaEdit } from "react-icons/fa";
import { Link, useParams } from "react-router-dom";
import { useApi } from "../../api";
  
  const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
    },
  }));
  
  const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
      backgroundColor: theme.palette.action.hover,
    },
    "&:last-child td, &:last-child th": {
      border: 0,
    },
  }));
  
  const DanhSachSinhVienLop = () => {
    const api = useApi();
    const [students, setStudents] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [lopHoc, setLopHoc] = useState(null);
    const itemsPerPage = 5;
    const { id } = useParams('id');
  
    useEffect(() => {
      fetchLopHoc();
      fetchStudents();
    }, []);
  
    const fetchStudents = async () => {
      try {
        const response = await api.get(`/giaovu/sinhvien/lophoc/${id}`);
        setStudents(response.data);
      } catch (error) {
        console.error("Error fetching students:", error);
      }
    };

    const fetchLopHoc = async () => {
      try{
        const response = await api.get(`/giaovu/lophoc/${id}`)
        setLopHoc(response.data);
      }catch(e){
        console.error(e);
      }
    }
  
    const totalPages = Math.ceil(students.length / itemsPerPage);
    const paginatedStudents = students?.slice(
      (currentPage - 1) * itemsPerPage,
      currentPage * itemsPerPage
    ) || [];
  
    return (
      <>
        <div className="container mt-4">
          <div className="title-gv d-flex justify-content-center align-items-center my-3">
            <h1 className="text-center">Danh sách sinh viên lớp {lopHoc?.tenLopHoc || ""} </h1>
          </div>
  
          <TableContainer component={Paper}>
            <Table sx={{ minWidth: 700 }} aria-label="customized table">
              <TableHead>
                <TableRow>
                  <StyledTableCell>Mã số sinh viên</StyledTableCell>
                  <StyledTableCell align="right">Họ và tên</StyledTableCell>
                  <StyledTableCell align="right">Ngày sinh</StyledTableCell>
                  <StyledTableCell align="right">Địa chỉ</StyledTableCell>
                  <StyledTableCell align="right">Giới tính</StyledTableCell>
                  <StyledTableCell align="right">Số điện thoại</StyledTableCell>
                  <StyledTableCell align="right">Email</StyledTableCell>
                  <StyledTableCell align="center">Hành động</StyledTableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {paginatedStudents.map((row) => (
                  <StyledTableRow key={row.idLopHoc}>
                    <StyledTableCell component="th" scope="row">
                      {row.idSinhVien}
                    </StyledTableCell>
                    <StyledTableCell align="right">{row.hoTen}</StyledTableCell>
                    <StyledTableCell align="right">
                      {new Date(row.ngaySinh).toLocaleDateString("vi-VN")}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {row.diaChi}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {row.gioiTinh === 1? "Nam" : "Nữ"}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {row.soDienThoai}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {row.email}
                    </StyledTableCell>
                    <StyledTableCell align="center">
                      <Link
                        to={`/giaovu/chitietsinhvien/${row.idTaiKhoan}`}
                        className="btn btn-warning me-2"
                      >
                        <FaEdit size={20} />
                      </Link>
                    </StyledTableCell>
                  </StyledTableRow>
                ))}
              </TableBody>
            </Table>
          </TableContainer>
          
          <div className="d-flex justify-content-center mt-3">
            <Pagination
              count={totalPages}
              page={currentPage}
              onChange={(event, value) => setCurrentPage(value)}
              color="primary"
            />
          </div>
        </div>
      </>
    );
  };
export default DanhSachSinhVienLop;