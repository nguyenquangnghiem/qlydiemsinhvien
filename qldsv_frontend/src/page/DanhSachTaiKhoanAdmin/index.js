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
    
    const DanhSachTaiKhoanAdmin = () => {
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
          const response = await api.get("/giaovu/taikhoan");
          setStudents(response.data);
        } catch (error) {
          console.error("Error fetching students:", error);
        }
      };
    
      const filteredStudents = students.filter((sv) =>
        sv?.attributes?.ho_ten[0]?.toLowerCase().includes(search.toLowerCase())
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
              <h1 className="text-center">Danh sách tài khoản</h1>
            </div>
    
            <TableContainer component={Paper}>
              <Table sx={{ minWidth: 700 }} aria-label="customized table">
                <TableHead>
                  <TableRow>
                    <StyledTableCell>Mã tài khoản</StyledTableCell>
                    <StyledTableCell align="right">Tên tài khoản</StyledTableCell>
                    <StyledTableCell align="center">Chức vụ</StyledTableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {paginatedStudents.map((row) => (
                    <StyledTableRow key={row.id}>
                      <StyledTableCell component="th" scope="row">
                        {row.id}
                      </StyledTableCell>
                      <StyledTableCell align="right">{row.email}</StyledTableCell>
                      <StyledTableCell align="center">
                        {Array.from(row.chucVu).length > 0 ? Array.from(row.chucVu).at(0) : ""}
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
    
    export default DanhSachTaiKhoanAdmin;
    