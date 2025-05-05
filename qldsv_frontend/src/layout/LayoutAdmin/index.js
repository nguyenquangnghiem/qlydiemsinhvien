import { Outlet } from "react-router-dom";
import HeaderAdmin from "../../component/HeaderAdmin";
import FooterAdmin from "../../component/FooterAdmin";

const LayoutAdmin = () => {
    return (
        <>
            <HeaderAdmin/>
            <Outlet/>
            {/* <FooterAdmin/> */}
        </>
    );
}

export default LayoutAdmin;