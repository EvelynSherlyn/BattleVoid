import './App.css';
import { Navigate, Route, Routes } from 'react-router-dom';
import HomePage from './paginas/HomePage';
import Void from './paginas/pages/Void';
import Selector from './paginas/pages/Selector';
import Combate from './paginas/pages/Combate';

function App() {
  return (
    <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/void" element={<Void />} /> {/*Nada literalmente hablando*/}
        <Route path="/selector" element={<Selector />} />
        <Route path="/combate/:index/:index2" element={<Combate />} />

        {/* // Todo lo que no coincida con lo de arriba me va a este */}
        <Route path="*" element={<Navigate to="/"></Navigate>}></Route>
      </Routes>
  );
}

export default App;
