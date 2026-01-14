import React from "react";

export default function InfoCharacter({ jugador }) {
    return (
        <div>
            <p id={'nombre'}>{jugador.nombre}</p>
            <p>{jugador.info}</p>
        </div>
    )
}