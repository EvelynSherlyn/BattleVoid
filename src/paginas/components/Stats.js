import React from "react";

export default function Stats( {jugador} ) {
    return (
        <div className={'stats'}>
            <p>FUERZA:   {jugador.fuerza}</p>
            <p>DESTREZA:   {jugador.destreza}</p>
            <p>PODER:   {jugador.poder}</p>
        </div>
    )
}