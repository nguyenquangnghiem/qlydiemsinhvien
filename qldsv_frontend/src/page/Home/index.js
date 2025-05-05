import { useSelector } from "react-redux";
import HomeGV from "../HomeGV";
import HomeSV from "../HomeSV";

const Home = () => {
    const giangvien = useSelector(state => state.giangVienReducer);
    return (
        <>
            {giangvien? <HomeGV/> : <HomeSV/>}
            {/* <HomeGV/> */}
        </>
    );
}

export default Home;