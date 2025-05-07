import React, { createContext, useEffect, useState } from "react";
import { initKeycloak } from "./index";

export const KeycloakContext = createContext();

export const KeycloakProvider = ({ children }) => {
  const [keycloak, setKeycloak] = useState(null);

  useEffect(() => {
    initKeycloak().then((keycloakInstance) => {
      setKeycloak(keycloakInstance);
    });
  }, []);

  return (
    <KeycloakContext.Provider value={keycloak}>
      {children}
    </KeycloakContext.Provider>
  );
};
