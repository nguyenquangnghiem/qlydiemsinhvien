import "bootstrap/dist/css/bootstrap.min.css";
import { Provider } from 'react-redux';
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import store from './redux/store';
import './resources/css/access.css';
import './resources/css/student.css';
import './resources/css/teacher.css';
import Routes from './routes';

function App() {
  return (
    <>
    <Provider store={store}>
    <BrowserRouter>
      <Routes/>
    </BrowserRouter>
    </Provider>
    </>
  );
}

export default App;
