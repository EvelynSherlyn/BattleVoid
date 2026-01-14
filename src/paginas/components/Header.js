import React from "react";
import { useNavigate } from 'react-router-dom'


export default function Header({direccion}) {
      const nav = useNavigate();

    return (
        <div className={'cabecera'}>
            <img src={require("../assets/elementos/corazonIcono.png")} alt="icono" width="30" />
            <p id={'titulo'}>BattleVoid</p>
            <button id={'salir'} onClick={() => nav(direccion)}>X</button>
        </div>
    )
}