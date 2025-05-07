import "bootstrap/dist/css/bootstrap.min.css";
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import { KeycloakProvider } from "./component/Keycloak/keycloakProvider";
import store from './redux/store';
import './resources/css/access.css';
import './resources/css/student.css';
import './resources/css/teacher.css';
import Routes from './routes';

function App() {
  return (
    <>
    <Provider store={store}>
        <KeycloakProvider>

    <BrowserRouter>
      <Routes/>
    </BrowserRouter>
        </KeycloakProvider>
    </Provider>
    </>
  );
}

export default App;
