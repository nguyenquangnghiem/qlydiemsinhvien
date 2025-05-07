import { useContext } from "react";
import { KeycloakContext } from "../../component/Keycloak/keycloakProvider";
import HomeGV from "../HomeGV";
import HomeSV from "../HomeSV";

const Home = () => {
    const keycloak = useContext(KeycloakContext);
    console.log(keycloak);
    const roles = keycloak?.tokenParsed?.resource_access[keycloak?.tokenParsed?.azp]?.roles || [];
    return (
        <>
            {roles.includes('GV')? <HomeGV/> : <HomeSV/>}
            {/* <HomeGV/> */}
        </>
    );
}

export default Home;