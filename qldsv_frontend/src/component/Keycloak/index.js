import Keycloak from 'keycloak-js';

let keycloak;

export const initKeycloak = () => {
  keycloak = new Keycloak({
    url: "http://localhost:8180", // URL Keycloak của bạn
    realm: "qlydiemsinhvien",
    clientId: "quanlidiemsinhvien",
  });

  return new Promise((resolve, reject) => {
    keycloak.init({ onLoad: "login-required", redirectUri: window.location.origin + "/",}).then((authenticated) => {
      if (authenticated) {
        resolve(keycloak);
      } else {
        reject("Keycloak authentication failed");
      }
    });
  });
};

export const keycloakService = {
    getKeycloak: () => keycloak,
    logout: () => keycloak.logout(),
  };