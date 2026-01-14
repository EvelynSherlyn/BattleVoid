import React from 'react'
import './style/import.css';
import { useNavigate } from 'react-router-dom'

import Header from './components/Header';

function HomePage() {
  const nav = useNavigate();
  return (
    <div className={'padre'}>
        <div className={'marco fondo'}>

          <Header direccion={"/void"}/>

          {/*--------------------*/}

          <div className={'cuerpo'}>
            <div className={'menu'}>
              <img src={require("./assets/BATTLEVOID_BLANCO_Evelyn.png")} alt="Titulo de juego"/>
              <button type="button"  onClick={() => nav("/selector")}>Entrar al void</button>
              <button type="button" onClick={() => nav("/void")}>Escapar</button>
            </div>
          </div>
        </div>
    </div>
  )
}

export default HomePage

/*
useNavigate (Misma función que <Link> pero este no se comporta como <a>, perfecto para botones): https://reactrouter.com/api/hooks/useNavigate
*/