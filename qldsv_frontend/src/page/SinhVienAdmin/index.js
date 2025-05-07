import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { FaTrashAlt, FaEdit } from "react-icons/fa";
import "bootstrap/dist/css/bootstrap.min.css";
import { useApi } from "../../api";
import { Pagination } from "@mui/material";
import {
  Paper,
  styled,
  Table,
  TableBody,
  TableCell,
  tableCellClasses,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";

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

const SinhVienAdmin = () => {
    const api = useApi();
  const [students, setStudents] = useState([]);
  const [search, setSearch] = useState("");
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const response = await api.get("/giaovu/sinhvien");
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Bạn có chắc muốn xóa sinh viên này?")) {
      try {
        await api.delete(`/giaovu/sinhvien/add/${id}`);
        setStudents(students.filter((sv) => sv.idTaiKhoan !== id));
      } catch (error) {
        console.error("Error deleting student:", error);
      }
    }
  };

  const filteredStudents = students.filter((sv) =>
    sv.hoTen.toLowerCase().includes(search.toLowerCase())
  );

  const totalPages = Math.ceil(filteredStudents.length / itemsPerPage);
  const paginatedStudents = filteredStudents.slice(
    (currentPage - 1) * itemsPerPage,
    currentPage * itemsPerPage
  );

  return (
    <>
      <div className="container mt-4">
        <div className="nav-tk">
          <form className="search">
            <input
              className="form-control"
              type="text"
              placeholder="Search..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </form>
        </div>

        <div className="title-gv d-flex justify-content-between align-items-center my-3">
          <h1 className="text-center">Danh sách sinh viên</h1>
          <Link to="/giaovu/them-suasinhvien" className="btn btn-success">
            Thêm sinh viên
          </Link>
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
                <StyledTableRow key={row.idSinhVien}>
                  <StyledTableCell component="th" scope="row">
                    {row.idSinhVien}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.hoTen}</StyledTableCell>
                  <StyledTableCell align="right">
                    {new Date(row.ngaySinh).toLocaleDateString("vi-VN")}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.diaChi}</StyledTableCell>
                  <StyledTableCell align="right">
                    {row.gioiTinh === 1 ? "Nam" : "Nữ"}
                  </StyledTableCell>
                  <StyledTableCell align="right">{row.soDienThoai}</StyledTableCell>
                  <StyledTableCell align="right">{row.email}</StyledTableCell>
                  <StyledTableCell align="center">
                    <Link
                      to={`/giaovu/them-suasinhvien/${row.idTaiKhoan}`}
                      className="btn btn-warning me-2"
                    >
                      <FaEdit size={20} />
                    </Link>
                    <button
                      onClick={() => handleDelete(row.idTaiKhoan)}
                      className="btn btn-danger"
                    >
                      <FaTrashAlt size={20} />
                    </button>
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

export default SinhVienAdmin;
