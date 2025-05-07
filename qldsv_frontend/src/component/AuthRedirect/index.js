import { Navigate } from "react-router-dom";

const AuthRedirect = (props) => {
  const { keycloak, roles } = props;

  if (!keycloak?.authenticated) {
    return <Navigate to="/auth-option" />;
  }

  if (roles.includes("GVU")) {
    return <Navigate to="/giaovu/home" replace />;
  }

  return <Navigate to="/home" replace />;
};

export default AuthRedirect;
