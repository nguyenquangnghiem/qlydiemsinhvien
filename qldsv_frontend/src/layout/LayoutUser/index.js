import { Outlet } from "react-router-dom";
import Header from "../../component/Header";
import Footer from "../../component/Footer";
import SideBar from "../../component/SideBar";

const LayoutUser = () => {
    return (
        <>
            <Header/>
            <SideBar/>
            <main style={{flex: 1}}>
                <Outlet/>
            </main>
            <Footer/>
        </>
    )
}

export default LayoutUser;